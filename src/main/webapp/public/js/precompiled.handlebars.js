define(['handlebars'], function(Handlebars) {

this["JST"] = this["JST"] || {};

this["JST"]["partials/dashboardBarGraph.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<div id=\"BarGraphContainer\">\r\n	<div class=\"row\">\r\n	</div>\r\n	<div  class=\"row\" id=\"barGraph\" style=\"height: 500px;\"> </div>\r\n</div>\r\n";
  },"useData":true});

this["JST"]["partials/dashboardBarGraphInput.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<form role=\"form\">\r\n	<div class=\"form-group\">\r\n		<label for=\"startDate\" class=\"control-label\"> Start Date </label>\r\n		<div id=\"startDate\" class=\"datepicker input-append\">				\r\n			<input data-format=\"MM/dd/YYYY\"  class=\"form-control\" type=\"text\"></input>\r\n		</div>		\r\n	</div>	\r\n				\r\n	<div class=\"form-group\">\r\n		<label for=\"endDate\" class=\"control-label\"> End Date </label>\r\n		<div id=\"endDate\" class=\"datepicker input-append\">				\r\n			<input data-format=\"MM/dd/YYYY\"  class=\"form-control\" type=\"text\"></input>\r\n		</div>		\r\n	</div>	\r\n	\r\n	<div class=\"form-group\">\r\n		<label for=\"radio\" class=\"control-label\"> Chart For </label>\r\n	</div>\r\n	<div class=\"radio\">\r\n      <label><input type=\"radio\" name=\"chartTypeRadio\" checked>Total Active Users</label>\r\n    </div>\r\n    <div class=\"radio\">\r\n      <label><input type=\"radio\" name=\"chartTypeRadio\" >Activity</label>\r\n    </div>\r\n	    \r\n		\r\n	<div class=\"form-group activityDiv\">\r\n		<label for=\"activityName\" class=\"control-label\" > Activity </label>\r\n		<select class=\" form-control\" id=\"activityName\"> </select>\r\n	</div>\r\n		\r\n	<div class=\"form-group\">\r\n		<button class=\"btn btn-primary\" type=\"submit\"> Plot Graph </button>\r\n		<button class=\"btn btn-danger col-md-offset-1 reset\"> Clear </button>\r\n	</div>\r\n</form>\r\n	";
  },"useData":true});

this["JST"]["partials/dashboardGraph.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<div class=\"row\">\r\n	<div class=\"input col-md-4\"> </div>\r\n	<div class=\"col-sm-offset-1 col-md-6\">\r\n		<div class=\"row\">\r\n			<div class=\"bar col-md-12\" style=\"height: 500px;\"> </div>\r\n		</div>\r\n	</div>\r\n</div>\r\n";
  },"useData":true});

this["JST"]["partials/formAction.html"] = Handlebars.template({"1":function(depth0,helpers,partials,data) {
  return "		<div class=\"form-group\">\r\n			<button class=\"btn btn-primary edit\"> Edit </button>\r\n			<button class=\"btn btn-danger col-sm-offset-1 delete\"> Delete </button>\r\n		</div>\r\n";
  },"3":function(depth0,helpers,partials,data) {
  return "		<div class=\"form-group\">\r\n			<button class=\"btn btn-primary save\" type=\"submit\"> Save </button>\r\n			<button class=\"btn btn-danger col-sm-offset-1 cancel\"> Cancel </button>\r\n		</div>\r\n";
  },"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  var stack1, buffer = "<script id=\"formAction\" type=\"text/x-handlebars\">\r\n";
  stack1 = helpers['if'].call(depth0, (depth0 != null ? depth0.isView : depth0), {"name":"if","hash":{},"fn":this.program(1, data),"inverse":this.program(3, data),"data":data});
  if (stack1 != null) { buffer += stack1; }
  return buffer + "</script>";
},"useData":true});

