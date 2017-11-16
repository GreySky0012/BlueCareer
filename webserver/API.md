# BlueCareer后端接口文档

## 用户部分

### 默认返回信息

description：用户部分后端接口的默认返回信息结构

* code：返回码，0为正常
* message：错误信息
* data：返回的内容

### 查看用户列表（仅测试使用）

description：获取已经注册的所有用户

method：`Get`

url：`/BlueCareer/api/v1/user/list`

返回示例：

    {  
    	"code":0,  
    	"message":"OK",  
    	"data":[  
    		{  
    			"id":1001,  
    			"userName":"testUser",  
    			"realName":"Hu Hao Ran",  
    			"password":"123456",  
    			"email":"mail@example.com",  
    			"accessKey":"abcdefg",  
    			"imagePath":"/image/mail@example.com.jpg",  
    			"qq":"123456789"  
    		}  
    	]	  
    }

### 邮箱验证

description：查询邮箱是否已经存在

method：`Get`

url：`/BlueCareer/api/v1/user/email_exist?email=`

query：

* email：要查询的邮箱

返回示例：

	{  
    	"code":0,  
		"message":"OK",  
    	"data":false  
	}

### 注册

description：存入昵称、邮箱、密码、QQ号、真实姓名

method：`POST`

url：`/BlueCareer/api/v1/user/add`

body：

* userName：昵称(必需)
* email：邮箱(必需)
* password：密码(必需)
* qq：QQ号
* realName：真实姓名
* careerMessage： 职业信息

返回示例：

	{  
	    "code": 0,  
	    "message": "OK"  
	}

### 登录

description：登录，验证邮箱和密码是否正确，返回UserEntity用户完整信息

method：`POST`

url：`/BlueCareer/api/v1/user/login`

body：

* email：邮箱
* password：密码

返回示例：  

	{  
	    "code": 0,  
	    "message": "OK",  
	    "data": {  
	        "id": 1001,  
	        "userName": "testUser",  
	        "realName": "Hu Hao Ran",  
	        "accessKey": "abcdefg",  
	        "imagePath": "/image/mail@example.com.jpg",  
	        "careerMessage": "Software Engineer",  
	        "qq": "123456789"  
	    }  
	}

**注意：登陆后与服务器交互仅用id与accessKey**

### 上传头像

description：更新指定邮箱账户的头像

### 获取头像

description：获取用户的头像地址，需要验证key

method: `GET`

url: `/BlueCareer/api/v1/user/image_path`

返回示例：

	{
	    "code": 0,
	    "message": "OK",
	    "data": "/image/mail@example.com.jpg"
	}

### 更新用户信息

description：更用户信息，需要验证key

method： `PUT`

url: `/BlueCareer/api/v1/user/modify`

body:

    {
        "userName": "change",
        "realName": "change",
        "careerMessage": "change",
        "qq": "change"
    }
    
**注意：没有发生更改的项同样需要作为参数**

返回示例：

	{  
	    "code": 0,  
	    "message": "OK"  
	}

### 获取职业

description：获取用户已选职业列表，需要验证key

method： `GET`

url: `/BlueCareer/api/v1/user/career_message`

返回示例：

	{  
	    "code": 0,  
	    "message": "OK",  
	    "data": "Software Engineer"  
	}