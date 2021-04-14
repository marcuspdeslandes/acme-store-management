# POSTGRES

#Run postgres locally
docker run --rm --name psqldeveloper -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

#Create a database
CREATE DATABASE acmestore;

# SWAGGER 

LOCAL:

http://localhost:8082/swagger-ui.html
