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
