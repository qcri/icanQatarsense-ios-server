define ([
     'js/collections/BaseCollection', 'js/models/Message'
], function(BaseCollection, Message) {
	return BaseCollection.extend({
		model: Message,
		url: '/qsense/web-ws/message',
		initialize: function() {
			this.fetch({reset:true});
		}
	})
	
})