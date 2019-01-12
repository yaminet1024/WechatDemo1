set global time_zone = '+8:00';

create table user(
  `id` bigint(20) not null comment '用户id,手机号码',
  `nick_name` varchar(255) not null,
  `password` varchar(322) default null comment '加密密码',
  `salt` varchar(10) default null,
  `head_pic` varchar(128) default null comment '头像链接',
  `register_date` datetime default null comment '注册时间',
  `last_login_date` datetime default null comment '上次登陆时间',
  `login_count` int(11) default '0' comment '登陆次数',
  `user_sex` varchar(3) default '未知' comment '性别',
  primary key (`id`)
)engine = InnoDB default CHARSET utf8MB4
