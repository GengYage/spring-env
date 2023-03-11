### SpringBoot Tutorial
学习SpringBoot的例子

### DataBase
```mysql
create table if not exists users
(
    id        varchar(32) primary key not null,
    user_name varchar(32)        not null,
    age       int                null,
    unique key uniq_user_id (id(32)),
    index idx_user_name (user_name)
);
```
### Example
1. 多线程共享同一个事务(真正的共享,非多个事务阻塞IO)
