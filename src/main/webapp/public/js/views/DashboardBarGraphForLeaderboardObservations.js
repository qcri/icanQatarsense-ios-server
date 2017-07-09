define([
        'marionette',
        'utils',
        'constants',
        'JST',
        'moment',
        'async!https://www.google.com/jsapi?sensor=false',
        'goog!visualization,1,packages:[motionchart]'        
], function(Marionette, Utils, Constants, JST) {
		return Marionette.ItemView.extend({
			template: JST['partials/dashboardBarGraph.html'],
			ui: {
				'barGraph': '#barGraph',
			},
			onShow: function() {
				this.drawBarGraphForLeaderboardObservations();
			},
			drawBarGraphForLeaderboardObservations: function() {
				var chart = new google.visualization.MotionChart(this.ui.barGraph.get(0));
				var data = this.formatDataInBarGraphForLeaderboardObservations(this.options.leaderboardObservations);
				var options ={};
				options['width'] = 800;
				options['height'] = 600;
				chart.draw(data, options);
			},
			formatDataInBarGraphForLeaderboardObservations: function(data) {
				var formattedData = [['Group', 'Date', 'Min', 'Max', 'Average']];
				for(i in data) {
					var day = new Date(data[i].date);
					formattedData.push(['Control Group', day, data[i].minForControl,  data[i].maxForControl,  data[i].averageForControl]);
					formattedData.push(['Trial Group', day, data[i].minForTrial,  data[i].maxForTrial,  data[i].averageForTrial]);
				}
				return google.visualization.arrayToDataTable(formattedData);
			},
			showBarGraph: function(e){
				e.preventDefault();
				this.ui.barGraph.show();
			}
		})
})