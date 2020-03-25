DELETE FROM trip_users ;
DELETE FROM trip ;

insert into trip(id_trip,poi_id_poi,name,date_trip,time_start,time_end,nb_person,description,promoteur_id_user,city_id_city)
values (1,1,'Cinéma : les tontons ...','25/12/2020','19:10:25','19:10:25',5,'ça flingue !',1,1)


insert into trip_users(trip_id_trip,users_id_user)
values (1,1);
insert into trip_users(trip_id_trip,users_id_user)
values (1,10);
insert into trip_users(trip_id_trip,users_id_user)
values (1,20);




insert into trip(id_trip,poi_id_poi,name,date_trip,time_start,time_end,nb_person,description,promoteur_id_user,city_id_city)
values (2,2,'Théatre : AAAA','25/12/2020','19:10:25','19:10:25',5,'BBBBBB',2,2);

insert into trip_users(trip_id_trip,users_id_user)
values (2,165);
insert into trip_users(trip_id_trip,users_id_user)
values (2,135);
insert into trip_users(trip_id_trip,users_id_user)
values (2,125);



insert into trip(id_trip,poi_id_poi,name,date_trip,time_start,time_end,nb_person,description,promoteur_id_user,city_id_city,age_max,age_min)
values (3,3,'CCCC','25/03/2020','16:10:25','17:10:25',5,'BBBBBB',4,3,60,20);

insert into trip_users(trip_id_trip,users_id_user)
values (3,185);
insert into trip_users(trip_id_trip,users_id_user)
values (3,5);
insert into trip_users(trip_id_trip,users_id_user)
values (3,25);
