# travelBud Restful API 

It is a Maven Project based on Spring MVC framework.

1- Docker set up :

Docker pull postgres
docker run --name travelBudDB -e POSTGRES_DB=travelBud -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres

2-How to do Unit Test

mvn compile test -Dspring.profiles.active=unit 

3. Different Environment Variable :

Dev : mvn compile -Dspring.profiles.active=dev
Unit: mvn compile -Dspring.profiles.active=unit

4. Mvn Version :

10.13.3

5. Package :

mvn compile package -DoutputDirectory=../TravelBud2/target


