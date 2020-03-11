--liquibase formatted sql
/*
INSERT INTO region (id_region, name) VALUES (1,'Region Parisienne');
INSERT INTO region (id_region, name) VALUES (2,'Bourgogne');

INSERT INTO department (id_department, name, region_id_region) VALUES (1,'Paris',1);
INSERT INTO department (id_department, name, region_id_region) VALUES (2,'Essonne',1);
INSERT INTO department (id_department, name, region_id_region) VALUES (3,'Saone et loire',2);

INSERT INTO city (id_city, latitude, longitude, name, postal_code, department_id_department )
VALUES (1, 1.0, 1.0, 'Paris', '1', 1);
INSERT INTO city (id_city, latitude, longitude, name, postal_code, department_id_department )
VALUES (2, 1.0, 1.0, 'Dourdan', '2', 2);


INSERT INTO poi (id_poi, name) VALUES (1,'Cinéma');
INSERT INTO poi (id_poi, name) VALUES (2,'Théatre');
*/
