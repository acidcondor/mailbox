create table ADDRESSES (ID bigint not null auto_increment, STREET varchar(255), CITY varchar(255), STATE varchar(255), ZIP_CODE integer, UNIT varchar(255), COUNTRY varchar(255), MAILBOX_ADDRESS varchar(255), primary key (ID)) ENGINE=InnoDB;
create table MAILBOXES (ID bigint not null auto_increment, ADDRESS_ID bigint not null, NUMBER integer default 0 not null, INTERNET_ADDRESS varchar(255), primary key (ID)) ENGINE=InnoDB;
create table PARCELS (ID bigint not null auto_increment, RECIPIENT_ID bigint, STATUS varchar(255) default POSTED, primary key (ID)) ENGINE=InnoDB;
create table PERSONS (ID bigint not null auto_increment, FIRSTNAME varchar(255), MIDDLENAME varchar(255), LASTNAME varchar(255), PHONE_NUMBER varchar(255), PUBLIC_KEY varchar(255), primary key (ID)) ENGINE=InnoDB;
create table PERSON_MAILBOXES (ID bigint not null auto_increment, PERSON_ID bigint not null, MAILBOX_ID bigint not null, IS_OWNER bit, START_DATE date, END_DATE date, primary key (ID)) ENGINE=InnoDB;
create table POST_OFFICES (ID bigint not null auto_increment, STREET varchar(255), CITY varchar(255), STATE varchar(255), COUNTRY varchar(255), ZIP_CODE integer, primary key (ID)) ENGINE=InnoDB;
create table TRANSACTIONS (ID bigint not null auto_increment, PARCEL_ID bigint, MAILBOX_ID bigint, DATE date, primary key (ID)) ENGINE=InnoDB;
alter table MAILBOXES add constraint UK_hqarcyy8bru1rkwjb2c2gici8  unique (ADDRESS_ID, NUMBER);
alter table MAILBOXES add constraint FK_9vixpgb5glwc1jtt5ym14gvvu foreign key (ADDRESS_ID) references ADDRESSES (ID);
alter table PARCELS add constraint FK_jxjuenbt0y9oyrk6o5h5ftuep foreign key (RECIPIENT_ID) references PERSONS (ID);
alter table PERSON_MAILBOXES add constraint FK_s06pxm697rr3nl0q7j7m3rqu1 foreign key (PERSON_ID) references PERSONS (ID);
alter table PERSON_MAILBOXES add constraint FK_r3k42goqln8dwh4mkedst3ane foreign key (MAILBOX_ID) references MAILBOXES (ID);
alter table TRANSACTIONS add constraint FK_or8bkvv0lsxxpo0ytu99fxtye foreign key (PARCEL_ID) references PARCELS (ID);
alter table TRANSACTIONS add constraint FK_k18aw1pkv5658pu7hp40j6cwh foreign key (MAILBOX_ID) references MAILBOXES (ID);
