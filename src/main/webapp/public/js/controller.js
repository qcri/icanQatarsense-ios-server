define([
       
], function() {
	return {		
		onRouteBase: function() {
			require(['js/views/WelcomeView'], function(WelcomeView) {
				app.main.show(new WelcomeView());
			})
		},
		onRouteUser: function() {
			require(['js/views/UserView', 'js/collections/UserCollection'], function(UserView, UserCollection) {
				app.main.show(new UserView({collection:  new UserCollection()}));
			})			
		},
		onRouteCreateUser: function() {
			require(['js/views/UserDetailView', 'js/models/User'], function(UserDetailView, User) {
				app.main.show(new UserDetailView({mode: 'new',model: new User()}));
			})			
		},
		onRouteViewUser: function(id) {
			require(['js/views/UserDetailView', 'js/models/User'], function(UserDetailView, User) {
				app.main.show(new UserDetailView({mode: 'view',model: new User({id: id})}));
			})			
		},
		onRouteEditUser: function(id) {
			require(['js/views/UserDetailView', 'js/models/User'], function(UserDetailView, User) {
				app.main.show(new UserDetailView({mode: 'edit',model: new User({id: id})}));
			})			
		},
		onRouteMessage: function() {
			require(['js/views/MessageView', 'js/collections/MessageCollection'], function(MessageView, MessageCollection) {
				app.main.show(new MessageView({collection:  new MessageCollection()}));
			})			
		},
		onRouteCreateMessage: function() {
			require(['js/views/MessageDetailView', 'js/models/Message'], function(MessageDetailView, Message) {
				app.main.show(new MessageDetailView({mode: 'new',model: new Message()}));
			})			
		},
		onRouteViewMessage: function(id) {
			require(['js/views/MessageDetailView', 'js/models/Message'], function(MessageDetailView, Message) {
				app.main.show(new MessageDetailView({mode: 'view',model: new Message({id: id})}));
			})			
		},
		onRouteEditMessage: function(id) {
			require(['js/views/MessageDetailView', 'js/models/Message'], function(MessageDetailView, Message) {
				app.main.show(new MessageDetailView({mode: 'edit',model: new Message({id: id})}));
			})			
		},
		onRouteDashboardGraph: function() {
			require(['js/views/DashboardGraphView'], function(DashboardGraphView) {
				app.main.show(new DashboardGraphView());
			})
		}
	}	
})
