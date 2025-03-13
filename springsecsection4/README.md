- docker
```dockerfile
docker run -p 3306:3306 --name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eazybank -d mysql
```
- ddl
```mysql
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);
```