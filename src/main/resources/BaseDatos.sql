delete from movimiento;
alter table movimiento auto_increment = 0;
delete from cuenta;
alter table cuenta auto_increment = 0;
delete from cliente;
alter table cliente auto_increment = 0;
insert into cliente(id, nombres, contrasena, estado, genero, edad, identificacion, direccion, telefono)
values(1, "omar", "1234", true, "M", 36, "0502366503", "cotocollao", "0958757699");
insert into cliente(id, nombres, contrasena, estado, genero, edad, identificacion, direccion, telefono)
values(2, "paola", "1234", true, "F", 32, "1720114147", "san isidro", "0999926739");