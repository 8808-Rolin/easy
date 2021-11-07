<!--
 * @Description: Rolin`s code edit
 * @Author: Rolin-Code
 * @Date: 2021-10-29 12:54:03
 * @LastEditors: Rolin
 * @Code-Function-What do you want to do: 
-->

## 高校社团综合问题一体化解决平台

# Easy社团 设计约束文档

`JavaWeb实训大作业小组 - 05陈洁菁 11黄梓圭 14梁晋邦 15梁培才 16刘楚浩`

- [Easy社团 设计约束文档](#easy社团-设计约束文档)
- [技术选型及理由](#技术选型及理由)
- [后端项目结构](#后端项目结构)
    - [文件结构](#文件结构)
- [项目规范](#项目规范)
    - [命名规范](#命名规范)
    - [代码规范](#代码规范)

# 技术选型及理由

本项目后端的项目架构由以下技术组成：

- Tomcat：
    - Tomcat 服务器是一个免费的开放源代码的Web 应用服务器
    - 兼有可扩展性和安全性
    - Tomcat 服务器占用系统资源小
- SpringBoot框架
    - SpringBoot框架能够帮助我们快速构建出一个后端项目，能够极大的节省配置和编码量，提高开发效率
- MyBatis
    1. 与JDBC相比，减少了50%以上的代码量。
    2. MyBatis是最简单的持久化框架，小巧并且简单易学。
    3. MyBatis灵活，不会对应用程序或者数据库的现有设计强加任何影响，SQL写在XML里，从程序代码中彻底分离，降低耦合度，便于统一管理和优化，可重用。
    4. 提供XML标签，支持编写动态SQL语句（XML中使用if, else）。
    5. 提供映射标签，支持对象与数据库的ORM字段关系映射（在XML中配置映射关系，也可以使用注解）。
- Redis
    - 该项目需要大量的的操作数据库，如果将所有数据存放于数据库且不加缓存，那么整个系统的IO会出现很大的瓶颈，因此在项目开发时引入Redis数据库，作为该项目的高速吞吐中心，可以很有效的提高项目的运行效率。

# 后端项目结构

## 文件结构

该项目采用MVC和前后端分离的模式开发，因此后端的View层采用接口的方式实现。本项目的大概结构目录如下所示：

**icu.rolin.easy**:

- Controller:接收渲染层，接受前端的请求，并将数据渲染成JSON字符串通过接口发送给前端
    - user模块
    - info模块
    - action模块
    - association模块
    - bbs模块
    - apply模块
    - tool模块
- Service：业务层，接收来自上一层的请求，经过业务处理后将数据返回给Controller层
- Mapper：映射层，对数据库进行原子操作，并将结果返回给Service层
- Model：模型层，项目对象的最小单位
    - PO：(Persistant Object)持久化对象，一个PO的数据结构对应着库中表的结构，表中的一条记录就是一个PO对象，除了get，set之外没有别的方法
    - BO：(Business Object)业务对象，将多个PO组合起来就成为业务对象BO，常用在Service层中做业务处理
    - VO：(View Object) 视图对象，对应接口的对象格式，每一个接口类型就是一个VO。
- Tool

# 项目规范

## 命名规范
### Service层、Dao层
1. 获取单个或多个对象的方法用get做前缀 
  - getObject
  - getObjectById
  - getObjectByUkey
  - getObjectProperty
  - getObjectPropertyList
2. 获取多个对象的方法用list做前缀（不含分页）
  - listAll
  - listAllBy
  - listAllByXXX
3. 获取统计值的方法用count做前缀 
  - count
  - countBy
  - countByXXX
4. 插入的方法用save做前缀
  - saveObject
  - saveObjectList
5. 删除的方法用remove（推荐）或delete做前缀
  - removeObject
  - removeObjectById
  - removeObjectList
6. 修改的方法用update做前缀
  - updateObject
  - updateObjectList
7. 查询的方法用find做前缀（含分页）
  - findAll
  - findBy
  - findByXXX
  - findByCondition
  - findByConditionWithPage

## 代码规范

