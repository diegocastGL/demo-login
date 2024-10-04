create schema "testdb";

use "testdb";

create table if not exists USERS (
    ID int not null AUTO_INCREMENT,
    NAME varchar(100),
    EMAIL varchar(100) not null unique,
    PASSWORD varchar(100) not null,
    ACTIVE boolean not null,
    CREATE_DATE datetime not null,
    LAST_LOGIN datetime not null,
    PRIMARY KEY (ID)
);

create table if not exists PHONES (
    ID int not null AUTO_INCREMENT,
    NUMBER int not null,
    CITY_CODE int not null,
    COUNTRY_CODE varchar(10),
    USER_ID int not null,
    foreign key (USER_ID) references USERS(ID)
);



