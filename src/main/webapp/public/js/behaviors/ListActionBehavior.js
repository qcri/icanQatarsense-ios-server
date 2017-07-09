define([
	'marionette',
	'utils',
], function(Marionette, Utils) {
	return Marionette.Behavior.extend({
		defaults: {
			deleteWarningMessage: "Are you sure ?",
			deleteErrorMessage: "Error occured while deleting ! Please try again",
			listViewRoute: '',
			createRoute: '',
			
		}, 
		ui: {
			'delete': '.delete',
			//'view': '.view',
			'create': '.create',
			'table': '.bootstrap-table'
		},
		events: {
			'click @ui.delete': 'onDelete',
			'click @ui.create': 'onCreate'
		},
		collectionEvents: {
	    	'reset': 'refreshTable',
	    	'resetView': 'refreshTableForView',
	    	'add': 'addData',
	    	'remove': 'removeData'
		},
		onShow: function() {
			this.ui.table.bootstrapTable(Utils.bootStrapTableOptions());
			this.ui.table.bootstrapTable('showLoading');
		},
		onCreate: function() {
			app.router.navigate(this.options.createRoute);
		},
		onDelete: function(e) {
			e.preventDefault();
			e.stopPropagation();
			var yes = confirm(this.options.deleteWarningMessage);
			if (yes) {
				var id = e.currentTarget.dataset['id']
				var model = this.view.collection.get(id);
				var that = this;
				model.destroy({
					wait: true,
					error: function() {
						alert(that.options.deleteErrorMessage)
					}
				});
			}			
		},
		refreshTable: function(data) {
			if(this.options.listViewRoute == "/message"){
				this.ui.table.bootstrapTable('load', Utils.backboneCollectionToBootstrapTableArrayForView(this.options.listViewRoute + '/', this.view.collection.toArray()));
			}
			else{
				this.ui.table.bootstrapTable('load', Utils.backboneCollectionToBootstrapTableArray(this.options.listViewRoute + '/', this.view.collection.toArray()));
			}
			this.ui.table.bootstrapTable('hideLoading');
		},
		refreshTableForView: function(data) {
			this.ui.table.bootstrapTable('load', Utils.backboneCollectionToBootstrapTableArrayForView(this.options.listViewRoute + '/', this.view.collection.toArray()));
			this.ui.table.bootstrapTable('hideLoading');
		},
		addData: function(data) {
			if(this.options.listViewRoute.indexOf('message') > -1){
				data.attributes.viewCol =  Utils.getViewColumnOnly(this.options.listViewRoute + '/' , data.attributes.id);
			}else{
				data.attributes.viewCol =  Utils.getViewColumn(this.options.listViewRoute + '/' , data.attributes.id);
			}			
			this.ui.table.bootstrapTable('append', data.attributes);
		},		
		removeData: function(model) {
			this.ui.table.bootstrapTable('remove', {field: 'id', values: [model.id]});
		}
	})
})
	