create table person_info
(
    id   varchar(20) not null
        primary key,
    name varchar(20) not null,
    sex  varchar(2)  not null,
    age  int         not null
);

create index idx_name
    on person_info (name);

INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('10021', '张三3308', '男', 41);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('21243423424', 'zhangsan', '男', 13);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('234', '李四', '男', 23);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('333', '张三', '男', 34);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('534222', '赵六', '男', 45);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('56745', '王五', '女', 18);
INSERT INTO `test-ssm`.person_info (id, name, sex, age) VALUES ('9898780', '陈七', '男', 79);