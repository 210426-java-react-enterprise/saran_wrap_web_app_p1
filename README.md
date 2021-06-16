# Team F - Project 1 - Saran Wrap Web App

## Instructions for DevOps Team
1. Set up ORM first
 - Refer to ORM Github repo for instructions.
2. Launch Tomcat Server
3. Run `mvn tomcat7:deploy` to deploy the app
4. Use Postman to send request to our endpoints
 - /auth
    - post:
      - Send login credentials with a raw json
        - username:
        - password:
    - put:
      - While user's session is still valid a user can send this request to (soft) delete their account
    - delete:
     - Logs user out of the current session