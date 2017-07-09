define([
        'marionette',
        'utils',
        'constants',
        'JST',
        'moment',
        'async!https://www.google.com/jsapi?sensor=false',
        'goog!visualization,1,packages:[corechart, bar]'
], function(Marionette, Utils, Constants, JST) {
		return Marionette.ItemView.extend({
			template: JST['partials/dashboardBarGraph.html'],
			ui: {
				'barGraph': '#barGraph',
			},
			onShow: function() {
				this.drawBarGraphForActiveUsers();
			},
			drawBarGraphForActiveUsers: function() {
				var chart = new google.charts.Bar(this.ui.barGraph.get(0));
				var data = this.formatDataInBarGraphForActiveUsers(this.options.activeUsers);
				var options = {
				          chart: {
				            title: 'Bar chart of number of users for whom data has been logged'
				          }
				};
				chart.draw(data, options);
			},
			formatDataInBarGraphForActiveUsers: function(data) {
				var formattedData = [['Day', 'Control Group', 'Trial Group']];
				for(i in data) {
					var day = new Date(data[i].date);
					var controlGroup = data[i].controlGroupActiveUsers;
					var trialGroup = data[i].trialGroupActiveUsers
					formattedData.push([day,controlGroup,trialGroup]);
				}
				return google.visualization.arrayToDataTable(formattedData);
			},
			showBarGraph: function(e){
				e.preventDefault();
				this.ui.barGraph.show();
			}
		})
})