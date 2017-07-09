define([
      'js/models/BaseModel',
], function(BaseModel) {
	return BaseModel.extend({
		defaults: {
			id: null,
			title: 'Not assigned',
			content: 'Nothing',
			role: null,
			group: null
		},
		urlRoot : '/qsense/web-ws/message',
		initialize: function() {
		
		}
		
	})
})