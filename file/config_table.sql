-- table
--   table_name 表名
--   schema 所属数据库
--   engine 引擎 InnoDb
--   charset 数据类型
--   comment  备注
--   cache 是否需要缓存
--   au_show 数据增改展示方式
--   detail_show 数据查看展示方式 null 不用展示 dialog 弹框 page 页面
--
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色信息';
--
-- table_index
--   idx_name
--   idx_type(key, primary, unique)
--   idx_column(用,分开)
--
-- table_column
--   id 主键ID
--   table_name 所属表的表名
--   column_name 字段名
--   data_type 数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)
--   sort 展示顺序
--   not_null 是否不空
--   auto_increment 是否自增
--   default_value 默认值
--   comment 备注
--   page_element 页面使用元素
--   data (枚举型数据)选择数据
--   data_from 数据来源
--   is_add是否可添加
--   is_update(是否可以update)
--   query_type 查询时计算方式(在分页查询时，使用哪种计算方式 =, >, <, !=, >=, <=, like {}%, like %{}%, not null)
--
table_function
  table_name
  func_name

func_name: get, find, save, update, remove, findForPage
-- dict_info
--   id 主键ID
--   name 字典名称
--   type 1 list 2 map
-- dict_config
--   id  主键ID
--   value 值
--   label 展示内容
--   sort  顺序
--   parent_id 父节点
--   root 根节点