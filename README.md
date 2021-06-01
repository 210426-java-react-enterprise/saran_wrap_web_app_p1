# Team F - Project 1 - Saran Wrap Web App

## Tech Stack
- [x] Java 8
- [x] JUnit
- [x] Mockito
- [x] Apache Maven
- [x] Jackson library (for JSON marshalling/unmarshalling)
- [x] Java EE Servlet API (v4.0+)
- [x] PostGreSQL deployed on AWS RDS
- [x] AWS CodeBuild
- [x] AWS CodePipeline
- [x] Git SCM

## Functional Requirements
- [x] CRUD operations are supported for one or more domain objects via the web application's exposed endpoints
- [x] JDBC logic is abstracted away by [Saran Wrap ORM](https://github.com/210426-java-react-enterprise/saran_wrap_orm_p1)
- [x] Basic CRUD support using Saran Wrap ORM
- [x] Leverages Saran Wrap ORM for programmatic configuration of entities

## Non-Functional Requirements
- [ ] 80% line coverage of all service layer classes
- [x] Generated Jacoco reports that display coverage metrics
- [x] Usage of the java.util.Stream API within your project
- [x] Custom ORM source code should be included within the web application as a Maven dependency
- [x] Continuous integration pipelines that builds some main branch each project (the ORM and the web app, separately)

## Bonus Features 
- [ ] Custom ORM supports basic transaction management (begin, commit, savepoint, rollback)
- [ ] Custom ORM supports connection pooling
- [ ] Session-based caching to minimize calls to the database for already retrieved data
- [ ] Deployment of web application to AWS EC2 (use of AWS Elastic Beanstalk is permitted)

## Presentation
- Finalized version of custom ORM and web application must be pushed to personal repository within this organization by the presentation date (June 1st, 2021)
- 10-15 minute live demonstration of the web application (that leverages your custom ORM); demonstration will be performed using PostMan to query your API's endpoints
