define([ 'backbone' ], function(Backbone) {
	var originalSync = Backbone.sync;
	return Backbone.Collection.extend({

		sync : function(method, model, options) {
			var deferred = $.Deferred();
			options || (options = {});
			deferred.then(options.success, options.error);

			var response = originalSync(method, model, _.omit(options,
					'success', 'error'));

			response.done(deferred.resolve);
			response.fail(function() {				
				if (response.status == 200) {
					alert("Your session is timed out.");
					window.location.href = '/qsense/web-ws/j_spring_security_logout';
				} else {
					deferred.rejectWith(response, arguments);
				}
			});

			return deferred.promise();

		}

	})
})