alter table patients
    modify column cpf varchar(100) not null unique;