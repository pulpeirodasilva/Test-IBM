-- Se usarán las siguientes fechas a modo de ejemplo
-- para rellenar la columna 'fecha_alta' de la tabla 'proveedores':
-- 03/12/2014
-- 05/10/2015
-- 20/07/2018

create table proveedores(
	id_proveedor NUMBER(1),
	nombre VARCHAR2(20),
	fecha_alta DATE,
	id_cliente NUMBER(1),
    PRIMARY KEY (id_proveedor)	
);

insert into proveedores values (1, 'Coca-cola', date_format('2014-12-03', '%d %M %Y'), 5);
insert into proveedores values (2, 'Pepsi'    , date_format('2015-10-05', '%d %M %Y'), 5);
insert into proveedores values (3, 'Redbull'  , date_format('2018-07-20', '%d %M %Y'), 6);
