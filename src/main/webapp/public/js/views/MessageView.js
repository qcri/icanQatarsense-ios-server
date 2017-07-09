define([
     'marionette',
     'js/collections/MessageCollection',
     'JST',
     'utils',
     'js/behaviors/ListActionBehavior'
], function(Marionette, MessageCollection, JST, Utils, ListActionBehavior) {
	return Marionette.ItemView.extend({
		template: JST['partials/message.html'],
		behaviors: {
			ListActionBehavior: {
				behaviorClass: ListActionBehavior,
				createRoute: 'message/new',
				listViewRoute: '/message'
			}
		}
	})
			
		
})