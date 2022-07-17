wsl --shutdown
netsh winsock reset

docker compose -f docker-compose-graylog.yaml up -d

gradle quarkusDev

taskkill /F /PID 1242

- Successfully created Quarkus Demo App
- Successfully integrated GELF log
- Successfully test log end point is created & executed well
- Successfully ran the Graylog in the Docker
- Successfully able to view the logs in the Graylog console
Tomorrow i will start working on File Log handler for Quarkus Demo App

- Successfully configured File logging and it is working as expected
- Successfully configured File log rotation and it is working as expected
- Successfully tested console log, file log and gelf log simultaneously
I am going explore how to detect graylog is down
Switch btw Greylog <> filelog & vice-versa

,