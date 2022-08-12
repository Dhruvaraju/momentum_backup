# Momentum

An api to support Agile project management.

> Table name User is reserved by H2 database hence use some other name

## Mysql docker container

- To create a mysql docker container use the following command

```commandline
docker run -d -p 3306:3306 -v my_sql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=momentumDb -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin --name mysql mysql:latest
```

## Creating a postgresql docker container

```commandline

```
