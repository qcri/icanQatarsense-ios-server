define([
     'handlebars',
     'utils'
], function(Handlebars, Utils) {
	return {
		initalizeHelpers: function() {
			Handlebars.registerHelper("prettifyDate", function(timestamp) {
			    return Utils.formatDate(timestamp);
			});
		},
		registerCommonPartials: function() {
			require(['JST'], function(JST) {
				
			})
		}
	}
})