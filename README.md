**ANT_VERSION:** 1.8.3 with ivy.jar included in lib folder. (Hint: Download ivy.jar and copy in ant lib folder)

**JDK_VERSION:** jdk1.7.0_71

**TOMCAT_VERSION:** 7

####For Deployment on Windows Platforms:

a) Pull the latest code

b) Create DB in MySQL Database: `qsense_db`

c) Change SetEnv.bat according to your location of Tomcat, JDK and ant for PROFILE= (e.g. sandbox, dev, demo)

d) Configure src/main/resources/\<PROFILE>.application.properties according to your system configuration.

e) In MS-DOS execute: `SetEnv.bat`

f) In the same MS-DOS execute `ant clean_deploy`

####For Deployment on Linux/Mac OS Platforms OR AWS Server:

a) Pull the latest code

b) Create DB in MySQL Database: `qsense_db`

c) Change setenv.sh according to the location of Tomcat, JDK and ant on your system. and for PROFILE= (e.g. sandbox, dev, demo)

d) Configure src/main/resources/\<PROFILE>.application.properties according to your system configuration.

e) On terminal execute: `source setenv.sh`

f) On the same terminal execute `ant deploy_prod`


####For the web-ui

1.	Open command prompt in the directory E:\Q-Sense-Server\src\main\webapp\public

2.	Run command "bower install npm"

3.	Close command prompt

4.	Again open command prompt in the directory E:\Q-Sense-Server\src\main\webapp\public

5.	Run command "npm install"

**If you make any changes in the .html files then**

1.	open open command prompt in the directory E:\Q-Sense-Server\src\main\webapp\public
2. 	Run command "grunt" 
Don't close the window...It'll automatically create the handlebar file as it sees any changes in html files
