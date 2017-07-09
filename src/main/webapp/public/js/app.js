define([
        'marionette',
        'js/views/WelcomeView', 
        'js/router',
        'js/views/HeaderView',      
        'utils'
], function(Marionette, WelcomeView, Router, HeaderView, Utils) {
	var app = new Marionette.Application();
	var welcomeView = new WelcomeView();
	
	app.addRegions({
		header: 'header',
		main: '#main'
	});
	
	app.addInitializer(function() {
		console.log("Initalizing the application");	
		app.header.show(new HeaderView());
		Backbone.history.start();
		app.router = Router.initialize();
		var hash = Backbone.history.getFragment(window.href);
		Utils.loadRoute(hash);
	})	
	
	app.on('initialize:after', function() {
		/*Backbone.history.start({pushState: true});
		Router.initialize();*/
	})
	
	
	
	return window.app = app;
	
})