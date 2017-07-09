define([
    'js/models/BaseModel',
], function(BaseModel) {
	return BaseModel.extend({
		
		defaults: {
			id: null,
			userName: 'Not assigned',
			firstName: 'Nothing',
			lastName: 'Nothing',
			role: null,
			group: null,
			associatedParticipant: null
		},
		urlRoot : '/qsense/web-ws/user',
		initialize: function() {
		
		}
  
		
	})
})