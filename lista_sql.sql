create table userposjava (
 id bigint not null,
 nome varchar(255),
 email varchar(255),
 excluido varchar(1) default null,
 constraint user_pk primary key (id)
);

create sequence user_sequence increment 1 minvalue 1 maxvalue 9999999 start 1;

alter table userposjava alter column id set default nextval('user_sequence'::regclass);

alter table userposjava add unique(id);

insert into userposjava (nome, email) values ('andre','andre@gmail.com'),
(2,'roberto','roberto@gmail.com'),
(3,'bronca','bronca@gmail.com');

--------------------------------------------------------------------------------------------------------
create table telefoneuser(
 id bigint not null,
 numero varchar(255) not null,
 tipo varchar(255) not null,
 usuariopessoa bigint not null,
 constraint telefone_id primary key(id)
);

alter table telefoneuser add foreign key (usuariopessoa) references userposjava(id);

create sequence telefone_sequence increment 1 minvalue 1 maxvalue 9999999 start 1;

alter table telefoneuser alter column id set default nextval('telefone_sequence'::regclass);

alter table telefoneuser add unique(id);

insert into telefoneuser (numero,tipo,usuariopessoa) values
('(45) 99947-5166','celular tim',1),
('(45) 99784-2144','sei lá tim',2),
('(44) 99983-9935','sei lá tim',4),
('(44) 3528-5173','sei lá comercial',1);
--------------------------------------------------------------------------------------------------------