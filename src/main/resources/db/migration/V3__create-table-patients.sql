create table patients
(

    id             bigint       not null auto_increment,
    nome           varchar(100) not null,
    email          varchar(100) not null unique,
    phone          varchar(20)  not null unique,
    cpf            varchar(14)  not null,
    street_address varchar(100) not null,
    neighborhood   varchar(100) not null,
    cep            varchar(9)   not null,
    city           varchar(100) not null,
    uf             char(2)      not null,
    number         varchar(20),
    complement     varchar(100),

    primary key (id)

);

