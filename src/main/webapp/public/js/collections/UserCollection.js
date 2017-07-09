define ([
     'js/collections/BaseCollection', 'js/models/User'
], function(BaseCollection, User) {
	return BaseCollection.extend({
		model: User,
		url: '/qsense/web-ws/user',
		initialize: function() {
			this.fetch({reset:true});
		}
	})
	
})