-- Spring Boot will execute this script every time the app is started.

create table if not exists chickens (
  chicken_id serial primary key,
  name varchar(255) not null
);
