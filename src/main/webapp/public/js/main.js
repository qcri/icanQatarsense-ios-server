 require.config({
                baseUrl: 'public',
                waitSeconds: 0, //Setting wait timeout to infinite so that it would not fail for slow internet connections also
                paths: {
                        jquery: 'bower_components/jquery/dist/jquery.min',
                        bootstrap: 'bower_components/bootstrap/dist/js/bootstrap.min',
                        underscore: 'bower_components/underscore/underscore',
                        backbone: 'bower_components/backbone/backbone',
                        chart: 'bower_components/chartjs/Chart.min',
                        marionette: 'bower_components/marionette/lib/backbone.marionette.min',
                        app: 'js/app',
                        tpl: 'libs/tpl',
                        async: 'bower_components/requirejs-plugins/src/async',
                        goog: 'bower_components/requirejs-plugins/src/goog',
                        propertyParser : 'bower_components/requirejs-plugins/src/propertyParser',
                        utils: 'js/utils',
                        constants: 'js/constants',
                        multiselect: 'bower_components/select2/select2.min',
                        moment: 'bower_components/moment/moment',
            			'moment-timezone': 'bower_components/moment-timezone/moment-timezone',
            			dateTimePicker: 'libs/bootstrap-datepicker',
                        'bootstrap-table': 'bower_components/bootstrap-table/dist/bootstrap-table.min',
                        'JST': 'js/precompiled.handlebars',
                        'handlebars': 'bower_components/handlebars/handlebars.min',
                        'handlebars-helper': 'js/handlebarsHelper'
                },
                shim: {
                        underscore: {
                                exports: '_'
                        },
                        marionette: {
                                exports: 'Marionette',
                                deps: ['backbone']
                        },
                        bootstrap: {
                                deps: ['jquery']
                        },
                        dateTimePicker: {
            				deps: ['bootstrap', 'moment']
            			},
            			'moment-timezone': {
            				deps: ['moment']
            			},
                        'bootstrap-table': {
                                deps: ['bootstrap']
                        },
                        utils: {
                                deps: ['jquery']
                        },
                        'handlebars-helper': ['handlebars']
                }
        })

        require([
               'app', 'utils', 'handlebars-helper', 'bootstrap', 'moment-timezone', 'bootstrap-table'
        ], function(App, Utils, HandlebarsHelper){
                console.log("starting the app");
                Utils.initalisePlugins();
                HandlebarsHelper.initalizeHelpers();
                HandlebarsHelper.registerCommonPartials();
		App.start();
	})