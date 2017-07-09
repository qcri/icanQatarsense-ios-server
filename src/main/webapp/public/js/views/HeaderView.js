define([
        'marionette',
        'JST'
], function(Marionette, JST) {
	return Marionette.ItemView.extend({
		template: JST['partials/header.html'],
		initalize: function() {
			console.log("Initalizing welcome view...");
		},
		ui: {			
			'link': '.navbar-nav li'
		},
		events: {			
			'click @ui.link': 'onClickRouteLink'
		},		
		onClickRouteLink: function(e) {
			this.ui.link.removeClass('active');
			$(e.currentTarget).addClass('active')
		}
	})
})
