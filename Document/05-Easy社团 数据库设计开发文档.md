<!--

 * @Description: Rolin's code edit
 * @Author: Rolin-Code
 * @Date: 2021-10-25 11:43:57
 * @LastEditors: Rolin
 * @Code-Function-What do you want to do: 
-->

## 高校社团综合问题一体化解决平台

# Easy社团 数据库设计开发文档

`JavaWeb实训大作业小组 - 05陈洁菁 11黄梓圭 14梁晋邦 15梁培才 16刘楚浩`

- [Easy社团 数据库设计开发文档](#easy社团-数据库设计开发文档)
- [1. 引言](#1-引言)
    - [1.1 编写目的](#11-编写目的)
    - [1.2 背景](#12-背景)
    - [1.3 开发环境](#13-开发环境)
    - [1.4 术语定义](#14-术语定义)
- [2. 外部设计](#2-外部设计)
    - [2.1 标识符和状态](#21-标识符和状态)
    - [2.2 使用它的程序](#22-使用它的程序)
    - [2.4 设计约束](#24-设计约束)
    - [2.5 建表规约](#25-建表规约)
    - [2.6 ORM映射规约](#26-orm映射规约)
- [3. 结构设计](#3-结构设计)
    - [3.1 概念结构设计](#31-概念结构设计)
    - [3.2 逻辑结构设计](#32-逻辑结构设计)
        - [3.2.1 数据库表功能](#321-数据库表功能)
        - [3.2.2 各表设计](#322-各表设计)
            - [3.2.2.1 用户表(user)](#3221-用户表user)
            - [3.2.2.2 学院ID对应表(college_table)](#3222-学院id对应表college_table)
            - [3.2.2.3 创建社团申请表(apply_create)](#3223-创建社团申请表apply_create)
            - [3.2.2.4 协会成员表(association_user)](#3224-协会成员表association_user)
            - [3.2.2.5 申请加入协会表(apply_join_association)](#3225-申请加入协会表apply_join_association)
            - [3.2.2.6 通用审批表(apply_commond)](#3226-通用审批表apply_commond)
            - [3.2.2.7 申请内容表(apply_content)](#3227-申请内容表apply_content)
            - [3.2.2.8 邮箱表](#3228-邮箱表)
            - [3.2.2.9 参加活动表](#3229-参加活动表)
            - [3.2.2.10 活动表](#32210-活动表)
            - [3.2.2.11 社团表](#32211-社团表)
            - [3.2.2.12 帖子数据表](#32212-帖子数据表)
            - [3.2.2.13 评论表 (comments)](#32213-评论表-comments)
            - [3.2.2.14 收藏表(favorite_table)](#32214-收藏表favorite_table)
            - [3.2.2.15 帖子内容表(post_content)](#32215-帖子内容表post_content)
- [4.数据库实施](#4数据库实施)
    - [4.1 创建数据库](#41-创建数据库)
    - [4.3 插入默认数据与测试数据](#43-插入默认数据与测试数据)
        - [4.3.1 默认数据](#431-默认数据)
        - [4.3.2测试数据](#432测试数据)

# 1. 引言

## 1.1 编写目的

1. 本数据库设计文档是关于**高校一体化社团管理平台**的数据库设计，包括数据逻辑结构设计，数据字典字段设计以及运行环境和其他设计。
2. 本数据库设计文档读者：用户、系统开发人员、系统测试人员以及系统维护人员。
3. 本数据库设计文档是根据系统需求分析设计所编写的
4. 本数据库设计文档为开发软件提供了一定基础

## 1.2 背景

随着科学技术的不断提高，计算机科学日渐成熟，其强大的功能已为人们深刻认识，他已经进入人类社会各个领域并发挥着越来越重要的作用。近年来，互联网的普及速度远超人们想象，但高校的社团管理还停留在仅靠人工进行管理和操作，这种管理方式存在着许多的缺点，例如效率低以及保密性差的问题，另外时间一长，将产生大量的实体文件和数据，这对于查找、更新和维护文件的工作带来了不少困难，因此一套通过互联网+社团管理的系统成为了管理社团的不二之选，这对于学校管理者以及学生都是至关重要的。而实现该系统需要一套底层数据库的支持。**
本文旨在对此系统所使用的数据库进行详细阐述**。

## 1.3 开发环境

数据库选用 MySQL 8.0 数据库系统进行开发 数据库将最终运行在Ubuntu系统以及Windows系统上 数据库链接：http://easy.rolin.icu:4406/easy_db
数据库账号：root 数据库密码：120605

## 1.4 术语定义

为了节省篇幅，开发文档中将使用一些英文缩写来替代单词的完整写法，还有一些专用名词，为了读者能够更加直观的读懂文档，我们将在该节描述文档中出现的一些术语定义。

- PK ：主键约束 Primary Key

- UK ：唯一约束 Unique Key

- FK ：外键约束 foreign key

- AI ：自增约束 AUTO INCREMENT

- NN：非空约束 not null

- DF ：默认 default

- DB ：数据库 Database

- DBMS：数据库管理系统 Database Management System

- SQL：结构化查询语言 Structured Query Language

- ORM：对象关系映射 Object Relational Mapping

- E-R、ER：实体-联系 Entity Relationship Diagram

## 1.5 更新历史

| 更新时间   | 更新章节           | 更新内容                              |
| ---------- | ------------------ | ------------------------------------- |
| 2021-11-06 | 社团表、用户表     | 头像修改为相对路径URL，不再使用Base64 |
| 2021-11-07 | 3.2.2.3 创建社团表 | 修订 新增note字段                     |
| 2021-11-08 | 3.2.2.7 申请内容表 | 移除该表，将其合并成内容表content     |
| 2021-11-09 | 3.2.2.10 活动表    | 新增position字段，记录活动举办地点    |

# 2. 外部设计

## 2.1 标识符和状态

- 数据库软件的名称：MySQL 8.0
- 数据库的名称：EASY_DB

## 2.2 使用它的程序

**本数据库使用于“Easy社团平台系统”**

## 2.4 设计约束

本系统中，数据库的设计采用绘制E-R图进行，并且采用面向对象的设计方法，首先进行对象实体的设计，最后将对象持久化到数据库中，所有表与表之间的关联即E-R图使用开源软件**draw.io**
绘制，这样能够使整个系统的设计和数据库设计有机的结合起来。

在设计的过程中，出于设计规范的需要，将参考**阿里巴巴Java开发手册**，撰写本项目的MySQL数据库开发约束，详见后续章节。

## 2.5 建表规约

1. 表达是否概念的字段，**必须**使用**is_xxx**的方式命名，数据类型unsigned tinyint (1表示是，0表示否)且任何字段如果为非负数，必须是unsigned。
2. 表名、字段名**必须**使用小写字母或数字，**禁止**出现数字开头，**禁止**两个下划线中间只出现数字。数据库字段名的修改代价很大，因为无法进行预发布，所以字段名称需要**慎重**考虑。
3. 表面**不**使用复数名词。
4. **禁用保留字**，如desc、range、match、delayed等，请参考MySQL官方保留字。
5. 小数类型为decimal，禁止使用float和double。
6. 如果存储的字符串长度几乎相等，使用char定长字符串类型。
7. varchar是可变长字符串，不预先分配存储空间，长度不要超过5000，**如果存储长度大于此值，定义字段类型为text，独立出来一张表，用主键来对应**，避免影响其它字段索引效率。
8. 如果修改字段含义或对字段表示的状态追加时，需要及时更新字段注释。
9. 合适的字符存储长度，不但节约数据库表空间、节约索引存储，更重要的是提升检索速度。 10.所有数据库的命名都是以功能模块加上具体表的英文单词词汇组成，这样能够统一数据库命名。

## 2.6 ORM映射规约

1. 在表查询中，一律不要使用* 作为查询的字段列表，需要哪些字段必须明确写明。
2. 不要写一个大而全的数据更新接口，传入为POJO类，不管是不是自己的目标更新字段，都进行update table set c1=value1,c2=value2,c3=value3;
   这是不对的。执行SQL时，不要更新无改动的字段，一是易出错；二是效率低；三是增加binlog存储。

# 3. 结构设计

## 3.1 概念结构设计

<div aligin=center><img src=./draw.io/database.png /></div>

## 3.2 逻辑结构设计

### 3.2.1 数据库表功能



本系统基于MySQL 8.0 ,数据库名为 EASY_DB，由user、association等是十几个表构成，其功能如下表所示： 

| 序号 | 表 |功能描述 | 
| ---- | ---- |:--:|
| 1 | user|用户表，包含用户信息以及用户空间数据 |
| 2 | association |社团表，包含社团信息 | 
| 3 | post |帖子表，包含帖子信息 |
| 4 | comment |评论表 |
| 5 | post_content|帖子内容表，是帖子表的从表 | 
| 6 | favorite_table |用户收藏表 | 
| 7 | mail |邮件数据表 |
| 8 | action |活动纪录表 | 
| 9 | join_action |活动参与人员表 | 
|10 | apply_join_association |加入社团申请表 | 
| 11 | association_user |社团成员表 | 
| 12 | apply_commond |通用社团审批表 | 
| 13 |apply_content |审批内容表，是通用社团审批表的从表 | 
| 14 | apply_create |创建社团申请表 |
| 15 | college_table |学院对应表 |

### 3.2.2 各表设计

#### 3.2.2.1 用户表(user)

<table>
    <tr><th>表名</th><td colspan='3'>user</td></tr>
    <tr><th>表描述</th><td colspan='3'>用户数据存放表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>用户名</td><td>username</td><td>varchar(25);NN </td><td>用户的昵称</td></tr>
    <tr><td>真实姓名</td><td>realname</td><td>varchar(25);NN </td><td>用户的真实姓名</td></tr>
    <tr><td>学号</td><td>student_number</td><td>varchar(25);NN;UK </td><td>用户的长学号,在该系统里是唯一项</td></tr>
    <tr><td>学院ID</td><td>college_id</td><td>unsigned int;NN </td><td>对应college_table表</td></tr>
    <tr><td>用户密码</td><td>password</td><td>varchar;NN </td><td></td></tr>
    <tr><td>用户邮箱</td><td>email</td><td>varchar;NN </td><td></td></tr>
    <tr><td>手机号码</td><td>phone</td><td>char(11);NN;UK </td><td>用户手机号码，在该系统里是唯一项</td></tr>
    <tr><td>性别</td><td>sex</td><td>unsigned int(1);NN </td><td>0为男，1为女</td></tr>
    <tr><td>生日日期</td><td>birth</td><td>Date;NN </td><td>年月日</td></tr>
    <tr><td>是否开放空间</td><td>is_open_zone</td><td>unsigned int;NN;DF 1 </td><td></td></tr>
    <tr><td>权限等级</td><td>level</td><td>unsigned int;NN;DF 0 </td><td>用户的权限，0为普通用户，1社团学生，2为学校管理员</td></tr>
    <tr><td>用户头像</td><td>user_avatar(255)</td><td>varchar;NN </td><td>一个相对路径url</td></tr>
    <tr><td>发帖数量</td><td>post_number</td><td>unsigned int;NN;DF 0 </td><td></td></tr>
    <tr><td>空间公告</td><td>notice</td><td>varchar(255); </td><td></td></tr>
    <tr><td>个人简介</td><td>intro</td><td>varchar(255);DF "该用户真是懒到不行啦~，没有填写简介呢" </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr></table>


#### 3.2.2.2 学院ID对应表(college_table)

<table>
    <tr><th>表名</th><td colspan='3'>college_table</td></tr>
    <tr><th>表描述</th><td colspan='3'>对应存放每一个学院的ID以及名字</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>学院名称</td><td>college_name</td><td>varchar(15);NN </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
</table>

#### 3.2.2.3 创建社团申请表(apply_create)

<table>
    <tr><th>表名</th><td colspan='3'>apply_create</td></tr>
    <tr><th>表描述</th><td colspan='3'>创建社团申请表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>申请人用户ID</td><td>u_id</td><td>unsigned int;NN </td><td>对应User.id</td></tr>
    <tr><td>社团名称</td><td>name</td><td>varchar;NN </td><td></td></tr>
    <tr><td>社团头像</td><td>logo</td><td>varchar;NN </td><td>相对URL</td></tr>
    <tr><td>社团简介</td><td>intro</td><td>varchar;NN </td><td></td></tr>
    <tr><td>申请备注</td><td>note</td><td>varchar;NN </td><td></td></tr>
    <tr><td>所属组织</td><td>parent_organization</td><td>varchar;NN </td><td></td></tr>
    <tr><td>是否通过审批</td><td>is_approved</td><td>unsigned int;NN;DF 0 </td><td>0为未通过，1为已通过</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>




#### 3.2.2.4 协会成员表(association_user)

<table>
    <tr><th>表名</th><td colspan='3'>association_user</td></tr>
    <tr><th>表描述</th><td colspan='3'>协会成员表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>协会id</td><td>a_id</td><td>unsigned int;NN </td><td>对应association.id</td></tr>
    <tr><td>用户id</td><td>u_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>是否是管理员</td><td>is_admin</td><td>unsigned int;NN;DF 0 </td><td>0为否 1为是</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>

#### 3.2.2.5 申请加入协会表(apply_join_association)

<table>
    <tr><th>表名</th><td colspan='3'>apply_join_association</td></tr>
    <tr><th>表描述</th><td colspan='3'>用户申请加入协会数据表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>用户id</td><td>u_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>协会id</td><td>a_id</td><td>unsigned int;NN </td><td>对应association.id</td></tr>
    <tr><td>申请备注</td><td>note</td><td>varchar;NN </td><td></td></tr>
    <tr><td>是否通过</td><td>is_approved</td><td>unsigned int;NN;DF 0 </td><td>0为否，1为是</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>

#### 3.2.2.6 通用审批表(apply_commond)

<table>
    <tr><th>表名</th><td colspan='3'>apply_commond</td></tr>
    <tr><th>表描述</th><td colspan='3'>社团通用审批表，社团提交给学校的表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>社团id</td><td>a_id</td><td>unsigned int;NN </td><td>对应association.id</td></tr>
    <tr><td>标题</td><td>title</td><td>varchar;NN </td><td></td></tr>
    <tr><td>内容id</td><td>content_id</td><td>unsigned int;NN </td><td>对应apply_content.id</td></tr>
    <tr><td>是否通过</td><td>is_approved</td><td>unsigned int;NN;DF 0 </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>



#### 3.2.2.8 邮箱表

<table>
    <tr><th>表名</th><td colspan='3'>mail</td></tr>
    <tr><th>表描述</th><td colspan='3'>用户邮箱表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>发送人id</td><td>from_id</td><td>unsigned int;可空 </td><td>对应user.id，当发件人是系统时该选项为空</td></tr>
    <tr><td>收件人id</td><td>to_id</td><td>unsigned int;NN </td><td>当mail_type为1时这里是社团aid</td></tr>
    <tr><td>邮件标题</td><td>title</td><td>varchar;NN </td><td></td></tr>
    <tr><td>邮件内容</td><td>content</td><td>varchar;NN </td><td></td></tr>
    <tr><td>是否已读</td><td>is_read</td><td>unsigned int;NN;DF 0 </td><td></td></tr>
    <tr><td>是否系统邮件</td><td>is_system</td><td>unsigned int;NN;DF 0 </td><td></td></tr>
    <tr><td>邮件类型</td><td>mail_type</td><td>unsigned int;NN;DF 0 </td><td>0为个人邮件，1为社团邮件</td></tr>
	<tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>




#### 3.2.2.9 参加活动表

<table>
    <tr><th>表名</th><td colspan='3'>join_action</td></tr>
    <tr><th>表描述</th><td colspan='3'>用户参加活动表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>活动id</td><td>act_id</td><td>unsigned int;NN </td><td>对应action.id</td></tr>
    <tr><td>用户id</td><td>u_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
</table>

#### 3.2.2.10 活动表

<table>
    <tr><th>表名</th><td colspan='3'>action</td></tr>
    <tr><th>表描述</th><td colspan='3'>活动表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>社团id</td><td>a_id</td><td>unsigned int;NN </td><td>对应association.id</td></tr>
    <tr><td>活动标题</td><td>title</td><td>varchar(120);NN </td><td></td></tr>
    <tr><td>活动内容ID</td><td>content_id</td><td>unsign int;NN </td><td>富文本字符串</td></tr>
    <tr><td>position</td><td>position</td><td>unsign int;NN </td><td>举办活动的地点</td></tr>
    <tr><td>开始时间</td><td>start_time</td><td>DATETIME;NN </td><td></td></tr>
    <tr><td>结束时间</td><td>end_time</td><td>DATETIME;NN </td><td></td></tr>
	<tr><td>是否通过</td><td>is_approved</td><td>unsigned int;NN;DF 0 </td><td>0为否，1为真</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>



#### 3.2.2.11 社团表

<table>
    <tr><th>表名</th><td colspan='3'>association</td></tr>
    <tr><th>表描述</th><td colspan='3'>社团表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>协会负责人id</td><td>leader_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>协会名</td><td>name</td><td>varchar;NN </td><td></td></tr>
    <tr><td>logo</td><td>logo</td><td>varchar;NN </td><td>URL</td></tr>
    <tr><td>简介</td><td>intro</td><td>varchar;NN </td><td></td></tr>
    <tr><td>上属组织</td><td>parent_organization</td><td>varchar;NN </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>


#### 3.2.2.12 帖子表 (Post)

<table>
    <tr><th>表名</th><td colspan='3'>post</td></tr>
    <tr><th>表描述</th><td colspan='3'>帖子数据表</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>社团id</td><td>a_id</td><td>unsigned int;NN </td><td>对应association,为0时是公共交流区</td></tr>
    <tr><td>用户id</td><td>u_id</td><td>unsigned int;NN </td><td></td></tr>
    <tr><td>标题</td><td>title</td><td>varchar;NN </td><td></td></tr>
    <tr><td>内容id</td><td>content_id</td><td>unsigned int;NN </td><td>对应post_content.id</td></tr>
    <tr><td>标签</td><td>tags</td><td>varchar; </td><td></td></tr>
    <tr><td>帖子类型</td><td>post_type</td><td>unsigned int;NN </td><td>当帖子类型为0时是系统帖子</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>


#### 3.2.2.13 评论表 (comments)

<table>
    <tr><th>表名</th><td colspan='3'>comments</td></tr>
    <tr><th>表描述</th><td colspan='3'>是帖子的评论</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>帖子ID</td><td>p_id</td><td>unsigned int;NN </td><td>对应post.id</td></tr>
    <tr><td>发表人ID</td><td>u_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>内容</td><td>content</td><td>varchar;NN </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
</table>

#### 3.2.2.14 收藏表(favorite_table)

<table>
    <tr><th>表名</th><td colspan='3'>favorite_table</td></tr>
    <tr><th>表描述</th><td colspan='3'>收藏表，表示用户是否收藏了该帖子</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>帖子ID</td><td>p_id</td><td>unsigned int;NN </td><td>对应post.id</td></tr>
    <tr><td>收藏人ID</td><td>u_id</td><td>unsigned int;NN </td><td>对应user.id</td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
</table>
#### 3.2.2.15 内容表(content)

申请内容表、活动表以及帖子内容表中的字段均指向该表

<table>
    <tr><th>表名</th><td colspan='3'>content</td></tr>
    <tr><th>表描述</th><td colspan='3'>是帖子的评论</td></tr>
    <tr><th>中文描述</th><th>字段名</th><th>类型以及精度</th><th>备注</th></tr>
    <tr><td>唯一标识符</td><td>id</td><td>unsigned int;PK;UK;AI;NN </td><td></td></tr>
    <tr><td>内容</td><td>content</td><td>text;NN </td><td></td></tr>
    <tr><td>创建时间</td><td>create_time</td><td>timestamp;NN;DEFAULT CURRENT_TIMESTAMP</td><td></td></tr>
    <tr><td>更改时间</td><td>update_time</td><td>Timestamp;NN;ON UPDATE CURRENT_TIMESTAMP </td><td></td></tr>
</table>


# 4.数据库实施

## 4.1 创建数据库

创建数据库时需要标定数据库的默认编码，因此创建数据库语句如下：

```mysql
DROP DATABASE IF EXISTS `easy_db`;
CREATE DATABASE IF NOT EXISTS `easy_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
````

## 4.2 创建表

创建数据库表的全部语句如下：

```mysql
use easy_db;

CREATE TABLE user (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `username` VARCHAR(25) NOT NULL COMMENT '用户昵称',
  `realname` VARCHAR(25) NOT NULL COMMENT '真实姓名',
  `student_number` VARCHAR(25) NOT NULL UNIQUE COMMENT '学生学号',
  `college_id` INT UNSIGNED NOT NULL COMMENT '学院ID',
  `password` VARCHAR(32) NOT NULL COMMENT 'MD5加密过的用户密码',
  `email` VARCHAR(35) NOT NULL COMMENT '电子邮箱',
  `phone` VARCHAR(11) NOT NULL UNIQUE COMMENT '手机号码',
  `sex` INT UNSIGNED NOT NULL COMMENT '性别',
  `birth` DATE NOT NULL COMMENT '生日',
  `is_open_zone` INT UNSIGNED DEFAULT 1 COMMENT '是否开放空间',
  `level` INT UNSIGNED DEFAULT 0 COMMENT '用户权限',
  `user_avatar` VARCHAR(255) NOT NULL COMMENT '用户头像',
  `post_number` INT UNSIGNED DEFAULT 0 COMMENT '发帖数量',
  `notice` VARCHAR(255) COMMENT '空间公告',
  `intro` VARCHAR(255) DEFAULT '该用户真是懒到不行啦~，没有填写简介呢' COMMENT '个人简介',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE college_table (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `college_name` VARCHAR(15) NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE apply_create (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `u_id` INT UNSIGNED NOT NULL COMMENT '申请人ID',
  `name` VARCHAR(30) NOT NULL COMMENT '社团名字',
  `logo` VARCHAR(255) COMMENT '头像的相对URL路径',
  `intro` VARCHAR(255) NOT NULL COMMENT '社团简介',
  `note` VARCHAR(255) NOT NULL COMMENT '申请备注',
  `parent_organization` VARCHAR(25) NOT NULL COMMENT '上级组织',
  `is_approved` INT UNSIGNED DEFAULT 0 NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE association_user(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `a_id` INT UNSIGNED NOT NULL COMMENT '协会ID',
  `u_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `is_admin` INT UNSIGNED DEFAULT 0 NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE apply_join_association(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `a_id` INT UNSIGNED NOT NULL COMMENT '协会ID',
  `u_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `note` VARCHAR(255) NOT NULL COMMENT '申请时的备注',
  `is_approved` INT UNSIGNED DEFAULT 0 NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE apply_commond(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `a_id` INT UNSIGNED NOT NULL COMMENT '协会ID',
  `title` VARCHAR(120) NOT NULL COMMENT '标题',
  `content_id` INT UNSIGNED NOT NULL COMMENT '内容ID' ,
  `is_approved` INT UNSIGNED DEFAULT 0 NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;



CREATE TABLE mail(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `from_id` INT UNSIGNED  COMMENT '发件人,当为系统时该字段为空',
  `to_id` INT UNSIGNED NOT NULL COMMENT '收件人',
  `title` VARCHAR(120) NOT NULL COMMENT '标题',
  `content` VARCHAR(255) NOT NULL COMMENT '邮件内容',
  `is_read` INT UNSIGNED DEFAULT 0,
  `is_system` INT UNSIGNED DEFAULT 0,
  `mail_type` INT UNSIGNED DEFAULT 0,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE join_action(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `act_id` INT UNSIGNED NOT NULL COMMENT '活动ID',
  `u_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE action(
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `a_id` INT UNSIGNED NOT NULL COMMENT '协会ID',
  `title` VARCHAR(120) NOT NULL COMMENT '标题',
  `content_id` INT UNSIGNED NOT NULL COMMENT '活动内容ID',
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `is_approved` INT UNSIGNED DEFAULT 0 NOT NULL,
   `position` VARCHAR(30) NOT NULL DEFAULT '操场',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE association (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `leader_id` INT UNSIGNED NOT NULL COMMENT '负责人ID',
  `name` VARCHAR(120) NOT NULL COMMENT '社团名字',
  `logo` VARCHAR(255) NOT NULL COMMENT '社团头像,相对路径URL',
  `intro` VARCHAR(120) NOT NULL COMMENT '社团简介',
  `parent_organization` VARCHAR(35) NOT NULL COMMENT '上级组织',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE post (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `a_id` INT UNSIGNED NOT NULL COMMENT '协会ID,为0表示公共论坛',
  `u_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `title` VARCHAR(120) NOT NULL COMMENT '标题',
  `content_id` INT UNSIGNED NOT NULL COMMENT '内容对应ID',
  `tags` VARCHAR(255)  COMMENT '逗号分隔标签',
  `post_type` INT UNSIGNED NOT NULL COMMENT '帖子类型，是一个整数型，为0时是公告',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE comments (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `p_id` INT UNSIGNED NOT NULL COMMENT '帖子ID',
  `u_id` INT UNSIGNED NOT NULL COMMENT '发表人用户ID',
  `content` VARCHAR(255) NOT NULL COMMENT '内容对应ID',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
	
CREATE TABLE favorite_table (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `p_id` INT UNSIGNED NOT NULL COMMENT '帖子ID',
  `u_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE content (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
  `content` MEDIUMTEXT NOT NULL COMMENT '内容富文本',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

```

## 4.3 插入默认数据与测试数据

### 4.3.1 测试数据

#### 4.3.1.1 插入用户数据

- 默认密码：password
- 默认头像：/images/default.jpg

```sql
use easy_db;
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Alan","张三","202006070001",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_01@foxmail.com","18906070000",0,"2000-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Beer","李四","202006070002",2,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_02@foxmail.com","18906070001",0,"2000-01-02",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Chen","陈一发","202006070002",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_03@foxmail.com","18906070002",0,"2000-01-03",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Dog","王五","202006070004",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_05@foxmail.com","18906070004",0,"2000-01-05",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("EDGNB","黄六","202006070005",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_06@foxmail.com","18906070005",0,"2000-02-06",1,1,"/images/default.jpg","这是测试Test05用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("FuckYouMan","李鸿雁","202006070006",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_07@foxmail.com","18906070006",0,"2000-05-05",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("惜曦","陈洁菁","202006070007",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_08@foxmail.com","18906070007",0,"2001-06-22",0,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Xixi","李某","202006070008",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_09@foxmail.com","18906070008",0,"2002-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("氯磷","黄梓圭","202006070009",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_10@foxmail.com","18906070009",0,"2000-08-27",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Joolum","梁晋邦","202006070010",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_11@foxmail.com","18906070010",0,"2000-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("MeUmy","咩栗","202006070011",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_12@foxmail.com","18906070011",0,"1999-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("树莓派","刘楚浩","202006070012",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_13@foxmail.com","18906070012",0,"2000-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("我爱编程","刘盈余","202006070013",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_14@foxmail.com","18906070013",1,"2000-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("香橙派","李小龙","202006070014",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_15@foxmail.com","18906070014",1,"2000-01-01",1,1,"/images/default.jpg","这是测试用户，这是我的空间公告","该用户真是懒到不行啦~连简介都要代劳");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("Faker","李相赫","202006070015",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_16@foxmail.com","18906070015",1,"2000-01-01",1,0,"/images/default.jpg","这是测试用户，这是我的空间公告","李相赫（游戏ID：Faker），绰号“大魔王”，司职《英雄联盟》中单，1996年5月7日出生于韩国，是国际电子竞技俱乐部T1英雄联盟分部的队员之一");
INSERT INTO user (username,realname,student_number,college_id,password,email,phone,sex,birth,is_open_zone,level,user_avatar,notice,intro) VALUES ("AdminTest","贺建锋","202006070016",1,"5f4dcc3b5aa765d61d8327deb882cf99","test_account_17@foxmail.com","18906070017",0,"1966-04-01",0,2,"/images/default.jpg","这是一个管理员用户，这是我的空间公告","管理员就是牛逼好吧");
```
#### 4.3.1.2 插入学院ID数据
- 一共有1~8，8个学院数据
```sql
INSERT INTO college_table(college_name) VALUES ("信息技术学院");
INSERT INTO college_table(college_name) VALUES ("应用外语学院");
INSERT INTO college_table(college_name) VALUES ("轻化工技术学院");
INSERT INTO college_table(college_name) VALUES ("财贸学院");
INSERT INTO college_table(college_name) VALUES ("男德学院");
INSERT INTO college_table(college_name) VALUES ("酒店管理学院");
INSERT INTO college_table(college_name) VALUES ("艺术设计学院");
INSERT INTO college_table(college_name) VALUES ("机电技术学院");
INSERT INTO college_table(college_name) VALUES ("A-SOUL学院");

```

#### 4.3.1.3 新建社团申请表数据
- 初始一共由四则数据，均是没有通过审批的
```sql
INSERT INTO apply_create (uid,name,logo,intro,note,parent_organization) VALUES (1,"极东魔术昼寝结社の夏","/images/default.jpg","极东魔术昼寝结社之夏（日语：极东魔术昼寝结社の夏）（或译：极东魔术昼寝结社的夏天、远东魔法午睡结社之夏）是轻小说《中二病也要谈恋爱》及其衍生作品中的一个架空的非正式学生社团（同好会）。","决定在学校寻找据点，亲自成立一个社团","社团联合会");
INSERT INTO apply_create (uid,name,logo,intro,note,parent_organization) VALUES (2,"学园偶像部","/images/default.jpg","Lovelive！","拯救废社危机！","社团联合会");
INSERT INTO apply_create (uid,name,logo,intro,note,parent_organization) VALUES (3,"轻音部","/images/default.jpg","如果不能重新召集起4名成员就会被废部。前来参观合唱部的琴吹䌷加入了，正在寻找最后一人……。","决定在学校寻找据点，亲自成立一个社团","社团联合会");
INSERT INTO apply_create (uid,name,logo,intro,note,parent_organization) VALUES (4,"摸鱼社","/images/default.jpg","大家一起来摸鱼。","这是一条备注信息","学校直辖协会");
```
#### 4.3.1.4 协会表数据
- 默认头像：/images/default.jpg
- 一共由5个协会数据组成
```sql
INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (5,"轻风文学社","/images/default.jpg","这是文学社的简介信息","社团联合会");
INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (6,"自由天空动漫协会","/images/default.jpg","我们是广东轻工职业技术学院 60多个社团里之一的，艺术类社团 自由天空动漫协会，成立于2004年。是由一批动漫爱好者自主建立的属于业余爱好的社团。 ","社团联合会");
INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (7,"国学社","/images/default.jpg","这是国学社的简介信息","社团联合会");
INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (8,"青年志愿服务协会","/images/default.jpg","这是青年志愿服务协会的简介信息","校团委");
INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (9,"马克思主义与习近平新时代特色社会主义研究社团","/images/default.jpg","这是马克思主义与习近平新时代特色社会主义研究社团的简介信息","校党委");
```

#### 4.1.1.5 协会成员表
- 拥有社团的首先是管理员
- 其次每个社团拥有一定数量的社员，也有没有社员的社团
```sql
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(1,5,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(1,10,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(1,11,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(1,13,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(1,14,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(2,6,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(2,1,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(2,2,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(2,3,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,7,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,1,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,2,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,3,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,4,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,11,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(3,14,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(4,8,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(5,9,1);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(5,10,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(5,11,0);
INSERT INTO association_user(a_id,u_id,is_admin)VALUES(5,12,0);
```

#### 4.1.1.6 申请加入协会数据
- 大部分是未通过状态，只有两个是通过状态
```sql
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (1,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (2,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (3,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (4,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (5,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (6,4,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (1,1,"测试数据.........",0);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (2,1,"测试数据.........",0);
VALUES (2,3,"测试数据.........通过了呢~",1);
INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES (3,3,"测试数据.........我能过吗",1);

```
#### 4.3.1.7 邮箱表数据
```SQL
# 系统邮件
INSERT INTO mail (to_id,title,content,is_read,is_system,mail_type) VALUES (2,"0-这是一封系统测试邮件","这里是邮件内容！！！！",0,1,0);
INSERT INTO mail (to_id,title,content,is_read,is_system,mail_type) VALUES (5,"1-这是一封系统测试邮件","这里是邮件内容！！！！",1,1,0);
INSERT INTO mail (to_id,title,content,is_read,is_system,mail_type) VALUES (5,"2-这是一封系统测试邮件","这里是邮件内容！！！！",0,1,0);
INSERT INTO mail (to_id,title,content,is_read,is_system,mail_type) VALUES (2,"3-这是一封系统测试邮件","这里是邮件内容！！！！",0,1,1);
INSERT INTO mail (to_id,title,content,is_read,is_system,mail_type) VALUES (2,"4-这是一封系统测试邮件","这里是邮件内容！！！！",0,1,1);
# 用户邮件
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (12,1,"5-这是一封用户发送的测试邮件","这里是邮件内容！！！！",0,0,0);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (12,1,"6-这是一封用户发送的测试邮件","这里是邮件内容！！！！",0,0,0);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (12,1,"7-这是一封用户发送的测试邮件","这里是邮件内容！！！！",1,0,0);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (11,2,"8-这是一封用户发送的测试邮件","这里是邮件内容！！！！",0,0,0);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (11,2,"9-这是一封用户发送给社团的测试邮件","这里是邮件内容！！！！",0,0,1);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (11,2,"10-这是一封用户发送给社团的测试邮件","这里是邮件内容！！！！",0,0,1);
INSERT INTO mail (from_id,to_id,title,content,is_read,is_system,mail_type) VALUES (11,2,"1-这是一封用户发送给社团的测试邮件","这里是邮件内容！！！！",1,0,1);
```
#### 4.3.1.8 内容表数据
```sql
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>1-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>2-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>3-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>4-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>4.5-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>5-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.baidu.com'>6-通用申请表-测试数据内容表格</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>7-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>8-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>10-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>11-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>12-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>13-活动内容-我的活动很重要！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>14-中國共産黨第十九屆中央委員會第六次全體會議8日上午在京召開。中央委員會總書記習近平代表中央政治局向全會作工作報告，並就《中共中央關于黨的百年奮鬥重大成就和歷史經驗的決議（討論稿）》向全會作了説明。！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>15-活动内容-我的活动很重要！</a>\n<img src='http://rolin.icu:11119/images/default.jpg' />");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>16-帖子内容，奇奇怪怪！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>17-帖子内容，奇奇怪怪！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>18-帖子内容，奇奇怪怪！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>19-帖子内容，奇奇怪怪！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>20-帖子内容，奇奇怪怪！</a>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>21-帖子内容，奇奇怪怪！</a>\n<p>富途moomoo小岛粉丝专享一股NIO蔚来赠股，价值约40美金，全网中文博主独家福利！\n通过小岛链接入金100美金可额外获赠一股NIO！小岛专属页面链接打开可看到新人豪礼详情。</p>");
INSERT INTO content (content) VALUES ("<a href='www.BILIBILI.com'>22-帖子内容，奇奇怪怪！</a>");




```
#### 4.3.1.9 通用审批表数据
- 均未通过
```sql
INSERT INTO apply_commond(a_id,title,content_id) VALUES (2,"我想要举办社团活动",1);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (2,"我想要举办社团活动",2);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (2,"我想要举办社团活动",3);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (2,"我想要举办社团活动",4);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (2,"我想要举办社团活动",5);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (3,"我想要举办社团活动",6);
INSERT INTO apply_commond(a_id,title,content_id) VALUES (4,"我想要举办社团活动",7);
```
#### 4.3.1.10 活动表数据
```sql
INSERT INTO action (a_id,title,content_id,start_time,end_time,is_approved,position) VALUES (1,"举办Lovelive1",8,"2021-06-27 18:00:00","2021-12-08 12:00:00",1,"第四实训楼 C209");
INSERT INTO action (a_id,title,content_id,start_time,end_time,is_approved,position) VALUES (2,"举办Lovelive2",9,"2021-12-27 18:00:00","2021-12-30 13:00:00",0,"第一教学楼 1109");
INSERT INTO action (a_id,title,content_id,start_time,end_time,is_approved,position) VALUES (3,"举办Lovelive3",10,"2021-11-27 18:00:00","2021-12-08 12:00:00",0,"43栋楼下");
INSERT INTO action (a_id,title,content_id,start_time,end_time,is_approved,position) VALUES (3,"举办Lovelive4",11,"2021-11-27 18:00:00","2021-12-08 12:00:00",1,"x");
```

#### 4.3.1.11 参加活动表
```SQL
INSERT INTO join_action (act_id,u_id) VALUES (1,5);
INSERT INTO join_action (act_id,u_id) VALUES (1,10);
INSERT INTO join_action (act_id,u_id) VALUES (1,11);
INSERT INTO join_action (act_id,u_id) VALUES (1,13);
INSERT INTO join_action (act_id,u_id) VALUES (4,1);
INSERT INTO join_action (act_id,u_id) VALUES (4,2);
INSERT INTO join_action (act_id,u_id) VALUES (4,3);
```
#### 4.3.1.12 帖子表
- 插入一些帖子，公告论坛一共有5个帖子，两个论坛各三个帖子
- 其中，公共论坛有三个公告，其他论坛各一个
```sql
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (0,16,"这是一则公共交流区的公告",12,"ceshi,Test,测试",0);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (0,16,"美商务部长下周访问亚洲强化供应链 行程不包括中国",13,"ceshi,Test,测试",0);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (0,16,"中國共産黨第十九屆中央委員會第六次全體會議在京召開",14,"ceshi,Test,测试",0);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (0,13,"习氏决议VS.邓氏决议 学者吁警惕个人野心膨胀危及国际秩序",15,"ceshi,Test,测试",1);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (0,4,"每次穿裙子回寝室，我原来的室友们都极其淡定，有一种我是他们姐姐的感觉",16,"ceshi,Test,测试",1);

INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (2,6,"WE ARE THE CHAMPION!!!!!",17,"ceshi,Test,测试",0);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (3,7,"天宫玉兔的汉服真的好好看哇！",18,"ceshi,Test,测试",0);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (2,1,"舌尖上的中国空间站",19,"ceshi,Test,测试",1);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (2,2,"大家还是要知道「距离产生美」，网络上的人设可以是完美的，但实际情况是你不知道对方实际是一个什么样的人",20,"ceshi,Test,测试",1);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (5,12,"罗杰叔叔点评：地狱厨神戈登拉姆齐版10分钟拉面",21,"ceshi,Test,测试",1);
INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES (5,9,"【小岛浪吹】目前尺度最大的一期，这么多年你都白嫖了，原来里面有这么多牛B",22,"ceshi,Test,测试",1);
```
#### 4.3.1.13 评论表
- 三种情况：
	- 没有回复
	- 有几条回复
	- 有很多回复
```sql
INSERT INTO comments (p_id,u_id,content) VALUES(2,3,"测试评论等等等等");
INSERT INTO comments (p_id,u_id,content) VALUES(2,4,"测试评论等等等等");
INSERT INTO comments (p_id,u_id,content) VALUES(2,5,"测212试评论等等等等");
INSERT INTO comments (p_id,u_id,content) VALUES(7,7,"测试评论等等等等");
INSERT INTO comments (p_id,u_id,content) VALUES(7,7,"goodgoodgood");
INSERT INTO comments (p_id,u_id,content) VALUES(7,14,"goodgoodgood");
INSERT INTO comments (p_id,u_id,content) VALUES(7,16,"好的好的好的");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的好的，这是一条评论试了xxx");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的撒娇的发货时间发货哈大家进啊·和大家安静·x");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的撒娇的发货时间发货哈大家进啊·和大家安静·x");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的撒娇的发货时间发货哈大家进啊·和大家安静·x");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的撒娇的发货时间发货哈大家进啊·和大家安静·x");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"好的好的撒娇的发货时间发货哈大家进啊·和大家安静·x");
INSERT INTO comments (p_id,u_id,content) VALUES(7,1,"楼主牛逼！！！！！！！！！！！！！！！！！！！！");
```
#### 4.3.1.14 收藏表
```sql
INSERT INTO favorite_table(p_id,u_id) VALUES (2,1);
INSERT INTO favorite_table(p_id,u_id) VALUES (2,2);
INSERT INTO favorite_table(p_id,u_id) VALUES (3,4);
```
### 4.3.2 默认数据