## TravelBud Restful API 

1. It is a Maven Project based on Spring MVC framework.

### Run postgres container locally 

```
docker pull postgres
docker run --name travelBudDB -e POSTGRES_DB=travelBud -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```
### Creating database 

1. Create the database.

2. Take notes on ${username}/${password}/${databaseName}/${port}/${Url}

### Configuration information (The config file is located in ./src/main/resources/META-INF/)


1. Fill up the unit-db.template and change the env param as per needed for Unit Env or prod-db.template for 
production env.

2. Rename the unit-db.template to unit-db.properties or prod-db.template to prod-db.properties.

```
mvn compile -Dspring.profiles.active=unit
```


###  migration

1. Example : mvn compile flyway:migrate -P unit -Ddb_url= localhost:5432/travelbud_unit -Ddb_password=password -Ddb_username=admin

```
mvn compile flyway:migrate -P unit -Ddb_url=${url} -Ddb_password=${password} -Ddb_username=${username}

```

### Unit Test

```
mvn compile test -Dspring.profiles.active=unit -Daws.region=${us-east-2} -Ddb_url=${url} -Ddb_password=${password} -Ddb_username=${username}

```

### Package Command

```
mvn compile package -Dmaven.test.skip=true -Dhash=${hash}
```
