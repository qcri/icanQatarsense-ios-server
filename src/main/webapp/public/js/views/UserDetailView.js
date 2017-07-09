define([
     'marionette',
     'JST',
     'utils',
     'js/behaviors/FormActionsBehavior',
     'js/models/User'
], function(Marionette, JST, Utils,FormActionsBehavior, User) {
	return Marionette.ItemView.extend({
		template: JST['partials/userDetail.html'],
		behaviors: {
			FormActionsBehavior : {
				behaviorClass: FormActionsBehavior,
				listViewRoute: '#user',
				editViewRoute: '#user/edit/'
			}
		},
		ui: {
			'inputs': 'input',
			'userName': 'input[name="userName"]',
			'firstName': 'input[name="firstName"]',
			'lastName': 'input[name="lastName"]',
			'password': 'input[name="password"]',
			'role': '*[name="role"]',
			'group': '*[name="group"]',
			'associatedParticipant': '*[name="associatedParticipant"]'
		},	
		modelEvents: {
			'change': 'refresh'
		},
		events: {
			'change @ui.role': 'onChangeRole'
		},	
		onRender: function() {			
			this.fillRoleDropDown();
			this.fillGroupDropDown();	
			this.fillParticipiantDropDown();
		},
		serializeData: function() {
			var data = this.model.toJSON();
			this.options.isView = false;
			if(this.options.mode == 'view') {
				data.isView = true;
			}else if (this.options.mode == 'new') {
				data.isCreate = true;
			}else{
				data.isUpdate = true;
			}
			return data;
		},
		save: function(onSuccess, onError) {			
			var userName = this.ui.userName.val();
			var firstName = this.ui.firstName.val();
			var lastName = this.ui.lastName.val();
			var password = this.ui.password.val();
			var role = this.ui.role.val();
			var group = this.ui.group.val();
			var associatedParticipant = this.ui.associatedParticipant.val();
			if (!userName) {
				alert("userName is a required field");
				return;
			}

			if(userName.length>255){
				alert("Username length is greater than 255");
				return;
			}
			if (!password) {
				alert("password is a required field");
				return;
			}
			if (!firstName) {
				alert("firstName is a required field");
				return;
			}

			if(firstName.length>255){
				alert("First Name length is greater than 45");
				return;
			}
			if (!lastName) {
				alert("lastName is a required field");
				return;
			}
			if(lastName.length>45){
				alert("Last Name length is greater than 45");
				return;
			}
			if (!role) {				
				alert("Role is a required field");
				return;
			}			
			if(!associatedParticipant){
				if(role == 2)
					{
						alert("Associate Participant can't be empty, if role is Parent");
						return;
					}
			}
			if(associatedParticipant){
				if(role == 1 || role == 3)
					{
						alert("Associate Participant should be empty, if Role is Admin or Participant");
						return;
					}
			}
			var data = {};	
			if(this.model.isNew()){
				data = {						
						userName: userName,
						firstName: firstName,
						lastName: lastName,
						password: password,
						role: {id: role},
						group: {id: group},
						associatedParticipant: {id: associatedParticipant}
				}	
			}else{
				data = {						
						firstName: firstName,
						lastName: lastName,
						password: password
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
				url: '/qsense/web-ws/role/',
				success: function(role) {
					Utils.fillDataInDropDown(that.ui.role, role);
				}
			})
		},
		fillParticipiantDropDown: function() {
			var that = this;
			$.ajax({
				url: '/qsense/web-ws/user/participant/',
				success: function(associatedParticipant) {
					Utils.fillDataInDropDown(that.ui.associatedParticipant, associatedParticipant);
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
		onChangeRole: function(e) {
			Utils.resetDropDown(this.ui.associatedParticipant);
			var role = this.ui.role.val();
			if(role == 1 || role == 3)
			{
				this.ui.group.removeAttr('disabled');
				this.ui.associatedParticipant.attr('disabled', 'disabled');
				return;
			}
			this.ui.group.attr('disabled', 'disabled');
			this.ui.associatedParticipant.removeAttr('disabled');
			var that = this;
			$.ajax({
				url: '/qsense/web-ws/user/participant/',
				success: function(associatedParticipant) {
					Utils.fillDataInDropDown(that.ui.associatedParticipant, associatedParticipant);
				}
			})
		},
		refresh: function(model) {
			this.render();
		}
	})
})
