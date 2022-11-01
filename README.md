# training.spring-boot.pizza

## Dev Diary

### H2 database connection refused

- H2 server must be started with "ifNotExists" flag to allow remote creation of DB
- H2 launcher bean must run before DataSource bean -> solved using "dependsOn" bean attribute

### No products in DB

- Database tool in Intellij had old connection (to what??) -- works when debugging
the code and refreshing datasource in Database tool
- but after debug is done, products are gone again -> script-runner executed after productsetup,
dropping populated table. also using dependsOn to solve this