this["JST"]["partials/header.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<nav class=\"navbar navbar-default nav-main\" role=\"navigation\">\r\n	<div class=\"container-fluid\">\r\n		<!-- Brand and toggle get grouped for better mobile display -->\r\n		<div class=\"navbar-header\">\r\n			<button type=\"button\" class=\"navbar-toggle collapsed\"\r\n				data-toggle=\"collapse\" data-target=\"#navbar-collapse-1\">\r\n				<span class=\"sr-only\">Toggle navigation</span> \r\n				<span class=\"icon-bar\"></span> \r\n				<span class=\"icon-bar\"></span> \r\n				<span class=\"icon-bar\"></span>\r\n			</button>\r\n			<a href=\"#\"> <img src=\"/qsense/public/img/logo_header.png\"></a> \r\n		</div>\r\n		<div class=\"collapse navbar-collapse\" id=\"navbar-collapse-1\">\r\n				<ul class=\"nav navbar-nav\">\r\n					<li><a href=\"#message\">Messages</a></li>\r\n					<li><a href=\"#user\">Users</a></li>\r\n					<li><a href=\"#dashboard-graph\">Visualize Data</a></li>\r\n				</ul>			 	\r\n					<ul class=\"user nav navbar-nav navbar-right\">						\r\n						<li class=\"dropdown\"><a class=\"dropdown-toggle\"\r\n							data-toggle=\"dropdown\"> <span class=\"user-pic\"></span> <span\r\n								class=\"caret\"></span></a>\r\n							<ul class=\"dropdown-menu\" role=\"menu\">								\r\n								<li><a href=\"j_spring_security_logout\">LogOut</a></li>\r\n							</ul>\r\n						</li>\r\n	\r\n				</ul>				\r\n									\r\n		</div>\r\n	</div>\r\n</nav>\r\n";
  },"useData":true});

this["JST"]["partials/message.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<div class=\"container\">\r\n	<div class=\"row\">\r\n		<div class=\"col-md-2\">\r\n			<button class=\"btn btn-primary create\">Create</button>\r\n		</div>\r\n	</div>\r\n	<div class=\"row\">\r\n		<div class=\"col-md-12\">\r\n			<table data-toggle=\"table\" data-cache=\"false\" class=\"bootstrap-table\" data-sort-name=\"postedAt\" data-sort-order=\"desc\">\r\n			    <thead>\r\n			        <tr>\r\n			            <th  data-field=\"id\" \"data-align=\"left\">ID</th>\r\n			            <th  data-field=\"title\" data-sortable=\"true\" data-align=\"left\">Title</th>\r\n			            <th  data-field=\"groupName\" data-sortable=\"true\" data-align=\"left\">Group</th>\r\n			            <th  data-field=\"roleName\" data-sortable=\"true\" data-align=\"left\">Role</th>\r\n			            <th  data-field=\"postedBy\" data-sortable=\"true\" data-align=\"left\">Posted By</th>\r\n			            <th  data-field=\"postedAt\" data-sortable=\"true\" data-align=\"left\">Posted At</th>\r\n			            <th  data-field=\"viewCol\" data-align=\"center\"></th>\r\n			        </tr>\r\n			    </thead>\r\n			    <tbody id=\"messageListContainer\">\r\n			    	\r\n			    </tbody>\r\n			</table>\r\n		</div>\r\n	</div>\r\n</div>";
  },"useData":true});

