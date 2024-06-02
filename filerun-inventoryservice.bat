@echo off
set API_LISTENING_PORT=8082
set DB_HOST=localhost
set DB_NAME=employee
set DB_PASSWORD=meveo
set DB_PORT=5432
set DB_USERNAME=meveo


java -jar inventoryservice/target/inventoryservice-1.0-SNAPSHOT.jar
pause
