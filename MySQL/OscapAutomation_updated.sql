create database OscapAutomation;
use OscapAutomation;

create table multiScantbl(
scanID     int(35) Not Null auto_increment,
firstname  varchar(65)  Not  Null,
lastname   varchar(65)  Not Null,                
 nodename   varchar(65)  Not Null,
scandate   varchar(65)  Not Null,
scantime   varchar(65)  Not Null,
scanstatus varchar(120) Not Null,
Primary key(scanID)); 
 Alter table multiScantbl auto_increment 55990;


create table hostListTbl(
hostID int(15) Not Null auto_increment,
nodename varchar(25) Not Null,
Primary key(hostID));

Grant All Privileges On OscapAutomation.* To Openscap@localhost Identified By 'Opensc@p4321';

use OscapAutomation;

insert into hostListTbl values(0,    'sben2d016');
insert into hostListTbl values(0,    'inen1a076');
insert into hostListTbl values(0,    'inen2a043');
insert into hostListTbl values(0,    'inen3a062'); 
insert into hostListTbl values(0,    'inen3a060'); 
insert into hostListTbl values(0,    'inen1mer01');
insert into hostListTbl values(0,    'INEN2T015');
insert into hostListTbl values(0,    'INEN1T011'); 
insert into hostListTbl values(0,    'INEN1T014');
insert into hostListTbl values(0,    'inen1a042');
insert into hostListTbl values(0,    'inen1mer02');
insert into hostListTbl values(0,    'inen3a061');
insert into hostListTbl values(0,    'inen1mer03');
insert into hostListTbl values(0,    'TSDEL1W001');
insert into hostListTbl values(0,    'IMDEL2A001');
insert into hostListTbl values(0,    'PDQTS1W003');
insert into hostListTbl values(0,    'itqts2w002');
insert into hostListTbl values(0,    'IMDEL2W001');
insert into hostListTbl values(0,    'inen1d036');
insert into hostListTbl values(0,    'sben1a003');
insert into hostListTbl values(0,    'itqts2a004');
insert into hostListTbl values(0,    'sben1a021');
insert into hostListTbl values(0,    'tsqts1a001');
insert into hostListTbl values(0,    'PDQTS2W002');
insert into hostListTbl values(0,    'tsen2w002');
insert into hostListTbl values(0,    'inen1w028');
insert into hostListTbl values(0,    'inen2d037');
insert into hostListTbl values(0,    'tsen1a001');
insert into hostListTbl values(0,    'inen1a032');
insert into hostListTbl values(0,    'dvqts1w001');
insert into hostListTbl values(0,    'tsqts2w002');
insert into hostListTbl values(0,    'tsen2a004');
insert into hostListTbl values(0,    'inen1a030');
insert into hostListTbl values(0,    'itqts1w001');
insert into hostListTbl values(0,    'itqts1a003');
insert into hostListTbl values(0,    'dvsbd2a004');