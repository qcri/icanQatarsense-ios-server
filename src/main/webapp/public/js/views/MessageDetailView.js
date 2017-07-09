define([
     'marionette',
     'utils',
     'JST',
     'js/behaviors/FormActionsBehavior',
     'js/models/Message'
], function(Marionette, Utils,JST,FormActionsBehavior, Message) {
	return Marionette.ItemView.extend({
		template: JST['partials/messageDetail.html'],
		behaviors: {
			FormActionsBehavior : {
				behaviorClass: FormActionsBehavior,
				listViewRoute: '#message',
				editViewRoute: '#message/edit/'
			}
		},
		ui: {
			'inputs': 'input',
			'title': 'input[name="title"]',
			'content': 'textarea[name="content"]',			
			'role': '*[name="role"]',
			'group': '*[name="group"]'
		},
		
		modelEvents: {
			'change': 'refresh'
		},
		onRender: function() {			
				this.fillRoleDropDown();
				this.fillGroupDropDown();			
				
		},
		serializeData: function() {
			var data = this.model.toJSON();
			this.options.isView = false;
			if(this.options.mode == 'view') {
				data.isView = true;
			}else if (this.options.mode == 'new') {
				data.isCreate = true;
			}
			return data;
		},
		save: function(onSuccess, onError) {			
			var title = this.ui.title.val();
			var content = this.ui.content.val();			
			var role = this.ui.role.val();
			var group = this.ui.group.val();
			if (!title) {				
				alert("Title is a required field");
				return;
			}
			if (!content) {
				alert("Content is a required field");
				return;
			}
			if (!role) {				
				alert("Role is a required field");
				return;
			}
			if (!group) {				
				alert("Group is a required field");
				return;
			}

			if(title.length>200){
				alert("Message title length is greater than 200");
				return;
			}
			if(content.length>1000){
				alert("Message content length is greater than 1000");
				return;
			}
			var data = {};	
			if(this.model.isNew()){
				data = {						
						title: title,
						content: content,						
						role: {id: role},
						group: {id: group}
				}	
			}
			
							
			this.model.save(data, {
				success: onSuccess,
				error: onError
			})
		},
		fillRoleDropDown: function() {
			var that = this;
			$.ajax({
				url: '/qsense/web-ws/role/withoutAdmin/',
				success: function(role) {
					Utils.fillDataInDropDown(that.ui.role, role);
				}
			})
		},
		fillGroupDropDown: function() {
			var that = this;
			$.ajax({
				url: '/qsense/web-ws/group/',
				success: function(group) {
					Utils.fillDataInDropDown(that.ui.group, group);
				}
			})
		},
		refresh: function(model) {
			this.render();
		}
	})
})
