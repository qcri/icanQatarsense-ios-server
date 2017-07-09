module.exports = function(grunt) {

  /*
   * https://github.com/gruntjs/grunt/wiki/Configuring-tasks
   */
  grunt.initConfig({
	pkg: grunt.file.readJSON('package.json'),
	clean: ['dist'],	
    "handlebars": {
      compile: {
        options: {
          amd: true
        },
        src: ["partials/**/*.html"],
        dest: "js/precompiled.handlebars.js"
      }
    },
    uglify: {
        options: {
            banner: '<%= banner %>'
        },
        my_target: {
            files: {
                'dist/<%= pkg.name %>.min.js': ['src/<%=pkg.name %>.js']
            }
        }
    },
    watch: {
    	options: {
    	      livereload: true,
    	},
        //scripts: {
        //  files: ['js/**/*.js'],
        //  task: ['concat, uglify']
        //},
        css: {
            files: 'stylesheets/**/*.scss',
            tasks: ['sass']
        },
        templates: {
        	files: 'partials/**/*.*',
        	tasks: ['handlebars']
        }
    },
    sass: {
    	options: {
    		noCache: true
    	},
        dist: {
          files: [{
            //expand: true,
        	  'stylesheets/css/common.css': 'stylesheets/scss/common.scss'
          }]
        }
    }

  });

  // Requires the needed plugin
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-handlebars');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-sass');
  
  grunt.registerTask('default', ['sass','handlebars','watch']);
};