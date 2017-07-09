define(['constants', 'jquery'], function(Constants, jquery) {
	return {		
		resetDropDown: function(element) {
			element.prop('selectedIndex', -1);
			element.empty();
		},		
		
		initalisePlugins: function() {
			this.addFormatMethodInString();
			window.dateFormatter = function(value, row, index) {
				//Use this method for formatting the dates in bootstrap table.
	        	return moment(value).utc().format(Constants.DATE_FORMAT);
	        }
		},
		
		addFormatMethodInString: function() {
			String.prototype.format = function() {
			    var formatted = this;
			    for (var i = 0; i < arguments.length; i++) {
			        var regexp = new RegExp('\\{'+i+'\\}', 'gi');
			        formatted = formatted.replace(regexp, arguments[i]);
			    }
			    return formatted;
			};
		},
		//date would be in milisecond
		formatDate: function(date) {
			return moment(date).utc().format(Constants.DATE_FORMAT);
		},		
		selectdTextInDropDown: function(element) {
			return element.children('option').filter(':selected').text();
		},
		buildDate: function(dd, mm, yyyy, hour, min, ss) {
			return Date.today().set({
				    second: ss,
				    minute: min,
				    hour: hour,
				    day: dd,
				    month: mm,
				    year: yyyy
			});
		},
		removeTimeFromDate: function(date){  //date is in moment object
			return new Date(date.year(),date.month(),date.date());
		},
		
		datePickerOptions: function() {
			return {
				language: 'en',
			    pick12HourFormat: true
			}
		},
		toGMTDate: function(date) {
			//TODO Find some good way of setting the time zone in Moment.
			return moment(date).utc().zone(new Date().getTimezoneOffset() * -1)._d;
		},
		
		sampleLabel: function() {
			var description = {};
			description.Label1 = Constants.Label1;
			description.Label2 = Constants.Label2;			
			return description;
		},
				
		tooltipProperties: function() {
			return {
				className: 'tip-twitter',
				showTimeout: 1,
				alignTo: 'target',
				alignX: 'center',
				allowTipHover: false,
				fade: true,
				slide: true,
				offsetY: 20
			}
		},
		
		bootStrapTableOptions: function() {
			return {
				pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100, 200],
                search: true,
                showColumns: true,
                minimumCountColumns: 2,
                clickToSelect: true,
                striped: true
			}
		},
		
		toArray: function(obj) {
			return jquery.makeArray(obj);
		},
		getViewColumn: function(viewPath, id) {
			var href = viewPath + id;
			return ['<a title="View" class="pull-left view" href=#' + href + '>',
            '<i class="glyphicon glyphicon-eye-open"></i>',
            '</a>', '<a title="Delete" href="javascript:void(0)" class="pull-right delete" data-id=' + id + '>',
            '<i class="glyphicon glyphicon-remove-sign"></i>',
            '</a>'].join('');
		},
		getViewColumnOnly: function(viewPath, id) {
			var href = viewPath + id;
			return ['<a title="View" class="pull-left view" href=#' + href + '>',
            '<i class="glyphicon glyphicon-eye-open"></i>',
            '</a>'].join('');
		},
		backboneCollectionToBootstrapTableArray: function(viewPath, col) {
			var prettyArray = [];
			var that = this;
			col.forEach(function(ele) {				
				
			ele.attributes.viewCol = that.getViewColumn(viewPath, ele.attributes.id);
				
				
				prettyArray.push(ele.attributes)
			})
			return prettyArray;
		},
		
		backboneCollectionToBootstrapTableArrayForView: function(viewPath, col) {
			var prettyArray = [];
			var that = this;
			col.forEach(function(ele) {
				ele.attributes.viewCol = that.getViewColumnOnly(viewPath, ele.attributes.id);
				prettyArray.push(ele.attributes)
			})
			return prettyArray;
		},
		
		loadRoute: function(hash) {
			Backbone.history.loadUrl(hash);
		},
		fillDataInDropDown: function(parentElement, optionData) {
			parentElement.empty();
			for(i in optionData) {
				var option = "<option value=" +optionData[i].id + ">" + optionData[i].name + "</option>";
				parentElement.append(option);
			}
			parentElement.prop('selectedIndex',-1);
		},
		fillEnumDataInDropDown: function(parentElement, optionData) {
			parentElement.empty();
			for(i in optionData) {
				var option = "<option value=" +optionData[i].name + ">" + optionData[i].value + "</option>";
				parentElement.append(option);
				
			}
			parentElement.prop('selectedIndex',1);
		}
		
		
	}
})
