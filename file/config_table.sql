use hjk;
-- 表信息

-- 工程信息
-- project name version groupId artifactId root_dir dependencies
--      dependency name group_id, version

create table data_base (
  id int not null auto_increment comment '主键ID',
  type varchar(16) not null default 'MySql' comment '数据库类型, Mysql PostgreSQL',
  name varchar(32) not null comment '数据库名称',
  user_name varchar(32) not null comment '用户名',
  password varchar(32) comment '密码',
  host varchar(32) comment '主机名或IP',
  port varchar(32) comment '端口号',
  char_set varchar(32) comment '字符类型',
  user_id bigint not null default 1 comment '所属人员',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库信息';

create table table_info (
  id int not null auto_increment comment '主键ID',
  name varchar(32) not null comment '表名',
  label varchar(32) comment '前端展示名称',
  engine varchar(32) comment '引擎 InnoDb',
  charset varchar(16) comment '数据类型',
  comment varchar(64) comment '备注',
  cache tinyint comment '是否需要缓存',
  ret_primary tinyint comment '是否返回ID',
  au_show tinyint comment '数据创建(add)修改(update)展示方式 row 行内修改 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面',
  detail_show tinyint comment '数据查看展示方式 null 不用展示 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面',
  db_id int not null comment '所属数据库',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表数据信息';

-- 表索引信息
create table table_index (
  id int not null auto_increment comment '主键ID',
  table_id int not null comment '所属表ID',
  name varchar(32) comment '索引名称',
  type varchar(16) not null default 'KEY' comment '索引类型: key, primary key, unique key',
  db_id bigint not null comment '所属数据库',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表的索引信息';

-- 表索引的字段信息
create table index_column (
  id int not null auto_increment comment '主键ID',
  idx_id int not null comment '索引Id',
  col_name varchar(32) not null comment '字段名称',
  data_type int not null comment '字段数据类型',
  sort int not null default 0 comment '顺序',
  table_id int not null comment '表ID',
  db_id int not null comment '所属数据库',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表的索引字段信息';

-- 表字段信息
create table table_column(
  id int not null auto_increment comment '主键ID',
  table_id int not null comment '所属表ID，不用名字，有重复',
  db_id int not null comment '所属数据库',
  name varchar(32) not null comment '字段名',
  label varchar(16) comment '展示名称，考虑到comment中内容比较丰富，增加此字段',
  data_type int not null comment '数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)',
  data_length int not null default 0 comment '字段数据的长度，一般用于字符串和数值型，varchar(10), int(11)',
  sort int default 0 comment '展示顺序',
  not_null tinyint default 0 comment '是否空 0 可以为空 1 not null',
  auto_increment tinyint default 0 comment '是否自增',
  default_value varchar(8) default '' comment '默认值',
  comment varchar(64) comment '备注，支持原生',
  edit_element int default 1 comment '页面编辑使用元素 默认使用input',
  data int default 1 comment '(枚举型数据)选择数据，默认为空',
  data_from tinyint comment '数据来源 1 空 2 配置 3 查询数据表',
  add_type tinyint comment '新增数据时传入方式 1 前端传入，2 后台默认, 从dta字段中取',
  update_type tinyint comment '是否可以update 0 不需要update 1 可以',
  query_type tinyint comment '查询时计算方式(在分页查询时，使用哪种计算方式 EQ: =, GT: >, LT:<, NE: !=, GE: >=, LE: <=, LK: like {}%, LA: like %{}%, NN: not null)',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表的字段信息';

-- 表对应的方法
create table table_method (
  id int not null auto_increment comment '主键ID',
  name varchar(32) not null comment '方法名称 get, find, save, update, remove, total, pagination',
  request_type varchar(8) default 'POST' comment '方法的调用方式method：POST, GET, PUT, DELETE',
  path_variable boolean default false comment '是否需要在路径中传入参数',
  table_id int not null comment '表ID',
  db_id int not null comment '所属数据库',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表生成的对象下的方法信息';

-- 数据字典信息
create table dict_info (
  id int not null auto_increment comment '主键ID',
  name int not null comment '字典名称',
  type int not null default 1 comment '数据展示方式: 1 list 2 map',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典信息';

-- 数据字典配置信息
create table dict_config (
  id int not null auto_increment comment '主键ID',
  value varchar(32) not null comment '值',
  type tinyint not null comment '数据类型',
  label varchar(32) not null comment '展示内容',
  sort int not null default 0 comment '顺序',
  parent_id int not null default 0 comment '父节点',
  group_id int not null default 0 comment '根节点',
  user_id bigint not null default 1 comment '所属用户',
  create_time datetime default current_timestamp comment '创建时间',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典配置信息';

CREATE TABLE user_account (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '帐号ID',
  user_name varchar(16) DEFAULT NULL COMMENT '登录用户名',
  password varchar(32) DEFAULT NULL COMMENT '登录密码',
  salt varchar(6) DEFAULT NULL COMMENT '盐值',
  role_id int(11) DEFAULT NULL COMMENT '角色',
  user_id bigint(20) DEFAULT NULL COMMENT '对应人员ID',
  create_time datetime DEFAULT current_timestamp COMMENT '创建时间',
  login_time datetime comment '最近登录时间',
  status tinyint(4) DEFAULT 1 COMMENT '状态 0 无效 1 有效 2 冻结',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_account_username (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='用户帐号信息';
