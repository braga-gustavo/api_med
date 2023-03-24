alter table doctors
    add column active tinyint;

update doctors
set active = 1;

alter table doctors
    modify active tinyint not null;