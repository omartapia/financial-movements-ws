delete from persona;
alter table persona auto_increment = 3;
insert into persona(id, nombre, genero, edad, identificacion, direccion, telefono) values(1, "omar", "M", 36, "0502366503", "cotocollao", "0958757699");
insert into persona(id, nombre, genero, edad, identificacion, direccion, telefono) values(2, "paola", "F", 32, "1720114147", "san isidro", "0999926739");