#!/bin/bash
# Project_Details
export project_dir=/home/ec2-user/Q-Sense-Server
export jars_mapping=$project_dir/lib
#

# Java_Details
export JAVA_HOME=/usr/lib/jvm/java
export CLASSPATH=$JAVA_HOME/bin
#

# Tomcat_Details
export TOMCAT_HOME=/usr/share/tomcat7
export TOMCAT_WEBAPPS=$TOMCAT_HOME/webapps
export TOMCAT_LIB=$TOMCAT_HOME/lib
export TOMCAT_BIN=$TOMCAT_HOME/bin
#

# Ant_Details
export ANT_HOME=/usr/share/ant/
#

#
export PATH=$PATH:$ANT_HOME/bin:$JAVA_HOME/bin:$TOMCAT_BIN:$TOMCAT_LIB
export deploy_dir=$TOMCAT_WEBAPPS
export TOMCAT_USERNAME=admin
export TOMCAT_PASSWORD=password

export PROFILE=dev
#
