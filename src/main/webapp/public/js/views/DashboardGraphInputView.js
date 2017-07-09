define([
        //'jquery',
//        'js/models/AlertsGraphInput',
        'marionette',
        'utils',
        'JST',
        'dateTimePicker'
], function(//AlertsGraphInput, 
		Marionette, Utils, JST) {
	return Marionette.ItemView.extend({
		template: JST['partials/dashboardBarGraphInput.html'],
		ui: {
				'activityName': '#activityName',
				'submit' : '[type="submit"]',
				'reset': ".reset",
				'startDate': '#startDate',
				'endDate': '#endDate',
				'chartTypeRadio': '[name="chartTypeRadio"]',
				'activityDiv': '.activityDiv'
		},
		events: {
			'click @ui.submit' : 'onSubmit',
			'click @ui.reset': 'resetPage',
			'change @ui.chartTypeRadio': 'onChangeChartType'
			
		},		
		onRender: function() {
			this.fillActivityName();
			this.ui.startDate.datetimepicker(Utils.datePickerOptions());
			this.ui.endDate.datetimepicker(Utils.datePickerOptions());
			this.ui.activityDiv.hide();
		},
		
		fillActivityName: function() {
			var that = this;
			$.ajax({
				url: '/qsense/web-ws/subCategory/adminDashboardVisible',
				type: 'GET',
				success: function(activityNames) {
					Utils.fillDataInDropDown(that.ui.activityName, activityNames);
				}
			})
		},
		onSubmit: function(e) {
			e.preventDefault();
			var activityNameId = this.ui.activityName.val();
			var startTime = this.ui.startDate.data('DateTimePicker').getDate(); //getDate() will give Moment object not Date object
			startTime = Utils.removeTimeFromDate(startTime).getTime();
			var endTime = this.ui.endDate.data('DateTimePicker').getDate();
			endTime = Utils.removeTimeFromDate(endTime).getTime();
			var that = this;
			var data ={startDate: startTime, endDate: endTime};
			var data2 ={subCategoryId: activityNameId, startDate: startTime, endDate: endTime};
			var chartTypeStatus = this.ui.chartTypeRadio[0].checked;   // boolean value of Total Active User Radio Selector
			if(chartTypeStatus){
				$.ajax({
					url: '/qsense/web-ws/logactivity/activeUsers',
					type: 'GET',
					data: data,
					success: function(data) {
//						var selectedAlertName = Utils.selectdTextInDropDown(that.ui.alertName);
						app.vent.trigger('dashboardBarGraphForActiveUser:data:fetch', {//alertName: selectedAlertName, 
							activeUsers: data});
					}
				})
			}
			else{
				
				if (!activityNameId) {
					alert("Select a activity");
					return;
				}	
			
				$.ajax({
					url: '/qsense/web-ws/leaderboard/observationForWeb',
					type: 'GET',
					data: data2,
					success: function(data) {
//						var selectedAlertName = Utils.selectdTextInDropDown(that.ui.alertName);
						app.vent.trigger('dashboardBarGraphForLeaderboard:data:fetch', {//alertName: selectedAlertName, 
							leaderboardObservations: data});
					}
				})
			}
		},	
		onChangeChartType: function(e) {
			
			if(this.ui.chartTypeRadio[0].checked){
				this.ui.activityDiv.hide();
			}
			else if(this.ui.chartTypeRadio[1].checked){
				this.ui.activityDiv.show();
			}
		},
		resetPage: function(e) {
			e.preventDefault();
			this.render();
		}
	});
})