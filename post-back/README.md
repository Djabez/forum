<p align="center">
	<img src="https://img.shields.io/badge/JDK-1.8+-orange">
	<img src="https://img.shields.io/badge/SpringBoot-2.2.7.RELEASE-brightgreen">
	<img src="https://img.shields.io/badge/MyBatisPlus-3.5.5-red">
	<img src="https://img.shields.io/badge/Vue-2.6.11-brightgreen">
	<img src="https://img.shields.io/badge/license-MIT-blue">	
</p>





## Backend

- Core framework: Spring Boot
- Security framework: shiro
- Token authentication : jwt
- persistence framework: MyBatisPlus
- java version: JDK8





## Backend administration page
- Login function
- Registration function
- Change password function
- Article management.
  - Article Posting
  - Article Editing
  - Article Delete
- Comment function
- Share Function






## Project Quick Start
1. execute console.sql to create the database
2. turn on redis
3. modify yml configuration in idea, then run backend project
4. install npm and start the front-end page in idea


## Deploy the project online
1. use docker-compose to orchestrate
2. need nginx, redis, backend project, mysql
3. configure the certificate, you can apply for a year of free certificates from Tencent, Ali
4. nginx reverse proxy, https requests through http to the back-end container, to avoid interface exposure and http insecurity


