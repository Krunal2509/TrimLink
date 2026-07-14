<div>
  <p style="text-align: center; font-size: xx-large; font-weight: bold">TrimLink</p>
</div>

TrimLink is a simple web tool that helps you shorten long, messy website links into neat, tiny links. It lets users register, log in, and manage their own list of shortened links from a clean dashboard. When someone clicks or visits a shortened link, the app redirects them straight to the original website.

---

## 🚀 Tech Stack & Technologies

- **Java Development Kit (JDK)**: Java 25
- **Build Tool**: Apache Maven
- **Backend Framework**: Jakarta Servlet API (Version 6.0.0)
- **Frontend**: JavaServer Pages (JSP) (Version 3.1.1)
- **Database**: MySQL Server
- **Server Requirement**: Apache Tomcat 10 or 11

---

## 🛠️ Database Setup

Run the following SQL commands to set up the database and tables:

```sql
create database trimlink;

use trimlink;

create table users(  user_id int primary key  auto_increment,
					user_name varchar(10) ,
					password varchar(5) );
                  
create table urls(  url_id int primary key auto_increment,
                                user_id int,
                                long_url text,
                                short_url text,
                                foreign key (user_id) references users(user_id)
                                );                 
```

---

## ⚙️ Configuration

Before starting, update your database connection details in `src/main/resources/db.properties`:

```properties
DB_URL=jdbc:mysql://localhost:3306/trimlink
DB_USER=your_mysql_username
DB_PASSWORD=your_mysql_password
DB_DRIVER=com.mysql.cj.jdbc.Driver
```

---

## 📂 Project Structure

```text
TrimLink/
├── src/
│   └── main/
│       ├── java/com/trimlink/
│       │   ├── DAO/             # Data Access Objects (JDBC/SQL Logic)
│       │   │   ├── UrlDAO.java
│       │   │   └── UserDAO.java
│       │   ├── controllers/     # Servlets handling requests & routes
│       │   │   ├── AuthFilter.java
│       │   │   ├── dashboardController.java
│       │   │   └── ... (other controllers)
│       │   └── models/          # Data Models (User, Url)
│       │       ├── Url.java
│       │       └── User.java
│       ├── resources/
│       │   └── db.properties    # Database configuration properties
│       └── webapp/              # HTML/JSP files & Web Configuration
│           ├── WEB-INF/
│           │   └── web.xml      # Deployment descriptor
│           ├── login.jsp        # Login view
│           ├── register.jsp     # Register view
│           └── dashboard.jsp    # Dashboard view
├── pom.xml                      # Maven dependencies and build plugin settings
└── README.md
```
