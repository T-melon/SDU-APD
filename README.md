# TAPM
SDU scrum platform
# TAPM接口文档

| 版本号 |    日期    | 修改人 | 修改内容 |
| :----: | :--------: | :----: | :------: |
|  1.0   | 2021/12/17 | 田淑杰 |          |

## 项目基本说明

- 项目测试接口为http://localhost:8081/

- 前端向服务器发送一般数据的`Content-Type`为`application/json`，若包含文件(如图片或文档)，发送数据的`Content-Type`为`multipart/form-data`

- 除`login`、`register`接口外，其余请求均需使用`token`，下方具体接口内省略不写，前端将`token`放于请求头"token"进行请求

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
| nameList | ArrayList | username |

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
