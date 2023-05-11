alter table patients
    add column active tinyint;

update patients
set active = 1;

alter table patients
    modify active tinyint not null;