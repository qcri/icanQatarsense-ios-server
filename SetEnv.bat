@ECHO off

:: Project_Details
SET project_dir=E:\QCRI\Q-Sense-Server
SET jars_mapping=%project_dir%\lib
::

:: Java_Details
SET	JAVA_HOME=C:\Program Files\Java\jdk1.7.0_71
SET PROFILE=dev
SET	CLASSPATH=%JAVA_HOME%\lib
::

:: Tomcat_Details
SET TOMCAT_HOME=E:\apache-tomcat-7.0.55
SET TOMCAT_WEBAPPS=%TOMCAT_HOME%/webapps
SET TOMCAT_LIB=%TOMCAT_HOME%\lib
SET TOMCAT_BIN=%TOMCAT_HOME%\bin
:: 	

:: Ant_Details
SET ANT_HOME=C:\Program Files\apache-ant-1.9.7
::

::
SET PATH=%PATH%;%ANT_HOME%\bin;%JAVA_HOME%\bin;
SET deploy_dir=%TOMCAT_WEBAPPS%
SET TOMCAT_USERNAME=root
SET	TOMCAT_PASSWORD=root

::

@ECHO ON