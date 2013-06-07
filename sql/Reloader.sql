drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;

drop table if exists components cascade;
create table components (
        id int8 primary key,
        name varchar,
        type varchar,
        amount numeric(10, 2),
        total_cost numeric(10, 2),
	create_date timestamp,
	update_date timestamp
);

drop table if exists batches cascade;
create table batches (
        id int8 primary key,
        description varchar,
        bullet_id int8,
        powder_id int8,
        powder_charge numeric(10, 2),
        primer_id int8,
        brass_id int8,
	count int8,
        create_date timestamp,
        update_date timestamp
);

drop table if exists users cascade;
create table users (
	id int8 primary key,
	create_date timestamp,
	update_date timestamp,
	username varchar,
	password varchar
);

insert into users (id, create_date, update_date, username, password) values
(nextVal('hibernate_sequence'), now(), now(), 'testuser', 'testpass');

insert into components (id, name, type, amount, total_cost, create_date, update_date, user_id) values
(nextVal('hibernate_sequence'), 'CCI Large Rifle Primers', 'Primer', 5000, 182.00, now(), now(), 1),
(nextVal('hibernate_sequence'), 'IMR 4094', 'Powder', 42000, 161.9, now(), now(), 1),
(nextVal('hibernate_sequence'), '.308 Caliber 150gr Speer GS SP', 'Bullet', 500, 0, now(), now(), 1),
(nextVal('hibernate_sequence'), 'Once Fired 30-06', 'Brass', 100, 0, now(), now(), 1),
(nextVal('hibernate_sequence'), 'Once Fired .40', 'Brass', 100, 0, now(), now(), 1),
(nextVal('hibernate_sequence'), 'Winchester Small Pistol Primer', 'Primer', 4999, 211.00, now(), now(), 1),
(nextVal('hibernate_sequence'), 'FMP 165gr .40 Plated', 'Bullet', 750, 107.94, now(), now(), 1),
(nextVal('hibernate_sequence'), 'Hodgdon HP-38', 'Powder', 56000, 128.35, now(), now(), 1);

insert into batches (id, description, bullet_id, powder_id, powder_charge, primer_id, brass_id, count, create_date, update_date, user_id) values 
(nextVal('hibernate_sequence'), '150 Gr Speer RN GS Test 1', 3, 2, 48, 1, 4, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), '150 Gr Speer RN GS Test 2', 3, 2, 49, 1, 4, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), '150 Gr Speer RN GS Test 3', 3, 2, 50, 1, 4, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), '150 Gr Speer RN GS Test 4', 3, 2, 51, 1, 4, 10, now(), now(), 1),

(nextVal('hibernate_sequence'), 'First Handgun Test 4.7', 7, 8, 4.7, 6, 5, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), 'First Handgun Test 4.8', 7, 8, 4.8, 6, 5, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), 'First Handgun Test 4.9', 7, 8, 4.9, 6, 5, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), 'First Handgun Test 5.0', 7, 8, 5.0, 6, 5, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), 'First Handgun Test 5.1', 7, 8, 5.1, 6, 5, 10, now(), now(), 1),
(nextVal('hibernate_sequence'), 'Plinking Round Handgun', 7, 8, 4.9, 6, 5, 59, now(), now(), 1);

----- reporting SQL -------
select id, description, primer_cost, brass_Cost, powder_cost, bullet_cost,
(primer_cost + brass_cost + powder_cost + bullet_cost) as total_cost
from (
select 
rs.id, rs.description,
total(primer.total_cost / primer.amount, 4) as primer_cost,
total(brass.total_cost / brass.amount, 4) as brass_cost,
total(rs.powder_charge * (powder.total_cost / powder.amount), 4) as powder_cost,
total(bullet.total_cost / bullet.amount, 4) as bullet_cost
from rounds rs
inner join components bullet on bullet.id = rs.bullet_id
inner join components powder on powder.id = rs.powder_id
inner join components primer on primer.id = rs.primer_id
inner join components brass on brass.id = rs.brass_id
) as t1 ;


-- supplies left
select s1.id, s1.name, s1.type, s1.total_cost, s1.amount, (s1.amount - t1.total) as remaining
from
components s1
inner join
(
select bullet_id as supply_id, count(*) as total from rounds group by bullet_id
union
select powder_id, sum(powder_charge) as total from rounds group by powder_id
union
select brass_id, count(*) as total from rounds group by brass_id
union
select primer_id, count(*) as total from rounds group by primer_id
) as t1 on t1.supply_id = s1.id
order by type;
