create table tapm.demand_info
(
    demand_id int auto_increment
        primary key,
    title     varchar(20)   not null,
    project   varchar(20)   not null,
    ctime     varchar(30)   not null,
    ddl       varchar(30)   not null,
    cer       varchar(20)   not null,
    doer      varchar(20)   not null,
    priority  int default 1 not null,
    status    int default 1 not null,
    address   varchar(50)   null,
    docu      varchar(200)  null
);

create table tapm.log_info
(
    log_id    int auto_increment
        primary key,
    demand_id int         not null,
    project   varchar(20) not null,
    ctime     varchar(30) not null,
    cer       varchar(20) not null,
    commit    varchar(50) not null
);

create table tapm.user_info
(
    username varchar(20)   not null,
    password varchar(20)   not null,
    email    varchar(30)   not null,
    identity int default 1 not null,
    constraint user_info_account_uindex
        unique (username)
);

