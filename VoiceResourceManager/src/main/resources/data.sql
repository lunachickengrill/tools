insert into softswitch(oid,create_date,last_modified,description, name, status, switch_id)
values
(SEQ_OID.nextval,'2018-09-25','2018-09-25','blabla', 'cs2k','ONLINE','1'),
(SEQ_OID.nextval,'2018-09-25','2018-09-25','blabla', 'ngcp','OFFLINE','2');


insert into resource
select seq_oid.nextval,'2018-09-25 00:00:00','2018-09-25 00:00:00', 'SS 00 0 00 01','1','FREE', oid
from softswitch where switch_id=1;

insert into resource
select seq_oid.nextval,'2018-09-25 00:00:00','2018-09-25 00:00:00', 'SS 00 0 00 02','1','FREE', oid
from softswitch where switch_id=1;

insert into resource
select seq_oid.nextval,'2018-09-25 00:00:00','2018-09-25 00:00:00', 'SS 00 0 00 03','1','FREE', oid
from softswitch where switch_id=1;

insert into resource
select seq_oid.nextval,'2018-09-25 00:00:00','2018-09-25 00:00:00', 'SS 00 0 00 04','1','FREE', oid
from softswitch where switch_id=1;

insert into resource
select seq_oid.nextval,'2018-09-25 00:00:00','2018-09-25 00:00:00', 'SS 00 0 00 05','1','FREE', oid
from softswitch where switch_id=1;