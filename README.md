# Blog Writer App
### Contributors: Tam Doan, Demetrius Elder, Hoang Le, Nick Ogbuehi

A blog application designed to allow users to browse, create and post, and search blog entries.

#### Features:
- [x] Browse blog posts
- [x] Create new posts
- [x] Search through existing posts
- [x] Login and logout
- [ ] Register new users

#### To Do:
- [x] Bcrypt & User database 🔐 🕵️‍♂️
- [ ] Cosmos DB
- [ ] Pagination
- [ ] Edit && || delete function for certain users
- [ ] Finish bootstrapping app 👢
- [ ] Autofill author field when creating new post 🖊️
- [ ] ✨ Make it pretty ✨

## Technologies and Languages
<a href="https://angular.io/"><img src = "https://avatars.githubusercontent.com/u/139426?s=200&v=4" width="50px" /></a> <a href="https://spring.io/"><img src = "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/spring-boot/spring-boot.png" width="50px" /></a> <a href="https://getbootstrap.com/"><img src = "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/bootstrap/bootstrap.png" width="50px" /></a> <a href="https://www.typescriptlang.org/"><img src = "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/typescript/typescript.png" width="50px" /></a> <a href="https://www.java.com/en/"><img src = "https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png" width="50px" /></a> <a href="https://www.eclipse.org/ide/"><img src = "https://user-images.githubusercontent.com/11943860/46922575-7017cf80-cfe1-11e8-845a-0cd198fb546c.png" width="50px" /></a> <a href="https://code.visualstudio.com/"><img src = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Visual_Studio_Code_1.35_icon.svg/2048px-Visual_Studio_Code_1.35_icon.svg.png" width="50px" /></a>

## How To: Install and Run App

```git clone this repo```

<!-- since we're using file database it could cause conflict that cannot be resolved in git. -->

### Run Backend

Open backend folder and start the application

#### To open database:
1. Go to localhost:8080/h2-console
2. JDBC URL: jdbc:h2:file:./BlogDatabase
3. Username: sa
4. Password:
5. and click connect to view the database!

### Run Frontend

1. Open frontend folder in Visual Studio Code (or other alternative)
2. Run "npm install" in the terminal
3. Run "ng serve --open" to start the front end application
