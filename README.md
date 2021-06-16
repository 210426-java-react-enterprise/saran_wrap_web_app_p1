# Team F - Project 1 - Saran Wrap Web App

## Instructions for DevOps Team
1. Set up ORM first
   - Refer to ORM Github repo for instructions.
2. Launch Tomcat Server
3. Create an applications.properties files in a resources folder
   - Resources folder should be in the src/main directory
   - Requires host-url, username, and password.
4. Run `mvn tomcat7:deploy` to deploy the app
5. Use Postman to send request to our endpoints
   - /auth
     - post:
        - Send login credentials with a raw json
          - username:
          - password:
     - put:
        - While user's session is still valid a user can send this request to (soft) delete their account
        - Changes user_status column in the database to false.  
     - delete:
       - Logs user out of the current session
    
   - /users
     - get:
       - Returns all the users in the database
     - post: 
       - Creates a new user in the database with json with a default user_status of true
        - username:
        - password:
        - firstname:
        - lastname:
        - email:
        - age:  
