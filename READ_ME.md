Training project with technologies:
Java, Spring Boot, Lombok, Postgresql, MongoDB, Redis, Kafka, Splunk, Docker


TODO:
- Define how to create schemas in postgresql and mongo before running apps
- Move parameters from app files to env variables
- Add user management + auth
- Add Jenkins CICD
- Add Swagger or OpenApi
- Add code analyzer (Jacoco or Sonar)
- Add unit tests
- Add integration tests

**Splunk** 
http://localhost:8000/
Creds: admin / yourpassword

How to configure:
1. Create index in splunk (Uncheck "Enable SSL" )
   https://www.youtube.com/watch?v=VO20SgiTTOU&ab_channel=JavaTechie
2. Exclude loggers from Spring Boot starters
3. Add log4j2 and splunk-logging dependencies
4. Add log4j2-spring.xml file