define([
        'js/views/DashboardGraphInputView',
        'marionette',
        'JST',
        'js/views/DashboardBarGraphForActiveUsers',
        'js/views/DashboardBarGraphForLeaderboardObservations',
        'utils'
], function(DashboardGraphInputView, Marionette, JST, DashboardBarGraphForActiveUsers, DashboardBarGraphForLeaderboardObservations, Utils) {
	return Marionette.LayoutView.extend({
		template: JST['partials/dashboardGraph.html'],
		regions: {
			input: '.input',
			bar: '.bar'
		},
		onShow: function() {
			this.input.show(new DashboardGraphInputView());
			this.eventBinder();
		},
		showBarForActiveUsers: function(data) {
			this.bar.show(new DashboardBarGraphForActiveUsers(data));
		},
		showBarForLeaderboardObservations: function(data) {
			this.bar.show(new DashboardBarGraphForLeaderboardObservations(data));
		},
		eventBinder: function() {
			var that = this;
			
			app.vent.on('dashboardBarGraphForActiveUser:data:fetch', function(data) {
				that.showBarForActiveUsers(data);			
			});
			app.vent.on('dashboardBarGraphForLeaderboard:data:fetch', function(data) {
				that.showBarForLeaderboardObservations(data);			
			})
			
		},
		onDestroy: function() {
			//de-register the event once the view is closed so that a new event is registered when view renders next time.
			app.vent.off('dashboardBarGraphForActiveUser:data:fetch');
			app.vent.off('dashboardBarGraphForLeaderboard:data:fetch');

		}
	})
})