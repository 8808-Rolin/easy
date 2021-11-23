<!--

 * @Description: Rolin's code edit
 * @Author: Rolin-Code
 * @Date: 2021-10-25 11:36:23
 * @LastEditors: Rolin
 * @Code-Function-What do you want to do: 描述Web api 定义
-->

## 高校社团综合问题一体化解决平台

# Easy社团 用户UI与后台Web服务HTTP协议定义

`JavaWeb实训大作业小组 - 05陈洁菁 11黄梓圭 14梁晋邦 15梁培才 16刘楚浩`

- [Easy社团 用户UI与后台Web服务HTTP协议定义](#easy社团-用户ui与后台web服务http协议定义)
- [1. 概述](#1-概述)
  - [1.1 编写目的](#11-编写目的)
  - [1.2 编写背景](#12-编写背景)
  - [1.3 更新版本](#13-更新版本)
- [2. 接口设计规范](#2-接口设计规范)
- [3. UI与后台Web服务的HTTP协议定义](#3-ui与后台web服务的http协议定义)
  - [3.1 用户操作相关接口](#31-用户操作相关接口)
    - [3.1.1 登录/登出](#311-登录登出)
      - [3.1.1.1 登录（Finish）](#3111-登录finish)
      - [3.1.1.2 登出 (Remove)](#3112-登出-remove)
    - [3.1.2 注册 (Finish)](#312-注册-finish)
    - [3.1.3 忘记密码 (Finish)](#313-忘记密码-finish)
    - [3.1.4 判断学号与手机号码是否唯一 (Finish)](#314-判断学号与手机号码是否唯一-finish)
    - [3.1.5 通用接口](#315-通用接口)
      - [3.1.4.1 获取个人信息 (Finish)](#3141-获取个人信息-finish)
      - [3.1.4.2 图片上传接口  (Finish)](#3142-图片上传接口--finish)
      - [3.1.4.3 邮件发送接口  (Finish)](#3143-邮件发送接口--finish)
      - [3.1.4.4 获取社员列表 (Finish)](#3144-获取社员列表-finish)
      - [3.1.4.5 获取所有社团列表(Finish)](#3145-获取所有社团列表finish)
      - [3.1.4.6 文件上传接口 (Finish)](#3146-文件上传接口-finish)
      - [3.1.4.7 获取所有学院列表 (Finish)](#3147-获取所有学院列表-finish)
      - [3.1.4.8 下载文件接口 (Finish)](#3148-下载文件接口-finish)
      - [3.1.4.9 获取总的帖子类型（Finish）](#3149-获取总的帖子类型finish)
      - [3.1.4.10 通过社团ID获取社团信息（Finish）](#31410-通过社团id获取社团信息finish)
  - [3.2 首页相关接口](#32-首页相关接口)
    - [3.2.1 获取简单的用户信息（Finish）](#321-获取简单的用户信息finish)
    - [3.2.2 获取简要的公告信息（Finish）](#322-获取简要的公告信息finish)
    - [3.2.3 创建社团审批 （Finish）](#323-创建社团审批-finish)
  - [3.3 论坛页面相关接口](#33-论坛页面相关接口)
    - [3.3.1 获取论坛顶部数据接口（Finish）](#331-获取论坛顶部数据接口finish)
    - [3.3.2 获取帖子列表数据接口（Finish）](#332-获取帖子列表数据接口finish)
    - [3.3.3 发表帖子接口 (Finish)](#333-发表帖子接口-finish)
    - [3.3.4 论坛页面获取相关个人和论坛信息接口 (Finish)](#334-论坛页面获取相关个人和论坛信息接口-finish)
    - [3.3.5 申请加入社团接口 （Finish）](#335-申请加入社团接口-finish)
    - [3.3.6 获取社团活动列表接口(Finish)](#336-获取社团活动列表接口finish)
    - [3.3.7 	获取活动详细信息接口（Finish）](#337-获取活动详细信息接口finish)
    - [3.3.8 申请参加活动接口（Finish）](#338-申请参加活动接口finish)
  - [3.4 帖子页面相关接口](#34-帖子页面相关接口)
    - [3.4.1 获取个人信息接口](#341-获取个人信息接口)
    - [3.4.2 获取帖子与发帖人信息（Finish）](#342-获取帖子与发帖人信息finish)
    - [3.4.3 获取回复和回复人列表(Finish)](#343-获取回复和回复人列表finish)
    - [3.4.4 提交回复（Finish）](#344-提交回复finish)
    - [3.4.5 删除帖子以及回复（Finish）](#345-删除帖子以及回复finish)
    - [3.4.6 修改帖子(Finish)](#346-修改帖子finish)
    - [3.4.7 收藏帖子 (Finish)](#347-收藏帖子-finish)
  - [3.5 搜索页面相关接口(Finishing)](#35-搜索页面相关接口finishing)
  - [3.6 个人空间相关接口](#36-个人空间相关接口)
    - [3.6.1 权限验证](#361-权限验证)
    - [3.6.2 获取我的（他的）帖子和收藏](#362-获取我的他的帖子和收藏)
    - [3.6.3 获取我的邮件](#363-获取我的邮件)
      - [3.6.3.1 清空邮箱](#3631-清空邮箱)
      - [3.6.3.2 获取邮箱概要数据](#3632-获取邮箱概要数据)
      - [3.6.3.3 获取邮件内容](#3633-获取邮件内容)
    - [3.6.4 获取我的个人信息和公告](#364-获取我的个人信息和公告)
    - [3.6.5 修改公告](#365-修改公告)
    - [3.6.6 修改个人信息](#366-修改个人信息)
      - [3.6.6.1 修改昵称](#3661-修改昵称)
      - [3.6.6.2 修改简介](#3662-修改简介)
      - [3.6.6.3 修改头像](#3663-修改头像)
      - [3.6.6.4 修改电子邮箱](#3664-修改电子邮箱)
      - [3.6.6.5 修改生日](#3665-修改生日)
  - [3.7 社团管理后台相关接口](#37-社团管理后台相关接口)
    - [3.7.1 首页相关接口](#371-首页相关接口)
      - [3.7.1.1 固定展示信息](#3711-固定展示信息)
      - [3.7.1.2 社团邮箱数据](#3712-社团邮箱数据)
        - [3.7.1.2.1 显示摘要列表](#37121-显示摘要列表)
        - [3.7.1.2.2 展示邮件信息](#37122-展示邮件信息)
      - [3.7.1.3 数据可视化接口](#3713-数据可视化接口)
        - [3.7.1.3.1 个人活跃度](#37131-个人活跃度)
        - [3.7.1.3.2 社团每天活跃度](#37132-社团每天活跃度)
    - [3.7.2 社团成员管理](#372-社团成员管理)
      - [3.7.2.1 社团成员管理](#3721-社团成员管理)
        - [3.7.2.1.1 获取社团成员](#37211-获取社团成员)
        - [3.7.2.1.2 踢出成员](#37212-踢出成员)
      - [3.7.2.2 入社审批管理](#3722-入社审批管理)
        - [3.7.2.2.1 获取审批列表](#37221-获取审批列表)
        - [3.7.2.2.2 审批操作](#37222-审批操作)
    - [3.7.3 论坛公告管理接口](#373-论坛公告管理接口)
      - [3.7.3.1 获取论坛公告](#3731-获取论坛公告)
      - [3.7.3.2 发表论坛公告](#3732-发表论坛公告)
      - [3.7.3.2 删除论坛公告](#3732-删除论坛公告)
    - [3.7.4 论坛信息修改接口](#374-论坛信息修改接口)
    - [3.7.5 审批申请](#375-审批申请)
      - [3.7.5.1 审批清单](#3751-审批清单)
      - [3.7.5.2 发送审批](#3752-发送审批)
    - [3.7.6 活动管理](#376-活动管理)
      - [3.7.6.1 活动发布](#3761-活动发布)
      - [3.7.6.2 活动一览](#3762-活动一览)
      - [3.7.6.3 活动参加人员](#3763-活动参加人员)
  - [3.8 学校管理后台相关接口](#38-学校管理后台相关接口)
    - [3.8.1 首页](#381-首页)
      - [3.8.1.1 获取用户总数](#3811-获取用户总数)
      - [3.8.1.2 加入社团的学生占比](#3812-加入社团的学生占比)
      - [3.8.1.3 获取当前活动列表](#3813-获取当前活动列表)
      - [3.8.1.4 社团活跃度(数据可视化)](#3814-社团活跃度数据可视化)
        - [3.8.1.4.1 单个社团活跃度柱状图数据](#38141-单个社团活跃度柱状图数据)
        - [3.8.1.4.2 整个系统活跃度同比折线图数据](#38142-整个系统活跃度同比折线图数据)
    - [3.8.2 社团管理](#382-社团管理)
      - [3.8.2.1 社团信息一览](#3821-社团信息一览)
      - [3.8.2.2 创建社团审批](#3822-创建社团审批)
        - [3.8.2.2.1 拉取审批信息](#38221-拉取审批信息)
        - [3.8.2.2.2 审批](#38222-审批)
    - [3.8.3 用户信息](#383-用户信息)
    - [3.8.4 审批批复](#384-审批批复)
      - [3.8.4.1 拉取审批信息](#3841-拉取审批信息)
      - [3.8.4.2 审批批复](#3842-审批批复)
      - [3.8.4.3 拉取活动审批列表](#3843-拉取活动审批列表)
      - [3.8.4.4 活动审批](#3844-活动审批)
    - [3.8.5 公共交流区公告管理](#385-公共交流区公告管理)
      - [3.8.5.1 获取论坛公告](#3851-获取论坛公告)
      - [3.8.5.2 发表论坛公告](#3852-发表论坛公告)
      - [3.8.5.2 删除论坛公告](#3852-删除论坛公告)

# 1. 概述

## 1.1 编写目的

“Easy社团”系统采用前后端分离的模式进行开发，因此前端Web与后端服务器采用HTTP通讯协议进行交流，本文档即是对其交互协议做描述性定义

## 1.2 编写背景

在文档《03-用户UI设计书中》中，详细介绍了用户操作过程及UI设计。在UI设计中，涉及到需要后台Web服务配合才能完成的功能。例如，登录功能，需要后台Web对用户名/密码进行验证才能保证用户的合法性。本文档对UI设计中涉及到的后台Web功能，定义UI界面与后台Web服务的HTTP交互格式。

## 1.3 更新版本


|  更新时间  | 更新章节  |更新内容  |
|  ----  | ----  | ---- |
| 2021-11-02 | 全部章节 |将result参数修改为code参数  |
| 2021-11-02 | 3.1.4.1 个人信息 | 将响应参数中的org修改为college |
| 2021-11-02 | 3.1.4.3 发送邮件| 将mid修改为msg，code的含义修改为返回mid |
| 2021-11-02 | 3.4.7 收藏帖子 | 删除result参数，code的含义重新定义 |
| 2021-11-02 | 3.2.2 获取公告 | 将响应参数num修改为code |
| 2021-11-03 | 2 接口设计规范 | 将tip字段修改为message字段，将time字段修改为timestamp字段 |
| 2021-11-03 | 3.6.3.3 邮件内容 | 将content字段修改为msg字段 |
| 2021-11-03 | 3.6.4 获取个人信息 | 将aname、aprofile字段修改为name、profile，将org字段修改为college |
| 2021-11-03 | 3.6.5 修改公告 | 将newContent字段修改为newProfile字段 |
| 2021-11-03 | 3.7.4 修改论坛信息 | 订正请求参数类型，订正请求URL |
| 2021-11-06 | 用户、社团相关头像接口 | 均采用URL模式而不使用Base64 |
| 2021-11-07 | 新增下载文件接口 3.1.4.8 | 下载文件接口 |
| 2021-11-07 | 3.1.1.2 登出 | 废弃该接口 |
| 2021-11-09 | 新增 3.1.4.9 | 获取帖子类型列表 |
| 2021-11-21 | 新增 3.1.4.10、3.1.4.11 | 获取用户信息以及获取社团信息 |




# 2. 接口设计规范

接口设计文档包括如下几项：

- HTTP协议：规定当前API的HTTP协议版本
- 请求地址 ： 规定了当前API请求所指向的URI地址
- 请求方法：分为GET和Post，是当前请求的方式。
- 封装：数据的封装类型，有数据和文件两种
- 请求参数：携带在请求头中的参数
- 应答对象：服务器响应浏览器的数据，大部分是一个JSON对象

接口分为请求部分和响应部分， 请求分为 **请求方式和请求参数**
响应分为 **响应信息和响应数据**

前端每次请求后台时，若用户已登录，那么应该每次都在请求头(Header)上携带Token令牌以便后台服务器做权限核验

响应信息体为（一个响应包含信息和数据，不可或缺，下面接口设计将不再展示该部分）：
<table  >
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>参数名</th><th colspan=2>参数描述</th ></tr>
    <tr><td>status</td><td colspan=2>响应状态码：200等</td></tr>
    <tr><td>message</td><td colspan=2>提示信息</td></tr>
    <tr><td>timestamp</td><td colspan=2>响应时间戳</td></tr>
</table>


# 3. UI与后台Web服务的HTTP协议定义

在这一章，我们对UI中所有需要的协议进行定义。为了便于与《02-用户UI过程以及UI功能设计书》对应和查找，对每个协议，我们均明确标出对应《02-用户UI过程以及UI功能设计书》中的界面。在下面的描述中，UI界面文档，也就是《02-用户UI过程以及UI功能设计书》。

## 3.1 用户操作相关接口

### 3.1.1 登录/登出

#### 3.1.1.1 登录（Finish）

对应UI界面文档中2.1节中的登录界面，也就是图UI界面文档中2.2 的登录界面。该界面对用户的身份进行后台Web验证。
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/user/login</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>loginType</td><td>Integer</td><td>0:代表使用学号密码登录，1：代表使用手机密码登录</td></tr>
    <tr><td>account</td><td>String</td><td>登录用户名(学号、手机)</td></tr>
    <tr><td>password</td><td>String</td><td>用户密码（MD5加密）</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：登录成功，非0：登陆失败</td></tr>
    <tr><td>token</td><td colspan=2>code为0时返回该字段，是一个Token值，否则为空</td></tr>
    <tr><td>uid</td><td colspan=2>code为0时返回该字段，是用户的ID字段，否则为空</td></tr>
    <tr><td>msg</td><td colspan=2>当code非0时原因，否则为空</td></tr>
</table>


#### 3.1.1.2 登出 (Remove)

登出是用户安全退出系统的途径，可以防止用户信息泄露。在此情况下，可使用如下协议进行登出请求。
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/user/logout</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td colspan=3>  无  </td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td colspan=3>  无  </td></tr>
</table>

### 3.1.2 注册 (Finish)

注册是学生使用该系统的必要途径，因此在注册时需要提供学生所有的信息，当有新用户注册时，可以使用如下协议进行注册请求：
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/user/register</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>realName</td><td>String</td><td>中文字符串，是用户的真实姓名</td></tr>
    <tr><td>userName</td><td>String</td><td>中文字符串，是用户的昵称</td></tr>
    <tr><td>studentID</td><td>String</td><td>数字字符串，是用户的学号或老师教工号(唯一)</td></tr>
    <tr><td>college</td><td>Integer</td><td>所属学院，用一个整数型代指，是coid</td></tr>
    <tr><td>password</td><td>String</td><td>用户密码(MD5加密)</td></tr>
    <tr><td>email</td><td>String</td><td>邮箱字符串</td></tr>
    <tr><td>phone</td><td>String</td><td>数字字符串，是用户的手机号码(唯一)</td></tr>
    <tr><td>sex</td><td>Integer</td><td>0:男性  1:女性 </td></tr>
    <tr><td>birth</td><td>String</td><td>日期字符串，2000-00-00格式</td></tr>
    <tr><td>headImage</td><td>String</td><td>BASE64编码字符串，用户头像</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：注册成功，非0：注册失败</td></tr>
    <tr><td>msg</td><td colspan=2>当code非0时原因，否则为空</td></tr>
</table>


### 3.1.3 忘记密码 (Finish)

用户一旦忘记密码，即可以通过该接口找回密码，找回密码需要学号、手机号以及邮箱号同时一致时方能够找回
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/user/forget-password</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>studentID</td><td>String</td><td>数字字符串，学生的学号或老师的教工号</td></tr>
    <tr><td>phone</td><td>String</td><td>数字字符串，用户的手机号</td></tr>
    <tr><td>email</td><td>String</td><td>邮箱字符串</td></tr>
    <tr><td>password</td><td>String</td><td>新的用户密码（MD5加密）</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：修改成功，非0：修改失败</td></tr>
    <tr><td>msg</td><td colspan=2>当code非0时原因，否则为空</td></tr>
</table>


### 3.1.4 判断学号与手机号码是否唯一 (Finish)

在注册时，由于学号与手机号码在数据库中的值是唯一的，因此可以在用户输入完学号与手机号码之后，进行一次判断，提前告知用户学号与手机号是否被注册了，降低用户的试错成本。

该接口的具体设计如下：
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/uni-variable</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>studentID</td><td>String</td><td>学号</td></tr>
    <tr><td>phone</td><td>String</td><td>手机号码</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>结果代码：0->均不重复；1->学号重复；2->手机重复;3->均重复 </td></tr>
    <tr><td>msg</td><td colspan=2>描述信息</td></tr>
</table>

### 3.1.5 通用接口

#### 3.1.4.1 获取个人信息 (Finish)

该接口用于各个页面获取登录用户的个人信息，由于经常重复使用，因此单独成节。具体的接口描述如下
<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-common-person-information</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>id值，是用户的唯一标识符</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：获取成功 非0：获取失败，可能是Token不对</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
    <tr><th>data.user</td><th colspan=2>result为0时返回如下用户信息</td></tr>
    <tr><td>userName</td><td colspan=2>用户昵称</td></tr>
    <tr><td>realName</td><td colspan=2>用户真实姓名</td></tr>
    <tr><td>studentID</td><td colspan=2>学号或教工号</td></tr>
    <tr><td>college</td><td colspan=2>用户所属学院</td></tr>
    <tr><td>intro</td><td colspan=2>用户简介</td></tr>
	<tr><td>level</td><td colspan=2>0:普通用户 1：社团管理员 2：学校管理员</td></tr>
    <tr><td>headImage</td><td colspan=2>用户头像 BASE64编码字符串</td></tr>
</table>


#### 3.1.4.2 图片上传接口  (Finish)

由于在发布帖子和一些其他的操作需要用到图片时，需要调用该接口，将图片转为BASE64上传到云端，通过服务器转码保存到云端，再返回一个URL给前端，节省数据库开销。

具体的接口设计如下：

<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/upload-image</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>imageBASE64</td><td>String</td><td>图片的BASE64字符串编码</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>结果代码：0，转码成功 1，转码失败 </td></tr>
    <tr><td>msg</td><td colspan=2>如果code为0，则返回图片的URL地址，如果code为1，则返回错误信息</td></tr>
</table>


#### 3.1.4.3 邮件发送接口  (Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/send-email</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
 	<tr><td>isSystem</td><td>Integer</td><td>是否是系统发送，0为是用户发送，1为系统发送</td></tr>
	<tr><td>mailType</td><td>Integer</td><td>邮件类型，0：发送给用户，1：发送给社团</td></tr>
    <tr><td>fromuid</td><td>Integer</td><td>ID值，发送者UID(isSystem为1时无需该字段)</td></tr>
	<tr><td>touid</td><td>Integer</td><td>ID值，收件人UID（mailType为1时为aid）</td></tr>
	<tr><td>title</td><td>String</td><td>邮件标题</td></tr>
	<tr><td>content</td><td>String</td><td>邮件内容</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示发送是否成功，失败返回-1，成功返回一个mid</td></tr>
    <tr><td>msg</td><td colspan=2>描述信息</td></tr>
</table>




#### 3.1.4.4 获取社员列表 (Finish)

该接口是获取简单的社员信息，用以选择社员

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-member-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
 	<tr><td>aid</td><td>Integer</td><td>社团唯一标识符，为0时获取全部用户信息</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示发送是否成功，失败返回-1，成功返回数据条数</td></tr>
	<tr><th>data.members[]</td><th colspan=2>用户信息</td></tr>
    <tr><td>uid</td><td colspan=2>用户uid</td></tr>
	<tr><td>username</td><td colspan=2>用户昵称</td></tr>
	<tr><td>realname</td><td colspan=2>用户真实姓名</td></tr>
	<tr><td>studentid</td><td colspan=2>用户学号</td></tr>
</table>




#### 3.1.4.5 获取所有社团列表(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/get-association-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>无</th></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示发送是否成功，失败返回-1，成功返回数据条数</td></tr>
	<tr><th>data.ass[]</td><th colspan=2>社团信息</td></tr>
    <tr><td>aid</td><td colspan=2>社团uid</td></tr>
	<tr><td>name</td><td colspan=2>社团名称</td></tr>
</table>


#### 3.1.4.6 文件上传接口 (Finish)

用途用法如3.1.4.2节相似，不再赘述，同样返回一个URL链接

<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/upload-file</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">multipart/form-data</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>file</td><td>file</td><td>二进制的文件数据</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>结果代码：0，转码成功 1，转码失败 </td></tr>
    <tr><td>msg</td><td colspan=2>如果code为0，则返回文件的URL地址，如果code为1，则返回错误信息</td></tr>
</table>

#### 3.1.4.7 获取所有学院列表 (Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/get-college-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>无</th></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示发送是否成功，失败返回-1，成功返回数据条数</td></tr>
	<tr><th>data.college[]</td><th colspan=2>社团信息</td></tr>
    <tr><td>coid</td><td colspan=2>学院ID，coid</td></tr>
	<tr><td>name</td><td colspan=2>学院名称</td></tr>
</table>

#### 3.1.4.8 下载文件接口 (Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/download-file</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><td>fileName</td><td>String</td><td>预下载的文件名字</td></tr>
    <tr><td>uid</td><td>Integer</td><td>下载者的UID</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>file</td><th colspan=2>文件实体</td></tr>
</table>

#### 3.1.4.9 获取总的帖子类型（Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/tool/get-post-type</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>无</th></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data[]</td><th colspan=2>帖子类型数组</td></tr>
</table>

#### 3.1.4.10 通过社团ID获取社团信息（Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-association-info</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><td>aid</td><td>Integer</td><td>社团AID</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>表示请求是否成功，0为成功，-1为失败</td></tr>
	<tr><td>msg</td><td colspan=2>失败或成功的原因和信息</td></tr>
    <tr><td>leader_id</td><td colspan=2>社团名称</td></tr>
    <tr><td>name</td><td colspan=2>社团名称</td></tr>
	<tr><td>logo</td><td colspan=2>社团Logo，是一个连接</td></tr>
	<tr><td>intro</td><td colspan=2>社团简介</td></tr>
	<tr><td>parent_organization</td><td colspan=2>社团上级组织</td></tr>
    <tr><td>create_time</td><td colspan=2>社团创建时间,是一个时间戳，因为懒得处理</td></tr>
</table>


## 3.2 首页相关接口

首页用来展示一些系统公告，是针对UI界面设计中2.1所设计的接口文档，首页需要获取简单的用户信息以及系统公告内容。

### 3.2.1 获取简单的用户信息（Finish）

这个接口能够通过Token(在Cookie中)和uid获得一些必要的用户信息，例如**个人简介(默认为空)，头像，昵称，姓名，学号等**
接口具体设计见 - [3.1.4.1 获取个人信息](#3141-获取个人信息)

### 3.2.2 获取简要的公告信息（Finish）

首页可以获取一些简单的公共公告信息展示在页面上，因为首页只需要展示简略的公告，因此此处应当获取简要版的信息

- 公告标题
- 公告发布日期
- 公告的pid(可以通过pid直接进入页面)

<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-simple-notice</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><td colspan=3>无</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>公告数量，是一个整数</td></tr>
    <tr><th>data.notice[]</td><th colspan=2>当数量不为0时返回如下Json对象数组</td></tr>
    <tr><td>title</td><td colspan=2>公告标题</td></tr>
    <tr><td>date</td><td colspan=2>公告日期</td></tr>
    <tr><td>pid</td><td colspan=2>pid，帖子唯一标识符，可以直接进入帖子页面</td></tr>
</table>


### 3.2.3 创建社团审批 （Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/create-ass</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>assname</td><td>String</td><td>社团名字</td></tr>
    <tr><td>assintro</td><td>String</td><td>社团简介</td></tr>
	<tr><td>note</td><td>String</td><td>申请备注</td></tr>
    <tr><td>assprofile</td><td>String</td><td>社团头像URL</td></tr>
    <tr><td>uid</td><td>Integer</td><td>申请人UID</td></tr>
    <tr><td>org</td><td>String</td><td>社团所属组织</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回一个caid => create association ID</td></tr>
	<tr><td>msg</td><td colspan=2></td></tr>
</table>



## 3.3 论坛页面相关接口

对于着UI界面设计文档中的2-4、2-5节 公共交流论坛页面是本系统第一个重要的功能界面，获取其实现其页面数据需要多个接口共同协作，由于该页面分为游客用户以及注册用户两种不同大的用户群，因此获取数据将统合为两个接口即可，即**
获取用户数据以及获取帖子数据**。以及页面还有其他的功能接口，例如**发布帖子等**功能，将在本节详细介绍。

**这里是包括了公共论坛页面以及社团论坛页面的接口**

### 3.3.1 获取论坛顶部数据接口（Finish）

该接口用以获取用户数据，加入的社团以及学校所有的社团也在此接口一并获取 获取用户数据的接口见：- [3.1.4.1 获取个人信息](#3141-获取个人信息)

<table  >
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-show-data</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>id值，是用户的唯一标识符</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1获取失败，0没有获取到数据，正整数值：数据数量(是当前学校社团总数)</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
    <tr><th>data.ass[]</td><th colspan=2>result为0时返回如下社团信息数组</td></tr>
    <tr><td>assName</td><td colspan=2>社团名称</td></tr>
    <tr><td>aid</td><td colspan=2>ID值，社团唯一标识符</td></tr>
    <tr><td>isJoin</td><td colspan=2>0为未加入，1为已加入</td></tr>
    <tr><td>assImage</td><td colspan=2>社团Logo，如果isJoin字段为1，则读取该字段</td></tr>
</table>



### 3.3.2 获取帖子列表数据接口（Finish）

该接口用以给注册用户以及非注册用户获取帖子列表使用，能够得到帖子数据的基本概要，需要传入一个页面参数，该参数可空，空则为第一页，该接口设计具体如下：
<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-post-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>aid</td><td>Integer</td><td>一个整数值，等于社团的aid,公共交流板块值为0</td></tr>
	<tr><td>type</td><td>Integer</td><td>不可空，需要什么类型的帖子，默认为
        1->公告帖子，2->普通帖子</td></tr>
    <tr><td>page</td><td>Integer</td><td>可空，页面值，默认为1,公告不需要page以及limit</td></tr>
	<tr><td>limit</td><td>Integer</td><td>可空，每页展示几页，默认为10，公告不需要page以及limit</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>一个整数值，返回所有帖子的总数量</td></tr>
    <tr><th>data.posts[]</td><th colspan=2>code字段不为0时返回该列表数组</td></tr>
    <tr><td>pid</td><td colspan=2>ID值，帖子唯一标识符</td></tr>
    <tr><td>postType</td><td colspan=2>文本数据，帖子类型</td></tr>
    <tr><td>postTitle</td><td colspan=2>帖子标题</td></tr>
    <tr><td>postAuthor</td><td colspan=2>帖子作者昵称</td></tr>
    <tr><td>replies</td><td colspan=2>回复数量</td></tr>
    <tr><td>replyTime</td><td colspan=2>最后回复时间</td></tr>
    <tr><td>releaseTime</td><td colspan=2>发布时间</td></tr>
</table>


### 3.3.3 发表帖子接口 (Finish)

用户如果要在此处发布帖子，则需要调用该接口，提交发布的帖子内容，该接口的具体设计如下：
<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/release-post</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标志</td></tr>
    <tr><td>releaseArea</td><td>Integer</td><td>发表域，为社团的aid，公共论坛值为0</td></tr>
    <tr><td>postType</td><td>String</td><td>帖子类型，字符串</td></tr>
    <tr><td>postTitle</td><td>String</td><td>帖子标题</td></tr>
    <tr><td>content</td><td>String</td><td>帖子内容，带格式的文本串</td></tr>
    <tr><td>tags</td><td>String</td><td>逗号分隔的关键词标签</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1为失败，成功返回PID</td></tr>
    <tr><td>msg</td><td colspan=2>返回提示信息，提示成功或者失败以及失败的原因</td></tr>
</table>


### 3.3.4 论坛页面获取相关个人和论坛信息接口 (Finish)

用户点击进入论坛页面时，可以用该接口获取相关的个人信息以及论坛的基础信息。接口的具体设计如下：

个人信息获取接口见 - [3.1.4.1 获取个人信息](#3141-获取个人信息)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-ass-information</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标志</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，论坛的aid值</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0为获取成功，1为获取失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
    <tr><td>permissionCode</td><td colspan=2>权限代码，2为社团管理员 1为用户以加入该社团，0为用户未加入该社团</td></tr>
    <tr><th>data.ass</th><td colspan=2>result为0是返回该字段，是协会数据</td></tr>
    <tr><td>assName</td><td colspan=2>社团名称</td></tr>
    <tr><td>assIntro</td><td colspan=2>社团简介</td></tr>
    <tr><td>assImage</td><td colspan=2>社团Logo</td></tr>
    <tr><td>assOrg</td><td colspan=2>社团所属组织</td></tr>
    <tr><td>assHead</td><td colspan=2>社团负责人</td></tr>
</table>



### 3.3.5 申请加入社团接口 （Finish）

非成员社团用户可以在社团页面申请加入社团，该接口需要提供一个uid、aid、以及申请备注放能够提交成功

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/join-association</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标志</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，论坛的aid值</td></tr>
	<tr><td>note</td><td>String</td><td>申请备注</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0为申请成功，1为申请失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
</table>


### 3.3.6 获取社团活动列表接口(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-action-overview</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标志</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，论坛的aid值</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1为获取失败，0为没有数据，正整数表示获取到的数据数量</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
	<tr><th>data.action[]</td><th colspan=2>活动列表</td></tr>
	<tr><td>actid</td><td colspan=2>ID值，活动唯一标识符</td></tr>
	<tr><td>title</td><td colspan=2>活动的标题</td></tr>
	<tr><td>position</td><td colspan=2>活动举办地点</td></tr>
	<tr><td>date</td><td colspan=2>活动的开始时间</td></tr>
	<tr><td>isAttend</td><td colspan=2>是否已参加该活动，0为未参加，1为以参加</td></tr>
</table>



### 3.3.7 	获取活动详细信息接口（Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-action-info</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>uid</td><td>Integer</td><td>ID值，用户的唯一标识符</td></tr>
    <tr><td>actid</td><td>Integer</td><td>ID值，活动的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0成功，1失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
	<tr><td>title</td><td colspan=2>活动标题</td></tr>
	<tr><td>content</td><td colspan=2>活动正文内容</td></tr>
	<tr><td>releaseDate</td><td colspan=2>活动发布时间</td></tr>
	<tr><td>startDate</td><td colspan=2>活动的开始时间</td></tr>
	<tr><td>overDate</td><td colspan=2>活动的结束时间</td></tr>
	<tr><td>position</td><td colspan=2>活动举办的地点</td></tr>
	<tr><td>status</td><td colspan=2>活动参加状态码，0为可参加，1为没有权限参加，2为已参加</td></tr>
</table>



### 3.3.8 申请参加活动接口（Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/action/participate</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>uid</td><td>Integer</td><td>ID值，用户的唯一标识符</td></tr>
    <tr><td>actid</td><td>Integer</td><td>ID值，活动的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0成功，1失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
</table>


## 3.4 帖子页面相关接口

帖子页面是用以展示相关帖子主题与回复的页面，同时由于顶部栏的存在，还是需要获取相关的个人信息

帖子页面需要的相关接口有：

- 获取个人信息
- 获取帖子与发帖人信息
- 获取回复与回复人信息
- 提交回复
- 删除帖子(管理员权限)
- 删除回复(管理员和发帖人权限)
- 修改帖子(发帖人权限)
- 收藏帖子(普通用户权限)

### 3.4.1 获取个人信息接口

该接口以及整合至：- [3.1.4.1 获取个人信息](#3141-获取个人信息)

### 3.4.2 获取帖子与发帖人信息（Finish）

该接口可以用于获得帖子主体与发帖人的信息，需要返回两个字段，一个表示用户是否存在，一个表示帖子是否存在。
<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-post-page-info</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>pid</td><td>Integer</td><td>ID值，帖子的唯一标识符</td></tr>
	<tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0为获取成功，1为获取失败，且帖子不存在</td></tr>
    <tr><td>permissionCode</td><td colspan=2>权限代码，1为管理员 ，0为普通用户,2为用户不存在(是用发帖人的权限)</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
    <tr><th>data.post</th><td colspan=2>result为0是返回该字段，是帖子数据</td></tr>
    <tr><td>title</td><td colspan=2>帖子标题</td></tr>
    <tr><td>content</td><td colspan=2>帖子内容</td></tr>
    <tr><td>isFavorite</td><td colspan=2>是否收藏 0为未收藏 1为以收藏</td></tr>
    <tr><td>releaseDate</td><td colspan=2>发布日期</td></tr>
    <tr><td>tags[]</td><td colspan=2>帖子标签，是一个字符串数组</td></tr>
    <tr><th>data.master</th><td colspan=2>permissionCode不为2时返回该字段，显示帖子的作者信息</td></tr>
    <tr><td>muid</td><td colspan=2>Master-User-ID,作者的UID</td></tr>
    <tr><td>username</td><td colspan=2>用户昵称</td></tr>
    <tr><td>org</td><td colspan=2>用户所属学院，是一个文本型</td></tr>
    <tr><td>intro</td><td colspan=2>用户简介</td></tr>
    <tr><td>image</td><td colspan=2>Base64编码字符串，是用户头像</td></tr>
</table>



### 3.4.3 获取回复和回复人列表(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/get-discuss-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>pid</td><td>Integer</td><td>ID值，帖子的唯一标识符</td></tr>
    <tr><td>page</td><td>Integer</td><td>可空，页码 默认为1</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1获取失败，获取成功返回总页数</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
    <tr><th>data.discuss[]</th><td colspan=2>result为0是返回该字段，是回复数据数组</td></tr>
    <tr><td>page</td><td colspan=2>第几页 是一个整数</td></tr>
    <tr><th>data.discuss.author</th><td colspan=2>评论人数据</td></tr>
    <tr><td>cuid</td><td colspan=2>评论人的UID</td></tr>
    <tr><td>username</td><td colspan=2>评论人的昵称</td></tr>
    <tr><td>userImage</td><td colspan=2>评论人的头像</td></tr>
    <tr><th>data.discuss.content</th><td colspan=2>评论数据</td></tr>
    <tr><td>cid</td><td colspan=2>ID值，回复的唯一标识符</td></tr>
    <tr><td>text</td><td colspan=2>回复的文本内容</td></tr>
    <tr><td>releaseDate</td><td colspan=2>回复的发表时间</td></tr>
</table>


### 3.4.4 提交回复（Finish）

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/release-discuss</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>pid</td><td>Integer</td><td>ID值，帖子的唯一标识符</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户唯一标识符</td></tr>
    <tr><td>content</td><td>String</td><td>无格式文本内容</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1提交失败，提交成功返回cid</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
</table>


### 3.4.5 删除帖子以及回复（Finish）

帖子的发布者以及社团的管理员、学校的管理员有权删除帖子以及回复
<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/delete-post-discuss</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>requestType</td><td>Integer</td><td>请求类型：0为删除帖子，1为删除回复</td></tr>
    <tr><td>typeid</td><td>Integer</td><td>ID值，当请求类型为0时是pid，为1时是cid</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0为删除成功，1为删除失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回失败的原因</td></tr>
</table>


### 3.4.6 修改帖子(Finish)

帖子的作者可以修改帖子的内容（该权限仅作者独有，管理员也无权修改，仅能做删除操作）
<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/modify-post</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>pid</td><td>Integer</td><td>ID值，帖子唯一标识符</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，当前登录用户的ID值</td></tr>
    <tr><td>newContent</td><td>String</td><td>新内容</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0为修改成功，非0为修改失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



### 3.4.7 收藏帖子 (Finish)

用户可以收藏帖子，也可以取消收藏，由于收藏只有两种状态，因此该接口是一个开关，只需要请求即可自动切换收藏状态

接口的具体设计如下：

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/favoriteProcess</td></tr>
    <tr><th>请求方法</th><td colspan="2">Get</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>pid</td><td>Integer</td><td>ID值，帖子唯一标识符</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，当前登录用户的ID值</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>-1为修改失败， 0为收藏成功，1为取消收藏成功</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>


## 3.5 搜索页面相关接口(Finish) 

搜索页面是用户用以搜索站内相关内容的一个接口平台，用户可以搜索帖子、搜索用户、并使用帖子类型对搜索结果进行筛选（前端实现）（分页也在前端实现）

该接口的具体设计如下：

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/bbs/search</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>type</td><td>Integer</td><td>一个整数，0表示搜索帖子和回复(默认) 1表示搜索用户</td></tr>
    <tr><td>keyword</td><td>String</td><td>关键词</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0表示没有结果 整数表示结果数量，其他负数表示出错</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
	<tr><th>data.posts[]</td><th colspan=2>帖子数据数组</td></tr>
	<tr><td>pid</td><td colspan=2>帖子pid</td></tr>
	<tr><td>title</td><td colspan=2>帖子标题</td></tr>
	<tr><td>content</td><td colspan=2>帖子内容</td></tr>
	<tr><td>aname</td><td colspan=2>论坛名字</td></tr>
	<tr><td>aid</td><td colspan=2>ID值，论坛唯一标识符</td></tr>
	<tr><td>authorName</td><td colspan=2>作者昵称</td></tr>
 	<tr><td>authorUID</td><td colspan=2>作者UID</td></tr>
	<tr><td>releaseDate</td><td colspan=2>发布日期</td></tr>
	<tr><th>data.users[]</td><th colspan=2>用户数据结果数组</td></tr>
	<tr><td>uid</td><td colspan=2>ID值，用户唯一标识符</td></tr>
    <tr><td>username</td><td colspan=2>用户昵称</td></tr>
    <tr><td>intro</td><td colspan=2>用户简介</td></tr>
    <tr><td>numberOfPosts</td><td colspan=2>用户发帖数量</td></tr>
    <tr><td>image</td><td colspan=2>用户头像</td></tr>
</table>


## 3.6 个人空间相关接口

该节描述的是个人空间相关的一些接口，包括用户的权限验证，获取以及修改信息的接口

### 3.6.1 权限验证 (Finish)

用该接口来验证个人空间的相关权限，返回一个整数代码，表示当前权限状态

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/get-zone-status</td></tr>
    <tr><th>请求方法</th><td colspan="2">Get</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>muid</td><td>Integer</td><td>空间主人的UID->master user id</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>权限状态 0表示是自己空间，1表示是他人可访问空间，2表示是他人不可访问空间</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>

### 3.6.2 获取我的（他的）帖子和收藏 (Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/get-post</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>zone-uid</td><td>Integer</td><td>空间主人的UID->master user id</td></tr>
	<tr><td>type</td><td>Integer</td><td>查询类型：0为帖子列表 1为收藏收藏列表</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>返回一个整数，表示获取到的数据数量</td></tr>
    <tr><th>data.posts[]</td><th colspan=2>当code不为0时，返回该字段，是一个对象数组</td></tr>
    <tr><td>aid</td><td colspan=2>ID值，该帖子所在社团的aid号</td></tr>
    <tr><td>aname</td><td colspan=2>帖子所在社团的名称</td></tr>
    <tr><td>title</td><td colspan=2>帖子标题</td></tr>
    <tr><td>pid</td><td colspan=2>id值 帖子ID</td></tr>
    <tr><td>date</td><td colspan=2>帖子的发布日期，或者收藏的日期</td></tr>
    <tr><td>replies</td><td colspan=2>帖子的回复数</td></tr>
	<tr><td>aimage</td><td colspan=2>帖子所在社团LOGO</td></tr>
</table>


### 3.6.3 获取我的邮件

个人空间可以获取我的所有邮件，可以提示已读或者未读，也可以进行清空操作

#### 3.6.3.1 清空邮箱(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/delete-mail</td></tr>
    <tr><th>请求方法</th><td colspan="2">Get</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户ID-UID</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>清除成功则返回删除的条数，若失败，返回-1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>

#### 3.6.3.2 获取邮箱概要数据(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-mails</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>返回一个整数，表示获取到的数据数量</td></tr>
    <tr><th>data.mail[]</td><th colspan=2>当code不为0时，返回该字段，是一个对象数组</td></tr>
    <tr><td>mid</td><td colspan=2>mail-ID:邮件的唯一标识符</td></tr>
    <tr><td>title</td><td colspan=2>邮件标题</td></tr>
    <tr><td>date</td><td colspan=2>邮件发送日期</td></tr>
    <tr><td>isRead</td><td colspan=2>i是否已阅读 0为未阅读，1为已阅读</td></tr>
	<tr><td>isSystem</td><td colspan=2>是否是系统发送，1为系统发送，0为用户发送</td></tr>
    <tr><td>from</td><td colspan=2>邮件发送者昵称</td></tr>
</table>


#### 3.6.3.3 获取邮件内容(Finish)

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-mail-content</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>mid</td><td>Integer</td><td>ID值，邮件的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示获取是否成功，成功返回1，失败返回0</td></tr>
    <tr><td>msg</td><td colspan=2>邮件内容</td></tr>
</table>



### 3.6.4 获取我的个人信息和公告

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/get-information</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>ID值，用户的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>0表示获取失败，1表示获取成功</td></tr>
	<tr><td>assNum</td><td colspan=2>用户加入的社团数量</td></tr>
	<tr><td>notice</td><td colspan=2>公告内容</td></tr>
    <tr><th>data.userdata</td><th colspan=2>当code不为0时，返回该字段，是一个用户数据</td></tr>
	<tr><td>uid</td><td colspan=2>用户唯一标识符</td></tr>
    <tr><td>username</td><td colspan=2>用户昵称</td></tr>
    <tr><td>realname</td><td colspan=2>用户真实姓名</td></tr>
    <tr><td>profile</td><td colspan=2>用户头像</td></tr>
    <tr><td>phone</td><td colspan=2>用户电话号码</td></tr>
	<tr><td>email</td><td colspan=2>用户电子邮箱</td></tr>
    <tr><td>college</td><td colspan=2>用户所属院系</td></tr>
	<tr><td>birth</td><td colspan=2>用户生日</td></tr>
	<tr><td>numpost</td><td colspan=2>发帖数量(包括回复)</td></tr>
	<tr><th>data.joinass[]</td><th colspan=2>返回用户加入的社团，是一个对象数组</td></tr>
	<tr><td>aid</td><td colspan=2>社团唯一标识符</td></tr>
	<tr><td>name</td><td colspan=2>社团名称</td></tr>
	<tr><td>profile</td><td colspan=2>社团头像Logo</td></tr>
</table>



### 3.6.5 修改公告

用户可以在空间页面修改公告，只需要提交一个uid和修改后的新公告即可

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-notice</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newProfile</td><td>String</td><td>公告新内容</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



### 3.6.6 修改个人信息

用户可以在个人空间页面修改个人信息，只需要点击相关信息，就可以执行修改操作，因此每一个修改操作都对应一个接口，该部分的接口设置如下：

#### 3.6.6.1 修改昵称

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-name</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newProfile</td><td>String</td><td>新昵称</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



#### 3.6.6.2 修改简介

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-intro</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newProfile</td><td>String</td><td>新头像，是一个Base64编码字符串</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



#### 3.6.6.3 修改头像

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-profile</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newProfile</td><td>String</td><td>新头像，是一个Base64编码字符串</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>


#### 3.6.6.4 修改电子邮箱

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-email</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newEmail</td><td>String</td><td>新邮箱</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>


#### 3.6.6.5 修改生日

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/zone/update-birth</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>uid</td><td>Integer</td><td>用户唯一标识符</td></tr>
	<tr><td>newBirth</td><td>String</td><td>新的生日</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示修改是否成功，成功返回0，失败返回1</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>

## 3.7 社团管理后台相关接口

社团管理后台是社团管理员用于管理相关事务的一个后台系统，此部分的接口权限需要访问者拥有社团管理员的权限方可调用，否则会返回一个权限错误的页面。

### 3.7.1 首页相关接口

该部分的接口用于首页展示信息使用，一个是固定展示信息，一个是社团邮箱信息,一个是数据可视化展示信息。

#### 3.7.1.1 固定展示信息

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-fixed-show-info</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，社团的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示获取是否成功，成功返回1，失败返回0</td></tr>
	<tr><th>data.assInfo</td><th colspan=2>社团信息</td></tr>
    <tr><td>name</td><td colspan=2>社团名字</td></tr>
	<tr><td>intro</td><td colspan=2>社团简介</td></tr>
	<tr><td>principal</td><td colspan=2>社团负责人</td></tr>
	<tr><td>org</td><td colspan=2>社团所属组织</td></tr>
	<tr><td>profile</td><td colspan=2>社团头像 BASE64编码</td></tr>
	<tr><th>data.showInfo</td><th colspan=2>社团信息</td></tr>
	<tr><td>headcount</td><td colspan=2>社团总人数</td></tr>
    <tr><td>postcount</td><td colspan=2>论坛总帖数</td></tr>
    <tr><td>actioncount</td><td colspan=2>待举行活动的数量</td></tr>
</table>


#### 3.7.1.2 社团邮箱数据

这里可以显示社团的邮箱，点击邮箱里的邮件会弹出一个新的窗口展示邮件内容，点击邮箱的右上角的+号可以发送邮件

##### 3.7.1.2.1 显示摘要列表

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-ass-mails</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，用户的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>返回一个整数，表示获取到的数据数量</td></tr>
    <tr><th>data.mail[]</td><th colspan=2>当code不为0时，返回该字段，是一个对象数组</td></tr>
    <tr><td>mid</td><td colspan=2>mail-ID:邮件的唯一标识符</td></tr>
    <tr><td>title</td><td colspan=2>邮件标题</td></tr>
    <tr><td>date</td><td colspan=2>邮件发送日期</td></tr>
    <tr><td>isRead</td><td colspan=2>i是否已阅读 0为未阅读，1为已阅读</td></tr>
	<tr><td>isSystem</td><td colspan=2>是否是系统发送，1为系统发送，0为用户发送</td></tr>
    <tr><td>from</td><td colspan=2>邮件发送者昵称</td></tr>
</table>


##### 3.7.1.2.2 展示邮件信息

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-mail-content</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>mid</td><td>Integer</td><td>ID值，邮件的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>表示获取是否成功，成功返回1，失败返回0</td></tr>
    <tr><td>msg</td><td colspan=2>邮件内容</td></tr>
</table>



#### 3.7.1.3 数据可视化接口

数据可视化接口返回的是一组数组，用于Vue生成可视化图形

##### 3.7.1.3.1 个人活跃度

该接口会返回个人活跃度前10的用户的活跃度信息。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-person-act</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，社团的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量，默认为10</td></tr>
    <tr><th>data.data[]</td><th colspan=2>数据体</td></tr>
    <tr><td>id</td><td colspan=2>用户UID</td></tr>
    <tr><td>username</td><td colspan=2>用户昵称</td></tr>
    <tr><td>num</td><td colspan=2>数值(0-1直接的小数)</td></tr>
</table>



##### 3.7.1.3.2 社团每天活跃度

这里展示了社团从创立以来每天的活跃度变化

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-daily-act</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>aid</td><td>Integer</td><td>ID值，社团的唯一标识符</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量，默认为15</td></tr>
    <tr><th>data.daily[]</td><th colspan=2>数据体</td></tr>
    <tr><td>date</td><td colspan=2>日期时间</td></tr>
    <tr><td>num</td><td colspan=2>数值(0-无限大)</td></tr>
</table>


### 3.7.2 社团成员管理

#### 3.7.2.1 社团成员管理

##### 3.7.2.1.1 获取社团成员

该接口用来获取社团成员列表，为了提高接口的复用度，将加入一个类型参数，用来区分是获取社团成员或者是全部成员。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-member-information-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td><b>可选</b>。id值，是论坛唯一标志，若参数为0或为空，则默认获取所有用户</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：获取成功 非0：获取失败</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
    <tr><th>data.user[]</td><th colspan=2>result为0时返回如下用户信息</td></tr>
	<tr><td>uid</td><td colspan=2>用户唯一标识符</td></tr>
    <tr><td>userName</td><td colspan=2>用户昵称</td></tr>
    <tr><td>realName</td><td colspan=2>用户真实姓名</td></tr>
    <tr><td>studentID</td><td colspan=2>学号或教工号</td></tr>
    <tr><td>college</td><td colspan=2>用户所属学院</td></tr>
    <tr><td>intro</td><td colspan=2>用户简介</td></tr>
	<tr><td>permisson</td><td colspan=2>用户身份</td></tr>
	<tr><td>birth</td><td colspan=2>用户生日</td></tr>
</table>



##### 3.7.2.1.2 踢出成员

调用该接口可以将某一个成员移除出该社团

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/association/remove-user</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，是论坛唯一标志</td></tr>
	<tr><td>uid</td><td>Integer</td><td>将要移除的用户UID</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：移除成功 非0：移除失败</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
</table>


#### 3.7.2.2 入社审批管理

这里是用户申请加入社团时，社团管理员审批社团成员所调用的接口

##### 3.7.2.2.1 获取审批列表

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/get-join-apply-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，是论坛唯一标志</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：获取成功 非0：获取失败</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
    <tr><th>data.apply[]</td><th colspan=2>result为0时返回如下审批列表信息</td></tr>
	<tr><td>uid</td><td colspan=2>用户唯一标识符</td></tr>
	<tr><td>uaid</td><td colspan=2>user-apply-ID ：用户申请数据唯一标识符</td></tr>
    <tr><td>userName</td><td colspan=2>用户昵称</td></tr>
    <tr><td>realName</td><td colspan=2>用户真实姓名</td></tr>
    <tr><td>studentID</td><td colspan=2>学号或教工号</td></tr>
    <tr><td>college</td><td colspan=2>用户所属学院</td></tr>
    <tr><td>note</td><td colspan=2>申请备注</td></tr>
</table>



##### 3.7.2.2.2 审批操作

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/set-join-apply-status</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>uaid</td><td>Integer</td><td>id值，是审批数据唯一标志</td></tr>
	<tr><td>type</td><td>Integer</td><td>0：拒绝入社申请；1：同意入社申请</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：请求成功 非0：请求失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



### 3.7.3 论坛公告管理接口

#### 3.7.3.1 获取论坛公告

该接口参考 3.3.2节的接口，传递的参数Type值为1 （表示系统公告）

- [3.3.2 获取帖子列表数据接口](#332-获取帖子列表数据接口)

#### 3.7.3.2 发表论坛公告

该功能实际上是一个发布帖子的功能，因此能够复用发表帖子的接口。

使用 3.3.3 节的接口，将其postType值设置为1即可。但是一个论坛最多发表5个公告，如果公告超出则无法发表成功

- [3.3.3 发表帖子接口](#333-发表帖子接口)

#### 3.7.3.2 删除论坛公告

如果论坛公告满五个时，需要调用该接口进行删除，同理，删除论坛的接口也与前文删除帖子使用同一接口。

此时requestType值为0，即删除帖子

- [3.4.5 删除帖子以及回复](#345-删除帖子以及回复)

### 3.7.4 论坛信息修改接口

此接口可以人社团管理员用以修改社团基本信息(论坛基本信息)，调用此接口，并填入相关信息即可完成修改。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/update-ass-info</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，是论坛唯一标识符</td></tr>
	<tr><td>name</td><td>String</td><td>论坛名称</td></tr>
	<tr><td>intro</td><td>String</td><td>论坛简介</td></tr>
	<tr><td>logo</td><td>String</td><td>论坛Logo，Base64编码格式字符串</td></tr>
	<tr><td>headeruid</td><td>Integer</td><td>论坛负责人的UID</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：请求成功 非0：请求失败</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
</table>



### 3.7.5 审批申请

社团在此处可以提交一些审批申请，比如像活动申请啊，场地申请啊，也可以向学校提交年审文件之类的。

#### 3.7.5.1 审批清单

该接口用以拉取该社团所有的审批历史，包括审批中、审批打回、审批通过的都在此处

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/apply/get-association-apply-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，是论坛唯一标识符</td></tr>
	<tr><td>headeruid</td><td>Integer</td><td>论坛负责人的UID</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>请求失败：-1；请求成功则返回数据数量</td></tr>
    <tr><td>msg</td><td colspan=2>返回描述信息</td></tr>
	<tr><th>data.apply</td><th colspan=2>当code大于0时该字段存在</td></tr>
	<tr><td>aaid</td><td colspan=2>Association-Apply-ID：社团申请ID值，唯一标识符</td></tr>
	<tr><td>title</td><td colspan=2>标题</td></tr>
	<tr><td>date</td><td colspan=2>发送时间</td></tr>
	<tr><td>status</td><td colspan=2>审批状态</td></tr>
</table>


#### 3.7.5.2 发送审批

社团管理者可以调用该接口发送审批文档和数据给学校层，同样采用富文本编辑器，图片和附件都采用URL的形式上传。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/submit-association-apply</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，是论坛唯一标识符</td></tr>
	<tr><td>title</td></td><td>String</td><td>审批标题</td></tr>
	<tr><td>content</td></td><td>String</td><td>富文本审批内容</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是一个aaid</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
</table>



### 3.7.6 活动管理

#### 3.7.6.1 活动发布

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/action/release-action</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>title</td></td><td>String</td><td>活动标题</td></tr>
	<tr><td>content</td></td><td>String</td><td>活动内容</td></tr>
	<tr><td>aid</td></td><td>Integer</td><td>活动举办所在社团</td></tr>
	<tr><td>position</td></td><td>String</td><td>活动举办地点</td></tr>
	<tr><td>startTime</td></td><td>String</td><td>开始时间</td></tr>
	<tr><td>endTime</td></td><td>String</td><td>结束时间</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是一个actid</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
</table>



#### 3.7.6.2 活动一览

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/action/get-action-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td></td><td>Integer</td><td>活动举办所在社团aid,为0则显示所有活动</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是action长度</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
	<tr><th>data.action[]</td><th colspan=2>活动数据</td></tr>
    <tr><td>actid</td><td colspan=2>活动ID</td></tr>
    <tr><td>aid</td><td colspan=2>社团ID</td></tr>
    <tr><td>assname</td><td colspan=2>社团名字</td></tr>
    <tr><td>title</td><td colspan=2>活动标题</td></tr>
    <tr><td>content</td><td colspan=2>活动内容</td></tr>
	<tr><td>position</td><td colspan=2>活动举办地点</td></tr>
    <tr><td>startTime</td><td colspan=2>开始时间</td></tr>
    <tr><td>endTime</td><td colspan=2>结束时间</td></tr>
	<tr><td>status</td><td colspan=2>活动状态码：0：待审核，1：已发布，2，审批不过</td></tr>
</table>




#### 3.7.6.3 活动参加人员

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/action/get-action-member</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>actid</td></td><td>Integer</td><td>活动ID</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是action长度</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
	<tr><th>data.action_member[]</td><th colspan=2>参加活动的人员</td></tr>
    <tr><td>uid</td><td colspan=2>用户ID</td></tr>
    <tr><td>username</td><td colspan=2>用户昵称</td></tr>
    <tr><td>studentid</td><td colspan=2>学号</td></tr>
</table>



## 3.8 学校管理后台相关接口

### 3.8.1 首页

#### 3.8.1.1 获取用户总数

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-alluser-number</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><th colspan="3">无</th></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>失败值为“-1”，成功则返回用户数量</td></tr>
	<tr><td>msg</td><td colspan=2>描述信息</td></tr>
</table>



#### 3.8.1.2 加入社团的学生占比

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-user-per</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><th colspan="3">无</th></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>joins</td><td colspan=2>该字段返回加入社团学生的人数</td></tr>
	<tr><td>all</td><td colspan=2>字段返回全部学生的数量</td></tr>
</table>


#### 3.8.1.3 获取当前活动列表

通过调用该接口，Vue可以获得当前系统所有活动。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-action-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>requestType</td><td>Integer</td><td>0：按照论坛不同区分活动列表，1：按照状态不同区分活动列表</td></tr>
	<tr><td>statusType</td></td><td>Integer</td><td>若rt为0，则该参数是论坛aid，若rt为1，则该参数0代表待发布，1：待举行，2：已举行，3：审批不过</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时返回数据数量</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
 	<tr><th>data.action[]</td><th colspan=2>响应数据体</td></tr>
	<tr><td>actid</td><td colspan=2>活动唯一标识符</td></tr>
    <tr><td>title</td><td colspan=2>活动标题</td></tr>
    <tr><td>startTime</td></td><td colspan=2>活动开始日期</td></tr>
    <tr><td>status</td><td colspan=2>活动状态，是一个整数值</td></tr>
</table>



#### 3.8.1.4 社团活跃度(数据可视化)

##### 3.8.1.4.1 单个社团活跃度柱状图数据

该数据纵坐标是活跃度，横坐标是不同的社团

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-ass-act</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><th colspan="3">None</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量，默认为10</td></tr>
    <tr><th>data.data[]</td><th colspan=2>数据体</td></tr>
    <tr><td>id</td><td colspan=2>社团UID</td></tr>
    <tr><td>name</td><td colspan=2>社团名称</td></tr>
    <tr><td>num</td><td colspan=2>数值(0-1直接的小数)</td></tr>
</table>



##### 3.8.1.4.2 整个系统活跃度同比折线图数据

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-sys-act</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><th colspan="3">None</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量，默认为15</td></tr>
    <tr><th>data.daily[]</td><th colspan=2>数据体</td></tr>
    <tr><td>date</td><td colspan=2>日期时间</td></tr>
    <tr><td>num</td><td colspan=2>数值(0-无限大)</td></tr>
</table>


### 3.8.2 社团管理

#### 3.8.2.1 社团信息一览

该接口可以拉取所有的社团信息

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-ass-detail-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><th colspan="3">None</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量</td></tr>
    <tr><th>data.ass[]</td><th colspan=2>数据体</td></tr>
	<tr><td>aid</td><td colspan=2>社团ID，社团的唯一标识符</td></tr>
    <tr><td>name</td><td colspan=2>社团名字</td></tr>
    <tr><td>intro</td><td colspan=2>社团简介</td></tr>
    <tr><td>createTime</td><td colspan=2>创建时间</td></tr>
    <tr><td>uid</td><td colspan=2>社团负责人UID</td></tr>
    <tr><td>headname</td><td colspan=2>社团负责人真实姓名</td></tr>
</table>

#### 3.8.2.2 创建社团审批

##### 3.8.2.2.1 拉取审批信息

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/get-create-apply-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">GET</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><th colspan="3">None</th></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回数据数量</td></tr>
    <tr><th>data.create[]</td><th colspan=2>响应数据体</td></tr>
	<tr><td>caid</td><td colspan=2>创建审批ID</td></tr>
    <tr><td>name</td><td colspan=2>社团名字</td></tr>
    <tr><td>intro</td><td colspan=2>社团简介</td></tr>
    <tr><td>note</td><td colspan=2>申请备注</td></tr>
    <tr><td>uid</td><td colspan=2>创建社团的用户UID</td></tr>
    <tr><td>realname</td><td colspan=2>真实姓名</td></tr>
    <tr><td>studentID</td><td colspan=2>学号</td></tr>
	<tr><td>status</td><td colspan=2>状态码：0:未通过，1:已通过</td></tr>
</table>


##### 3.8.2.2.2 审批

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/create-ass-reply</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
    <tr><td>caid</td><td>Integer</td><td>创建社团审批信息唯一标识符ID</td></tr>
    <tr><td>status</td><td>Integer</td><td>状态：0：不通过，1：通过</td></tr>
    <tr><th>响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
	<tr><td>code</td><td colspan=2>失败返回-1，成功返回一个caid</td></tr>
    <tr><td>msg</td><td colspan=2>描述信息</td></tr>
</table>



### 3.8.3 用户信息

这个页面只做所有用户信息的展示。

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/info/get-all-users</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><th colspan="3">None</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>0：获取成功 非0：获取失败</td></tr>
    <tr><td>msg</td><td colspan=2>result非零时返回错误信息</td></tr>
    <tr><th>data.user[]</td><th colspan=2>result为0时返回如下用户信息</td></tr>
	<tr><td>uid</td><td colspan=2>用户唯一标识符</td></tr>
    <tr><td>userName</td><td colspan=2>用户昵称</td></tr>
    <tr><td>realName</td><td colspan=2>用户真实姓名</td></tr>
    <tr><td>studentID</td><td colspan=2>学号或教工号</td></tr>
    <tr><td>college</td><td colspan=2>用户所属学院</td></tr>
    <tr><td>intro</td><td colspan=2>用户简介</td></tr>
	<tr><td>permisson</td><td colspan=2>用户身份</td></tr>
	<tr><td>birth</td><td colspan=2>用户生日</td></tr>
</table>



### 3.8.4 审批批复

#### 3.8.4.1 拉取审批信息

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/get-ass-apply-list</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><th colspan="3">None</th></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时返回数据数量</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
	<tr><th>data.list[]</td><th colspan=2>请求成功时返回该字段，是个数组</td></tr>
	<tr><td>aaid</td><td colspan=2>审批ID</td></tr>
	<tr><td>aid</td><td colspan=2>社团ID</td></tr>
	<tr><td>title</td><td colspan=2>审批信息标题</td></tr>
	<tr><td>content</td><td colspan=2>审批信息内容</td></tr>
	<tr><td>assname</td><td colspan=2>社团名称</td></tr>
	<tr><td>date</td><td colspan=2>发布日期</td></tr>
</table>


#### 3.8.4.2 审批批复

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/send-aa-reply</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aaid</td><td>Integer</td><td>id值，是论坛提审唯一标识符</td></tr>
	<tr><td>status</td></td><td>Integer</td><td>0:不通过，1：通过</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是一个aaid</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
</table>


#### 3.8.4.3 拉取活动审批列表

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/action/get-act-apply</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>aid</td><td>Integer</td><td>id值，论坛唯一标识符，0代表获取所有活动</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是数据的数量</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
 	<tr><th>data.action[]</td><th colspan=2>活动列表</td></tr>
	<tr><td>actaid</td><td colspan=2>活动审批唯一ID</td></tr>
	<tr><td>aid</td><td colspan=2>举办活动的论坛id</td></tr>
	<tr><td>assname</td><td colspan=2>举办活动的论坛名字</td></tr>
    <tr><td>title</td><td colspan=2>活动名称</td></tr>
    <tr><td>content</td><td colspan=2>活动申请内容</td></tr>
    <tr><td>startTime</td><td colspan=2>开始时间</td></tr>
	<tr><td>endTime</td><td colspan=2>结束时间</td></tr>
</table>



#### 3.8.4.4 活动审批

<table>
    <tr><th colspan="3">请求</th></tr>
    <tr><th>HTTP协议</th><td colspan="2">1.1</td></tr>
    <tr><th>请求地址</th><td colspan="2">http://easy.30202.co:11119/api/apply/send-act-reply</td></tr>
    <tr><th>请求方法</th><td colspan="2">POST</td></tr>   
    <tr><th>封装格式</th><td colspan="2">application/x-www-form-urlencoded</td></tr>
    <tr><th colspan="3">请求参数</th></tr>    
    <tr><th>参数名</th><th>参数类型</td><th>备注</td></tr>
	<tr><td>actid</td><td>Integer</td><td>id值，是活动审批唯一标识符</td></tr>
	<tr><td>status</td></td><td>Integer</td><td>0:不通过，1：通过</td></tr>
    <tr><th >响应结果</th><th colspan=2>Json字符串</th></tr> 
    <tr><th>data</td><th colspan=2>响应数据体</td></tr>
    <tr><td>code</td><td colspan=2>-1：请求失败，请求成功时该参数是一个aaid</td></tr>
    <tr><td>msg</td><td colspan=2>请求失败时返回错误信息</td></tr>
</table>


### 3.8.5 公共交流区公告管理

#### 3.8.5.1 获取论坛公告

该接口参考 3.3.2节的接口，传递的参数Type值为1 （表示系统公告）

- [3.3.2 获取帖子列表数据接口](#332-获取帖子列表数据接口)

#### 3.8.5.2 发表论坛公告

该功能实际上是一个发布帖子的功能，因此能够复用发表帖子的接口。

使用 3.3.3 节的接口，将其postType值设置为1即可。但是一个论坛最多发表5个公告，如果公告超出则无法发表成功

- [3.3.3 发表帖子接口](#333-发表帖子接口)

#### 3.8.5.2 删除论坛公告

如果论坛公告满五个时，需要调用该接口进行删除，同理，删除论坛的接口也与前文删除帖子使用同一接口。

此时requestType值为0，即删除帖子

- [3.4.5 删除帖子以及回复](#345-删除帖子以及回复)

