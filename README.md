# SPRING BOOT TRAINING, PART 2 ("PIZZA")

## About

This repository contains source code and exercises for my introductory Spring Boot training.

## Copyright

Copyright Thomas Auinger (thomas@auinger.de), Germany.

This source is intended to be used in conjunction with a training given
by me or one of my licensees. Therefore, you may use segments
of this code in your projects if you took part in such a training.

In any case it is not permitted to reuse all or parts of
this project for training purposes without permission.

## Dev Diary

### H2 database connection refused

- H2 server must be started with "ifNotExists" flag to allow remote creation of DB
- H2 launcher bean must run before DataSource bean -> solved using "dependsOn" bean attribute

### No products in DB

- Database tool shows results when debugging the code and refreshing datasource in Database tool
- but after debug is done, products are gone again -> script-runner executed after product-setup,
  dropping populated table. also using dependsOn to solve this

### Migration notes

copy pom.xml from math, change GAV

copy / rename Appl class

add lombok lib without version
add servlet lib with version as provided
add h2 lib without version
add spring-boot-starter-jdbc lib

remove init() in servlets
added @ServletComponentScan to App class

-> compiles
-> start

added @Component to ProductJdbcDao
added @Service to the three Services

-> start: java.lang.NoClassDefFoundError: org/springframework/web/context/WebApplicationContext

changed start from "spring-boot-starter" to "spring-boot-starter-web"

-> start: Error creating bean with name 'dataSourceScriptDatabaseInitializer' defined in class path resource [org/springframework/boot/autoconfigure/sql/init/DataSourceInitializationConfiguration.class]: Invocation of init method failed; nested exception is org.springframework.jdbc.datasource.init.UncategorizedScriptException: Failed to execute database script from resource [URL [file:/C:/Dev/Workspaces/Integrata/training.spring-boot.pizza/target/classes/data.sql]]; nested exception is java.lang.IllegalArgumentException: 'script' must not be null or empty

removed data.sql

-> start: runs! dump servlet found, but null error

remove DumpDataServlet @Setter annotations, make final, add constructor

-> start: dump servlet works, but no data

added @PostConstruct to ProductSetup.createProducts()

-> Table "PRODUCTS" not found

added @Component to H2Launcher; added @PostConstruct to run()

-> start; connect to H2 in Intellij -> file corrupt

stop, delete H2 database files; start

-> H2 now empty, why is schema.sql not executed? Or is it but against other datasource?

renamed config.properties to application.properties
changed prop name to `spring.datasource.url`
added other three typical spring.datasource props (driverClassName, username, password)

start -> Table "PRODUCTS" not found (still)

commented ProductSetup @Component to investigate reading H2 from intellij database tool

-> [42S02][42102] org.h2.jdbc.JdbcSQLSyntaxErrorException: Tabelle "INFORMATION_SCHEMA_CATALOG_NAME" nicht gefunden Table "INFORMATION_SCHEMA_CATALOG_NAME" not found; SQL statement: select CATALOG_NAME from INFORMATION_SCHEMA.INFORMATION_SCHEMA_CATALOG_NAME [42102-210].

changed H2 driver version in Intellij back to 1.4.200 (https://github.com/h2database/h2database/issues/3459)

-> database tool now connects and reads data

@Component, @PostConstruct to H2ScriptRunner
re-fetched data.sql

-> start, tables now exist!

uncommented @Component in ProductSetup

-> start, dump now contains products!

add @ImportResource to appl-class
removed all other stuff from context.xml but Address and CustomerCreator
changed CustomerService injection to constructor

-> start, dump now contains customers!

-> place-order servlet call: "this.orderService" is null

changed @setter to @autowired

-> start, can place order, dump now contains orders!

-> run all tests: Failed to load ApplicationContext

changed annotation to @SpringBootTest

-> run all tests: Failed to load ApplicationContext, H2 server port in use

stop boot application

-> all tests run!

### using H2 TCP database with spring-data-jpa

problem: Spring runs scripts before H2 Launcher could start the DB -> connection exception

force creation of DB before datasource: https://stackoverflow.com/questions/37068808/how-to-start-h2-tcp-server-on-spring-boot-application-startup
OR (as we do now) use regular in-mem H2
