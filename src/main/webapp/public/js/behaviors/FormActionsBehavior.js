define([
	'marionette'
], function(Marionette) {
	return Marionette.Behavior.extend({
		defaults: {
			cancelwarningMessage: "Saved changed will be lost",
			listViewRoute: '',
			deleteErrorMessage: 'Error occured while deleting ! Please try agaian',
			disabledElements: []
		},
		ui: {
			'inputs': 'input',
			'selects': 'select',
			'save': '.save',
			'cancel': '.cancel',
			'edit': '.edit',
			'delete': '.delete'			
		},
		events: {
			'click @ui.save': 'onSave',
			'click @ui.cancel': 'onCancel',
			'click @ui.edit': 'onEdit',
			'click @ui.delete': 'onDelete',
		},
		onRender: function() {
			if( this.view.options.mode == 'view') {
				this.ui.inputs.attr('disabled', 'disabled');
				this.ui.selects.attr('disabled', 'disabled');
			} else if(this.view.options.mode == 'edit') {
				if(this.view.getDisabledElements != undefined && this.view.getDisabledElements instanceof Function) {
					this.view.getDisabledElements().forEach(function(ele) {
						if(ele.is('div')) {
							ele.children('input').attr('disabled','disabled')							
						} else {
							ele.attr('disabled', 'disabled');
						}					
					}) 
				}				
			}
		},
		onShow: function() {
			if(this.view.options.mode != 'new') {
				this.view.model.fetch();
			}
			//this.model.on('change', this.modelChanged);
		},
		onSave: function(e) {
			e.preventDefault();
			e.stopPropagation();
			var model = this.view.model;
			var that = this;
			this.view.save(
				function(model, response) {
					//Success callback
					var previousRouteExist = app.router.previous();
					if(!previousRouteExist) {
						app.router.navigate(that.options.listViewRoute)
					}
				}, 
				//Error callback
				function(model, response) {
					alert("Error occured while saving the data: " + response.toString());
					return;
				});			
		},
		onCancel: function(e) {
			e.preventDefault();
			e.stopPropagation();
			var yes = confirm(this.options.cancelwarningMessage);
			if(yes) {
				var previousRouteExist = app.router.previous();
				if(!previousRouteExist) {
					app.router.navigate(this.options.listViewRoute)
				}
			} else {
				return;
			}
		},
		onEdit: function(e) {
			e.preventDefault();
			e.stopPropagation();
			app.router.navigate(this.options.listViewRoute + '/edit/' + this.view.options.model.id);
		},
		onDelete: function(e) {
			e.preventDefault();
			e.stopPropagation();
			var that = this;
			this.view.model.destroy({
				wait: true,			
				success: function(model,response) {
					//Success Callback
					app.router.navigate(that.options.listViewRoute);
				}, 
				error: function(model,response) {
					//Error callback
					alert(that.options.deleteErrorMessage);
				}
			})
		},
	})
})