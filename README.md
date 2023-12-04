# TaskBackend-SpringBoot
Built the backend of Tasklist webapp using Springboot and MySql. APIs exposed by the application are mentioned below

### 1-Download the Project: 
Download Zip file from Github link and Extract it.

### 2-Install Dependencies: For a Spring Boot project, you'll need Java installed. 

### 3-Build the Project
mvn clean install

### 4-Run the Project:

Open the project in any JAVA IDE and try to run it from there. Else use CLI command as - 

java -jar your-project-name.jar (replace your-project-name with the application jar file)


### 5-Configure Database Connection:
Open the application.properties or application.yml file in the src/main/resources directory and configure your database connection properties -
#spring.datasource.url=jdbc:mysql://localhost:3306/your_database
#spring.datasource.username=your_username
#spring.datasource.password=your_password
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

## Create a table with the given schema - 
+----------------+--------------+------+-----+---------+
| Field          | Type         | Null | Key | Default | 
+----------------+--------------+------+-----+---------+
| id             | bigint       | NO   | PRI | NULL    |       
| name           | varchar(255) | YES  |     | NULL    |       
| status         | tinyint      | YES  |     | NULL    |       
| priority_level | tinyint      | YES  |     | NULL    |       
| description    | varchar(255) | YES  |     | NULL    |       
| due_date       | date         | YES  |     | NULL    |       
| external_id    | varchar(255) | YES  |     | NULL    |       
| active         | tinyint(1)   | YES  |     | NULL    |       
+----------------+--------------+------+-----+---------+

## RESTful APIs: 
 All the dynamic values are represented by enclosed parenthesis.

## GET /task?
	- /task?page=1
	- /task?page={pageNo}&sort_by={sortBy}
	- /task?page={pageNo}&sort_by={sortBy}&sorting_order={sortingOrder}
## POST /task
	if using POSTMAN give the request a body in the form of:
	``code`` {
	    "name": "new task",
	    "description": "this is your task 12",
	    "task_priority_level": "HIGH",
	    "task_status": "COMPLETED",
	    "due_date": "2025-11-20"
	}
## PUT /task/{taskId}
	This API also expects a body. give it an object and write fields which you want to change.
	``code`` {
        "name": "non-updated complete poc on email sending",
        "task_priority_level": "LOW",
        "task_status": "IN_PROGRESS"
      }
## DELETE /task/{taskId}
