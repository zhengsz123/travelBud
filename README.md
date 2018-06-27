### TravelBud Restful API 

It is a Maven Project based on Spring MVC framework.

#### Run postgres container locally 

```
docker pull postgres
docker run --name travelBudDB -e POSTGRES_DB=travelBud -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

#### Unit Test

```
mvn compile test -Dspring.profiles.active=unit 
```

#### Configuration information (The config file is located in ./src/main/resources/META-INF/)

```
mvn compile -Dspring.profiles.active=dev
mvn compile -Dspring.profiles.active=unit
```

#### Package Command

```
mvn compile package -DoutputDirectory=./target
```
