# Momentum

An api to support Agile project management.

> Table name User is reserved by H2 database hence use some other name

## Mysql docker container

- To create a mysql docker container use the following command

```commandline
docker run -d -p 3306:3306 -v my_sql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=momentumDb -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin --name mysql mysql:latest
```
Checking details on mysql container

- To log in to mysql container use command `docker exec -it -u root mysql /bin/bash`
- use `mysql -U admin -p` to login as admin user prompts for password provide `admin` if you are using above command
- After logging in to get list of databases `show databases;`
- to log on to a database `use database <database_name>`
- To list tables `show tables;`

> Additional information on mysql commands can be found at https://phoenixnap.com/kb/mysql-commands-cheat-sheet

## Creating a postgresql docker container

```commandline
docker run -d -p 5432:5432 -v postgres:/var/lib/postgresql/data -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=momentumdb --name postgres postgres
```

Checking details in postgres container
- To log in to postgres container use command `docker exec -it -u root postgres /bin/bash`
- use `su postgres` to become postgres user.
- use `psql -U admin -d momentumdb` to login as admin user to postgres and access momentumdb.
- to get list of databases `\l`
- to see list of tables '\dt'

> Additional information on psql commands can be found at https://www.geeksforgeeks.org/postgresql-psql-commands/
