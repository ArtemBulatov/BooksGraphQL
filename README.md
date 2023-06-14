## Хранилище книг и их авторов с помощью GraphQL

------------------------------------
### Необходимый софт для сборки и запуска
1. Maven 3.8.4
2. JDK 17
3. PostgreSQL 15.1
4. Docker 4.17.0
5. Сервис https://github.com/ArtemBulatov/BooksStore

### Сборка
Чтобы собрать проект, нужно выполнить в корне проекта команду "mvn install".
После сборки в директории target появляется исполняемый файл BooksApp.jar
В этом файле находится полностью упакованный проект. Вы можете поместить данный файл туда, куда вам нужно, и запускать.

### Запуск
Запуск исполняемого файла производится командой "java -jar BooksApp.jar".
Или можно использовать docker-compose.yml для запуска с помощью Docker.

### Команды
Отправлять POST запрос на /graphql.

mutations:
- saveBook(title, authors) - сохранение книги по названию с возможностью указать авторов 
- saveAuthor(name, books) - сохранение автора по имени с возможностью указать его книги

queries:
- getAllBooks() - получение всего списка книг
- getBooksByAuthor(author) - получение списка книг по автору
- getAuthor(name) - вывод данных автора (в т.ч. со списком книг автора)
