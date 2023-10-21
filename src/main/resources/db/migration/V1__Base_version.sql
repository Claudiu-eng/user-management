create table _user
(
    id         binary(16)   not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    constraint UK_k11y3pdtsrjgy8w9b6q4bjwrx
        unique (email)
);

create table user_role
(
    user_id binary(16)             not null,
    role    enum ('ADMIN', 'USER') null,
    constraint FKniaqoclrvx138sjw9hsollqav
        foreign key (user_id) references _user (id)
);