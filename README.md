## 本项目实现的最终作用是基于JSP在线公交查询管理系统
## 分为1个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 公交车信息管理
 - 站点信息管理
 - 管理员登录
 - 线路信息管理
## 数据库设计如下：
# 数据库设计文档

**数据库名：** busystem

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [admin](#admin) | 管理员表 |
| [bus](#bus) |  |
| [line](#line) |  |
| [notice](#notice) |  |
| [station](#station) |  |

**表名：** <a id="admin">admin</a>

**说明：** 管理员表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | UserName |   char   | 20 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | PassWord |   char   | 20 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="bus">bus</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   varchar   | 255 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  3   | Region |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | BeginTime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  5   | LastTime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="line">line</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | LineID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | Bus_ID |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  3   | Sta_ID |   varchar   | 255 |   0    |    N     |  N   |       |   |
|  4   | Distance |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="notice">notice</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   int   | 10 |   0    |    N     |  N   |       |   |
|  2   | title |   varchar   | 255 |   0    |    N     |  N   |       | 标题  |
|  3   | content |   longtext   | 2147483647 |   0    |    Y     |  N   |   NULL    | 内容  |
|  4   | Time |   datetime   | 19 |   0    |    N     |  N   |       |   |

**表名：** <a id="station">station</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | ID |   varchar   | 255 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

**运行不出来可以微信 javape 我的公众号：源码码头**
