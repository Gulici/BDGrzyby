## Requirements:
Create MySQL container in Docker
```
docker run --name BDProj -e MYSQL_ROOT_PASSWORD=toor -p 5001:3306 -d mysql:8.0.39
```
Add schema to database named "Grzyby". 
```
create schema Grzyby;
```
## Run
Application have interface running on Tomcat web server on port 8080 with context path '/'.\
To open it simply use url addres 'http://localhost:8080/' in internet browser.
