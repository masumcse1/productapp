@echo off
set API_LISTENING_PORT=8084
set DB_HOST=localhost
set DB_NAME=employee
set DB_PASSWORD=meveo
set DB_PORT=5432
set DB_USERNAME=meveo

rem java -jar target/your-app-name.jar
java -jar securityservice/target/securityservice-1.0-SNAPSHOT.jar
pause
