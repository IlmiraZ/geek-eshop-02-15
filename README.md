# Это pet-проект eshop.
## Курс gb.ru "Развивающийся интернет-магазин Spring Boot"

### Краткий обзор модулей
- **shop-database** - модуль сохранения магазина-базы данных
    - Spring Data JPA и  Hibernate
    - MySQL
    - Liquibase - управление версиями  БД

- **shop-admin-app** 
    - Template engine - Thymeleaf

- **shop-backend-api-app**
  - Spring Session Data Redis – репозиторий сессий
  - RabbitMQ – брокер сообщений
  - springdoc-openapi - генерация документации API с помощью проектов spring boot, Swagger-ui
  - spring-boot-starter-websocket  - для отправки сообщений между браузером и сервером 
  
- **shop-frontend-app** 
  - Angular application
  - Bootstrap
  
- **picture-service** - просто сервис для работы с изображениями
- **shop-picture-service-api** - spring boot application, API для получения изображений

- **spring-eureka**
  - Eureka - регистрация и обнаружение сервисов

- **Spring Cloud Config** - для внешней конфигурации

- **Spring Cloud Gateway**- обратный прокси-сервер и балансировка нагрузки

- **shop-delivery-service** - Пример простого приложения для изменения статуса заказа с помощью RabbitMQ


- **spring-integration**-demo
       Пример простого приложения для демонстрации интеграции.
       Здесь мы используем интеграцию через файлы, просто добавляем новые бренды.

- **shop-ui-tests**- Модуль для тестирования пользовательского интерфейса

 

### Общие инструменты
- [Spring Boot](https://spring.io/projects/spring-boot) -  упрощает создание автономных производственных приложений на базе Spring, которые вы можете "просто запустить".
- [Spring Security](https://spring.io/projects/spring-security) - это мощная и легко настраиваемая система аутентификации и контроля доступа

- [docker](https://www.docker.com/) - создавайте, развертывайте, запускайте, обновляйте контейнеры и управляйте ими
- [jib-maven-plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin) - инструмент контейнеризации для проектов maven
- [nginx](https://nginx.org/) - использование в качестве простого обратного прокси-сервера
- [swagger](https://swagger.io/) - для визуализации ресурсов API и взаимодействия с ними
- [MySQL](https://www.mysql.com/) - реляционная база данных 
- [liquibase](https://docs.liquibase.com/) - контроль версий
- [Angular](https://angular.io/) - это платформа для создания мобильных и настольных веб-приложений
- [ng-bootstrap](https://ng-bootstrap.github.io) - Виджеты Angular, созданные с нуля с использованием Bootstrap 5 CSS с API, разработанными для экосистемы Angular
- [ngx-pagination](https://github.com/michaelbromley/ngx-pagination) - разбивка на страницы для Angular
- [RxJS](https://rxjs.dev/) - реактивная библиотека расширений для Javascript

