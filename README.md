# TAPM
SDU scrum platform
# TAPM接口文档

| 版本号 |    日期    | 修改人 | 修改内容 |
| :----: | :--------: | :----: | :------: |
|  1.0   | 2021/12/17 | 田淑杰 |          |

## 项目基本说明

- 项目测试接口为http://localhost:8081/

- 前端向服务器发送一般数据的`Content-Type`为`application/json`，若包含文件(如图片或文档)，发送数据的`Content-Type`为`multipart/form-data`

- 除`login`、`register`等接口外，其余请求均需使用`token`，下方具体接口内省略不写，前端将`token`放于请求头"token"进行请求

- 若本次请求正常，则返回`code`为0，`message`为"success"；小于0代表错误，大于0代表非错误性提示，任何请求都会返回`code`和`message`，下方接口数据中的传出参数为`data`中的讯息，某些接口没有`data`数据

- 统一错误码

  | 错误码 |                  信息                   |
  | :----: | :-------------------------------------: |
  |   0    |                 success                 |
  |   -1   |                未知错误                 |
  |  401   | token无效或不存在，需重新登录（抛异常） |

```json
{
    "code": 0,
    "message": "success",
    "data": ""
}
```

### 具体接口

#### 一、登录注册

| 错误码 |           信息           |
| :----: | :----------------------: |
|   11   |      账号或密码错误      |
|   12   |      账号或密码为空      |
|   13   |        账号已存在        |
|   14   | 账号或密码超过最大长度20 |
|   15   |      注册信息不完整      |

##### 1.1登录

|     URL     | method |
| :---------: | :----: |
| /user/login |  POST  |

| 传入参数 |  类型  | 是否可空 |       说明       |
| :------: | :----: | :------: | :--------------: |
| username | String |    否    | 用户账号（唯一） |
| password | String |    否    |     用户密码     |
| identity |  int   |    否    |     用户身份     |

```json
{
    "code": 0,
    "message": "success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDIwMDAzMDAxMTYifQ.d431EWge7eCHApXBEJTZ_tSb5tx0aoOf0kBAjfq6vKg"
}
```

##### 1.2注册

|      URL       | method |
| :------------: | :----: |
| /user/register |  POST  |

| 传入参数 |  类型  | 是否可空 |          说明          |
| :------: | :----: | :------: | :--------------------: |
| username | String |    否    |        用户账号        |
| password | String |    否    |        用户密码        |
|  email   | String |    否    |        用户邮箱        |
| identity |  int   |    否    | 用户身份（产品1技术2） |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```

##### 1.3修改密码

|     URL     | method |
| :---------: | :----: |
| /user/reset |  POST  |

|   传入参数   |  类型  | 是否可空 |    说明    |
| :----------: | :----: | :------: | :--------: |
|   username   | String |    否    |  用户账号  |
| old_password | String |    否    | 用户原密码 |
| new_password | String |    否    | 用户新密码 |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```

##### 1.4个人信息

|    URL     | method |
| :--------: | :----: |
| /user/self |  POST  |

| 传出参数 |  类型  |   说明   |
| :------: | :----: | :------: |
| username | String | 用户账号 |
|  email   | String | 用户邮箱 |
| identity |  int   | 用户身份 |

```json
{
    "code": 0,
    "message": "success",
    "data": {
        "identity": 1,
        "email": "202000300116@mail.sdu.edu.cn",
        "username": "202000300116"
    }
}
```

##### 1.5展示技术成员列表

|       URL        | method |
| :--------------: | :----: |
| /user/technician |  POST  |

| 传出参数 |   类型    |   说明   |
| :------: | :-------: | :------: |
| nameList | List | username |

```json
{
    "code": 0,
    "message": "success",
    "data": [
        {
            "username": "test2_account"
        }
    ]
}
```
#### 二、需求管理
| 错误码 |        信息        |
| :----: | :----------------: |
|   21   | 创建需求信息不完整 |

##### 2.1需求新建

|      URL       | method |
| :------------: | :----: |
| /demand/create |  POST  |

| 传入参数 |  类型  | 是否可空 |       说明       |
| :------: | :----: | :------: | :--------------: |
|  title   | String |    否    |     需求标题     |
| project  | String |    否    | 需求所属项目名称 |
|   ddl    |  Date  |    否    |     截止日期     |
|   doer   | String |    否    |      负责人      |
| priority |  int   |    否    |      优先级      |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```

##### 2.2获取个人执行中（归档）需求

|          URL           | method |
| :--------------------: | :----: |
| /demand/doing（/done） |  POST  |

|  传出参数  | 类型 |                             说明                             |
| :--------: | :--: | :----------------------------------------------------------: |
| demandList | List | 包括demand_id，title，project，ctime，ddl，cer，doer，priority，status，address，docu所有 |

```json
{
    "code": 0,
    "message": "success",
    "data": [
        {
            "cer": "202000300116",
            "demand_id": 2,
            "doer": "test2_account",
            "project": "项目1",
            "ctime": "Fri Dec 17 03:29:40 CST 2021",
            "title": "需求2",
            "priority": 1,
            "ddl": "Wed Dec 15 22:00:00 CST 2021",
            "status": 2
        },
        {
            "cer": "202000300116",
            "demand_id": 4,
            "doer": "test2_account",
            "project": "项目1",
            "ctime": "Fri Dec 17 03:29:54 CST 2021",
            "title": "需求2",
            "priority": 1,
            "ddl": "Wed Dec 15 22:00:00 CST 2021",
            "status": 4
        },
        {
            "cer": "202000300116",
            "demand_id": 5,
            "doer": "test2_account",
            "project": "项目1",
            "ctime": "Fri Dec 17 03:34:08 CST 2021",
            "title": "需求2",
            "priority": 1,
            "ddl": "Wed Dec 15 22:00:00 CST 2021",
            "status": 4
        }
    ]
}
```

##### 2.3获取不同状态需求数量

|     URL     | method |
| :---------: | :----: |
| /demand/num |  POST  |

| 传出参数 |  类型  |     说明     |
| :------: | :----: | :----------: |
|  numStr  | String | 见代码内注释 |

```json
{
    "code": 0,
    "message": "success",
    "data": "i1=1, i2=1, i3=0, i4=2, i5=1, i6=0"
}
```

##### 2.4上传github地址

|      URL       | method |
| :------------: | :----: |
| /demand/github |  POST  |

| 传入参数 |  类型  | 是否可空 |      说明      |
| :------: | :----: | :------: | :------------: |
|  commit   | String |    否    | log内容 |
| address  | String |    否    | github提交地址 |
| demand_id  | int  |    否    |  需求id  |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```

