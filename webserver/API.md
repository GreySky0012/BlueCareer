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


数据格式： `application/json`

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

数据格式： `application/json`

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

method : POST

url: /BlueCareer/api/v1/user/image_upload

数据格式： `form-data`

返回示例：

	{
	    "code": 0,
	    "message": "OK",
	    "data": "/BlueCareer/image/1001.jpg"
	}


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

数据格式： `application/json`

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

## 文章部分

### 获取所有文章

description：获取所有文章，后续会进行限流 - - 

method： `GET`

url: `/BlueCareer/api/v1/article/all`

返回示例 ：

	{
    	"code": 0,
    	"message": "OK",
    	"data": [
        	{
        	    "id": 1001,
        	    "title": "title",
        	    "content": "content",
        	    "viewCount": 300,
        	    "jobName": "Software Engineer"
        	},
        	{
        	    "id": 1002,
        	    "title": "title2",
        	    "content": "content2",
        	    "viewCount": 302,
        	    "jobName": "Software Engineer"
        	}
    	]
	}


### 获取所选职业信息的文章

description：获取所选职业信息的文章，后续会进行限流 - - 

method： `GET`

url: `/BlueCareer/api/v1/article/list`

- query：jobs

注：query可传递数组，例：

	/BlueCareer/api/v1/article/list?jobs=teacher&jobs=police

返回示例 ：

	{
    	"code": 0,
    	"message": "OK",
    	"data": [
        	{
        	    "id": 1001,
        	    "title": "title",
        	    "content": "content",
        	    "viewCount": 300,
        	    "jobName": "Software Engineer"
        	},
        	{
        	    "id": 1002,
        	    "title": "title2",
        	    "content": "content2",
        	    "viewCount": 302,
        	    "jobName": "Software Engineer"
        	}
    	]
	}

### 获取所选职业信息之外的文章

description：获取所选职业信息之外的文章，后续会进行限流 - - 

method： `GET`

url: `/BlueCareer/api/v1/article/exclude`

- query：jobs

注：query可传递数组，例：

	/BlueCareer/api/v1/article/exclude?jobs=teacher&jobs=police

返回示例 ：

	{
    	"code": 0,
    	"message": "OK",
    	"data": [
        	{
        	    "id": 1001,
        	    "title": "title",
        	    "content": "content",
        	    "viewCount": 300,
        	    "jobName": "Software Engineer"
        	},
        	{
        	    "id": 1002,
        	    "title": "title2",
        	    "content": "content2",
        	    "viewCount": 302,
        	    "jobName": "Software Engineer"
        	}
    	]
	}