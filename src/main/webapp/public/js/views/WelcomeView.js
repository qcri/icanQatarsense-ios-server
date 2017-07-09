define([
        'marionette',
        'tpl!partials/welcomeView.html'
], function(Marionette, WelcomeTemplate) {
	return Marionette.ItemView.extend({
		template: WelcomeTemplate,
		className: 'row',
		initalize: function() {
			console.log("Initalizing welcome view...");
		}
	})
})