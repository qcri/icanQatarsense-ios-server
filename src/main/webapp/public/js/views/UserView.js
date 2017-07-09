define([
     'marionette',
     'js/collections/UserCollection',
     'JST',
     'utils',
     'js/behaviors/ListActionBehavior'
], function(Marionette, UserCollection, JST, Utils, ListActionBehavior) {
	return Marionette.ItemView.extend({
		template: JST['partials/user.html'],
		behaviors: {
			ListActionBehavior: {
				behaviorClass: ListActionBehavior,
				createRoute: 'user/new',
				listViewRoute: '/user'
			}
		}
	})
			
		
})