define([
        'marionette',
        'js/controller'
], function(Marionete, Controller) {
	return {
		initialize: function() {
			var appRoutesHandlers = {
					'': 'onRouteBase',					
					'user': 'onRouteUser',
					'user/new': 'onRouteCreateUser',
					'user/:id': 'onRouteViewUser',
					'user/edit/:id': 'onRouteEditUser',
					'message': 'onRouteMessage',
					'message/new': 'onRouteCreateMessage',
					'message/:id': 'onRouteViewMessage',
					'message/edit/:id': 'onRouteEditMessage',
					'dashboard-graph': 'onRouteDashboardGraph'
			}			
			var Router =  Marionette.AppRouter.extend({
				history: [],
				app: app,
				controller: Controller,
				appRoutes: appRoutesHandlers,
				navigate: function(hash, trigger) {
					if(!trigger) {
						trigger = true;
					}
					return Marionette.AppRouter.prototype.navigate(hash,{trigger: trigger})
				},
				onRoute: function(name, path, arguments) {
					this.history.push(Backbone.history.fragment)
				},
				previous: function() {
					if(this.history.length > 1) {
						this.navigate(this.history[this.history.length-2]);
						return true;
					} else {
						return false;
					}					
				}
			})			
			return new Router();
		}
	}
})
