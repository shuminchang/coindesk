CREATE TABLE currency (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

insert into currency (code, name) values ('USD', '美元');
insert into currency (code, name) values ('GBP', '英鎊');
insert into currency (code, name) values ('EUR', '歐元');