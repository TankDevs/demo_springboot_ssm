create table account
(
    accoun_id varchar(20) not null
        primary key,
    money     int(10)     null,
    person_id varchar(20) not null,
    constraint person_account_pkey
        foreign key (person_id) references person_info (id)
);

INSERT INTO `test-ssm`.account (accoun_id, money, person_id) VALUES ('23211', 10, '234');
INSERT INTO `test-ssm`.account (accoun_id, money, person_id) VALUES ('2345', 1000, '10021');
INSERT INTO `test-ssm`.account (accoun_id, money, person_id) VALUES ('768', 234, '10021');