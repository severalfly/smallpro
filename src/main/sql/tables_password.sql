create database test

select * from image

select * from user
create table user(
        id int auto_increment,
        username varchar(50),
        password varchar(100),
   primary key(id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8

create table passwd_record(
   id bigint,
   owner_id int,
   uname varchar(50),
   login_name varchar(50),
   e_mail varchar(50), 
   passwd varchar(100),
   create_time datetime, 
   update_time datetime,
   primary key(id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8

select * from passwd_record



CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8