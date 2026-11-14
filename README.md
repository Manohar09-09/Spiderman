# Spidy – Programming Learning Game
Based on your uploaded assignment and database specification. Uses HTML, CSS, JSP scriptlets, Jakarta Servlets, JDBC and MySQL. No JSTL or JSP EL.
## Requirements
JDK 17, Tomcat 10.1, MySQL 8+, Maven, Eclipse (optional).
## Setup
1. Run `database/01_create_database.sql` in MySQL.
2. Run `database/02_seed_admin.sql`. Demo admin: `admin` / `admin123`.
3. Edit DB username/password in `DBConnection.java`.
4. Import this folder as Existing Maven Project in Eclipse; Maven > Update Project.
5. Run `mvn clean package`; deploy `target/Spidy.war` to Tomcat 10.1.
6. Open `http://localhost:8080/Spidy/`.
Level rule implemented: every 100 XP advances one level. Student passwords use SHA-256 for this demo; use BCrypt/Argon2 and HTTPS for production.