##### 2.5需求文档上传（暂时传到tomcat本地服务器，重启则全部消失）

|      URL       | method |
| :------------: | :----: |
| /demand/upload |  POST  |

|  传入参数  | 类型 | 是否可空 |   说明   |
| :--------: | :--: | :------: | :------: |
| uploadFile | file |    否    | 需求文档 |
| demand_id  | int  |    否    |  需求id  |

| 传出参数 |  类型  |   说明    |
| :------: | :----: | :-------: |
| filePath | String | 服务器url |

```json
{
    "code": 0,
    "message": "success",
    "data": "http://localhost:8081/uploadFile/1/微信图片_地缚少年花子君.png"
}
```

##### 2.6需求文档下载

|       URL        | method |
| :--------------: | :----: |
| /demand/download |  POST  |

| 传入参数  | 类型 | 是否可空 |  说明  |
| :-------: | :--: | :------: | :----: |
| demand_id | int  |    否    | 需求id |

| 传出参数 |  类型  |   说明    |
| :------: | :----: | :-------: |
| filePath | String | 服务器url |

```json
{
    "code": 0,
    "message": "success",
    "data": "http://localhost:8081/uploadFile/1/微信图片_地缚少年花子君.png"
}
```

##### 2.7需求状态改变（创建log）

共1-2，1-3，2-4，2-5，2-6，4-5，4-6七种操作对应url

|       URL        | method |
| :--------------: | :----: |
| /demand/from1to2 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from1to3 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from2to4 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from2to5 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from2to6 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from4to5 |  POST  |

|       URL        | method |
| :--------------: | :----: |
| /demand/from4to6 |  POST  |

| 传入参数  |  类型  | 是否可空 |  说明   |
| :-------: | :----: | :------: | :-----: |
|  commit   | String |    否    | log内容 |
| demand_id |  int   |    否    | 需求id  |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```

##### 2.8发布邮件

|      URL      | method |
| :-----------: | :----: |
| /demand/email |  POST  |

| 传入参数 |  类型  | 是否可空 |    说明    |
| :------: | :----: | :------: | :--------: |
|    to    | String |    否    | 收件人邮箱 |
| subject  | String |    否    |  邮件主题  |
|   text   | String |    否    |  邮件内容  |

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```


#### 三、Log管理

##### 3.1展示全部log

|   URL    | method |
| :------: | :----: |
| /log/all |  POST  |

| 传出参数 | 类型 |                          说明                          |
| :------: | :--: | :----------------------------------------------------: |
| logList  | List | 包括log_id，demand_id，project，ctime，cer，commit所有 |

```json
{
    "code": 0,
    "message": "success",
    "data": [
        {
            "log_id": 1,
            "cer": "202000300116",
            "demand_id": 1,
            "commit": "commit1",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        },
        {
            "log_id": 2,
            "cer": "202000300116",
            "demand_id": 1,
            "commit": "commit2",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        },
        {
            "log_id": 3,
            "cer": "test2_account",
            "demand_id": 1,
            "commit": "commit3",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        },
        {
            "log_id": 4,
            "cer": "202000300116",
            "demand_id": 1,
            "commit": "commit4",
            "project": "项目2",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        }
    ]
}
```

##### 3.2展示个人log

|    URL    | method |
| :-------: | :----: |
| /log/self |  POST  |

| 传出参数 | 类型 |                          说明                          |
| :------: | :--: | :----------------------------------------------------: |
| logList  | List | 包括log_id，demand_id，project，ctime，cer，commit所有 |

```json
{
    "code": 0,
    "message": "success",
    "data": [
        {
            "log_id": 3,
            "cer": "test2_account",
            "demand_id": 1,
            "commit": "commit3",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        }
    ]
}
```

##### 3.3展示需求log

|     URL     | method |
| :---------: | :----: |
| /log/demand |  POST  |

| 传入参数  | 类型 |  说明  |
| :-------: | :--: | :----: |
| demand_id | int  | 需求id |

| 传出参数 | 类型 |                          说明                          |
| :------: | :--: | :----------------------------------------------------: |
| logList  | List | 包括log_id，demand_id，project，ctime，cer，commit所有 |

```json
{
    "code": 0,
    "message": "success",
    "data": [
        {
            "log_id": 1,
            "cer": "202000300116",
            "demand_id": 1,
            "commit": "commit1",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        },
        {
            "log_id": 2,
            "cer": "202000300116",
            "demand_id": 1,
            "commit": "commit2",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        },
        {
            "log_id": 3,
            "cer": "test2_account",
            "demand_id": 1,
            "commit": "commit3",
            "project": "项目1",
            "ctime": "Wed Dec 15 22:00:00 CST 2021"
        }
    ]
}
```

##### 3.4发布log（跟在2.7改变状态里）
