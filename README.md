I recommend against git pull and instead delete the old folder on your local machine and git clone this repo

since we're using file database it could cause conflict with git that cannot be resolved.

### Run Backend

Open backend folder and start the application

Go to localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./BlogDatabase

Username: sa

Password:

and click connect to view the database

### Run Frontend

Open frontend folder in Visual Studio Code

Run "npm install" in the terminal

Run "ng serve --open" start the front end application