this["JST"]["partials/messageDetail.html"] = Handlebars.template({"1":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<a href=\"#message\" class=\"back\"> < back</a>\r\n			</div>\r\n";
  },"3":function(depth0,helpers,partials,data) {
  var stack1, helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, lambda=this.lambda;
  return "			<div class=\"form-group\">\r\n				<label forName=\"title\" class=\"control-label\" > Title </label>\r\n				<input class=\"form-control\" name=\"title\" disabled=\"disabled\" value=\""
    + escapeExpression(((helper = (helper = helpers.title || (depth0 != null ? depth0.title : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"title","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"content\" class=\"control-label\" > Content (max 1000)</label>\r\n				<textarea class=\"form-control\" name=\"content\" disabled=\"disabled\">"
    + escapeExpression(((helper = (helper = helpers.content || (depth0 != null ? depth0.content : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"content","hash":{},"data":data}) : helper)))
    + "</textarea>\r\n			</div>		\r\n			<div class=\"form-group\">\r\n				<label forName=\"role\" class=\"control-label\" > Role </label>\r\n				<input class=\"form-control\" name=\"role\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.role : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"group\" class=\"control-label\" > Group </label>\r\n				<input class=\"form-control\" name=\"group\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.group : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>	\r\n";
},"5":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<label forName=\"title\" class=\"control-label\" > Title </label>\r\n				<input class=\"form-control\" name=\"title\" \">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"content\" class=\"control-label\" > Content (max 1000) </label>\r\n				<textarea class=\"form-control\" name=\"content\" ></textarea>\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"role\" class=\"control-label\" > Role </label>\r\n				<select class=\"form-control\" name=\"role\" id=\"role\"></select>\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"group\" class=\"control-label\" > Group </label>\r\n				<select class=\"form-control\" name=\"group\"></select>\r\n			</div>	\r\n";
  },"7":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<button class=\"btn btn-primary save\" type=\"submit\"> Save </button>\r\n				<button class=\"btn btn-danger col-sm-offset-1 cancel\"> Cancel </button>\r\n			</div>\r\n";
  },"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  var stack1, helper, options, functionType="function", helperMissing=helpers.helperMissing, blockHelperMissing=helpers.blockHelperMissing, buffer = "<div class=\"container\">\r\n	<form role=\"form\">\r\n";
  stack1 = helpers['if'].call(depth0, (depth0 != null ? depth0.isView : depth0), {"name":"if","hash":{},"fn":this.program(1, data),"inverse":this.noop,"data":data});
  if (stack1 != null) { buffer += stack1; }
  buffer += "		\r\n";
  stack1 = ((helper = (helper = helpers.isView || (depth0 != null ? depth0.isView : depth0)) != null ? helper : helperMissing),(options={"name":"isView","hash":{},"fn":this.program(3, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isView) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  stack1 = ((helper = (helper = helpers.isCreate || (depth0 != null ? depth0.isCreate : depth0)) != null ? helper : helperMissing),(options={"name":"isCreate","hash":{},"fn":this.program(5, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isCreate) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  buffer += "		\r\n";
  stack1 = ((helper = (helper = helpers.isCreate || (depth0 != null ? depth0.isCreate : depth0)) != null ? helper : helperMissing),(options={"name":"isCreate","hash":{},"fn":this.program(7, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isCreate) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  return buffer + "		\r\n	</form>\r\n</div>";
},"useData":true});

this["JST"]["partials/user.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<div class=\"container\">\r\n	<div class=\"row\">\r\n		<div class=\"col-md-2\">\r\n			<button class=\"btn btn-primary create\">Create</button>\r\n		</div>\r\n	</div>\r\n	<div class=\"row\">\r\n		<div class=\"col-md-12\">\r\n			<table data-toggle=\"table\" data-cache=\"false\" class=\"bootstrap-table\" data-sort-name=\"userName\">\r\n			    <thead>\r\n			        <tr>			            \r\n			        	<th  data-field=\"userName\" data-sortable=\"true\" data-align=\"left\">User Name</th>\r\n			            <th  data-field=\"firstName\" data-sortable=\"true\" data-align=\"left\">First Name</th>\r\n			            <th  data-field=\"lastName\" data-sortable=\"true\" data-align=\"left\">Last Name</th>			            \r\n			            <th  data-field=\"roleName\" data-sortable=\"true\" data-align=\"left\">Role</th>\r\n			            <th  data-field=\"groupName\" data-sortable=\"true\" data-align=\"left\">Group</th>\r\n			            <th  data-field=\"assocParticipantName\" data-sortable=\"true\" data-align=\"left\">Associate Participant</th>\r\n			            <th  data-field=\"viewCol\" data-align=\"center\"></th>\r\n			        </tr>\r\n			    </thead>\r\n			    <tbody id=\"userListContainer\">\r\n			    	\r\n			    </tbody>\r\n			</table>\r\n		</div>\r\n	</div>\r\n</div>\r\n";
  },"useData":true});

this["JST"]["partials/userDetail.html"] = Handlebars.template({"1":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<a href=\"#user\" class=\"back\"> < back</a>\r\n			</div>\r\n";
  },"3":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<label forName=\"userName\" class=\"control-label\"  required> User Name </label>\r\n				<input class=\"form-control\" name=\"userName\">\r\n			</div>		\r\n";
  },"5":function(depth0,helpers,partials,data) {
  var helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;
  return "			<div class=\"form-group\">\r\n				<label forName=\"userName\" class=\"control-label\"  required> User Name </label>\r\n				<input class=\"form-control\" name=\"userName\" disabled=\"disabled\" value=\""
    + escapeExpression(((helper = (helper = helpers.userName || (depth0 != null ? depth0.userName : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"userName","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n";
},"7":function(depth0,helpers,partials,data) {
  var stack1, helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, lambda=this.lambda;
  return "			<div class=\"form-group\">\r\n				<label forName=\"firstName\" class=\"control-label\" > First Name </label>\r\n				<input class=\"form-control\" name=\"firstName\" disabled=\"disabled\" value=\""
    + escapeExpression(((helper = (helper = helpers.firstName || (depth0 != null ? depth0.firstName : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"firstName","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"lastName\" class=\"control-label\" > Last Name </label>\r\n				<input class=\"form-control\" name=\"lastName\" disabled=\"disabled\" value=\""
    + escapeExpression(((helper = (helper = helpers.lastName || (depth0 != null ? depth0.lastName : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"lastName","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>		\r\n			<div class=\"form-group\">\r\n				<label forName=\"role\" class=\"control-label\" > Role </label>\r\n				<input class=\"form-control\" name=\"role\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.role : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>	\r\n			\r\n			<div class=\"form-group\">\r\n				<label forName=\"group\" class=\"control-label\" > Group </label>\r\n				<input class=\"form-control\" name=\"group\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.group : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>	\r\n			<div class=\"form-group\">\r\n				<label forName=\"associatedParticipant\" class=\"control-label\" > Associate Participant </label>\r\n				<input class=\"form-control\" name=\"associatedParticipant\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.associatedParticipant : depth0)) != null ? stack1.userName : stack1), depth0))
    + "\">\r\n			</div>			\r\n";
},"9":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<label forName=\"password\" class=\"control-label\" > Password </label>\r\n				<input class=\"form-control\" name=\"password\" type=\"password\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"firstName\" class=\"control-label\" > First Name </label>\r\n				<input class=\"form-control\" name=\"firstName\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"lastName\" class=\"control-label\" > Last Name </label>\r\n				<input class=\"form-control\" name=\"lastName\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"role\" class=\"control-label\" > Role </label>\r\n				<select class=\"form-control\" name=\"role\"></select>\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"group\" class=\"control-label\" > Group </label>\r\n				<select class=\"form-control\" name=\"group\"></select>							\r\n			</div>	\r\n			<div class=\"form-group\">\r\n				<label forName=\"associatedParticipant\" class=\"control-label\" > Associate Participant </label>\r\n				<select class=\"form-control\" name=\"associatedParticipant\"></select>							\r\n			</div>\r\n			\r\n";
  },"11":function(depth0,helpers,partials,data) {
  var stack1, helper, functionType="function", helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, lambda=this.lambda;
  return "			<div class=\"form-group\">\r\n				<label forName=\"password\" class=\"control-label\" > Password </label>\r\n				<input class=\"form-control\" name=\"password\" type=\"password\" value=\""
    + escapeExpression(((helper = (helper = helpers.password || (depth0 != null ? depth0.password : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"password","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"firstName\" class=\"control-label\" > First Name </label>\r\n				<input class=\"form-control\" name=\"firstName\" value=\""
    + escapeExpression(((helper = (helper = helpers.firstName || (depth0 != null ? depth0.firstName : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"firstName","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"lastName\" class=\"control-label\" > Last Name </label>\r\n				<input class=\"form-control\" name=\"lastName\" value=\""
    + escapeExpression(((helper = (helper = helpers.lastName || (depth0 != null ? depth0.lastName : depth0)) != null ? helper : helperMissing),(typeof helper === functionType ? helper.call(depth0, {"name":"lastName","hash":{},"data":data}) : helper)))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"role\" class=\"control-label\" > Role </label>\r\n				<input class=\"form-control\" name=\"role\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.role : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>\r\n			<div class=\"form-group\">\r\n				<label forName=\"group\" class=\"control-label\" > Group </label>\r\n				<input class=\"form-control\" name=\"group\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.group : depth0)) != null ? stack1.name : stack1), depth0))
    + "\">\r\n			</div>	\r\n			<div class=\"form-group\">\r\n				<label forName=\"associatedParticipant\" class=\"control-label\" > Associate Participant </label>\r\n				<input class=\"form-control\" name=\"associatedParticipant\" disabled=\"disabled\" value=\""
    + escapeExpression(lambda(((stack1 = (depth0 != null ? depth0.associatedParticipant : depth0)) != null ? stack1.userName : stack1), depth0))
    + "\">\r\n			</div>	\r\n";
},"13":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<button class=\"btn btn-primary edit\"> Edit </button>\r\n				<button class=\"btn btn-danger col-sm-offset-1 delete\"> Delete </button>\r\n			</div>\r\n";
  },"15":function(depth0,helpers,partials,data) {
  return "			<div class=\"form-group\">\r\n				<button class=\"btn btn-primary save\" type=\"submit\"> Save </button>\r\n				<button class=\"btn btn-danger col-sm-offset-1 cancel\"> Cancel </button>\r\n			</div>\r\n";
  },"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  var stack1, helper, options, functionType="function", helperMissing=helpers.helperMissing, blockHelperMissing=helpers.blockHelperMissing, buffer = "<div class=\"container\">\r\n	<form role=\"form\">\r\n";
  stack1 = helpers['if'].call(depth0, (depth0 != null ? depth0.isView : depth0), {"name":"if","hash":{},"fn":this.program(1, data),"inverse":this.noop,"data":data});
  if (stack1 != null) { buffer += stack1; }
  stack1 = helpers['if'].call(depth0, (depth0 != null ? depth0.isCreate : depth0), {"name":"if","hash":{},"fn":this.program(3, data),"inverse":this.program(5, data),"data":data});
  if (stack1 != null) { buffer += stack1; }
  buffer += "		\r\n";
  stack1 = ((helper = (helper = helpers.isView || (depth0 != null ? depth0.isView : depth0)) != null ? helper : helperMissing),(options={"name":"isView","hash":{},"fn":this.program(7, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isView) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  stack1 = ((helper = (helper = helpers.isCreate || (depth0 != null ? depth0.isCreate : depth0)) != null ? helper : helperMissing),(options={"name":"isCreate","hash":{},"fn":this.program(9, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isCreate) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  stack1 = ((helper = (helper = helpers.isUpdate || (depth0 != null ? depth0.isUpdate : depth0)) != null ? helper : helperMissing),(options={"name":"isUpdate","hash":{},"fn":this.program(11, data),"inverse":this.noop,"data":data}),(typeof helper === functionType ? helper.call(depth0, options) : helper));
  if (!helpers.isUpdate) { stack1 = blockHelperMissing.call(depth0, stack1, options); }
  if (stack1 != null) { buffer += stack1; }
  stack1 = helpers['if'].call(depth0, (depth0 != null ? depth0.isView : depth0), {"name":"if","hash":{},"fn":this.program(13, data),"inverse":this.program(15, data),"data":data});
  if (stack1 != null) { buffer += stack1; }
  return buffer + "		\r\n	</form>\r\n</div>";
},"useData":true});

this["JST"]["partials/welcomeView.html"] = Handlebars.template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
  return "<div class=\"welcomeView\">\r\n</div>\r\n";
  },"useData":true});

return this["JST"];

});