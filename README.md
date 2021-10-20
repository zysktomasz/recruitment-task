### Rozwiązanie

RESTowa aplikacja w Spring Boot. Dostępny jest jeden endpoint `GET /users/{login}` zwracający dane użytkownika z Githuba.

Do komunikacji z Github API użyta została biblioteka `Open Feign`. Mapowanie odpowiedz do docelowej formy z użyciem biblioteki `mapstruct`.

Baza danych użyta do przechowywania informacji o ilości requestów to `h2` (InMemory). Tabelka tworzona jest przez hibernate podczas startowania aplikacji.
Aktualizacja wartości w bazie wywoływana jest przez Aspect dodany z użyciem `Spring AOP`.

Dostępny jest również swagger UI, pod adresem: 'http://localhost:8080/swagger-ui/'

Link do UI bazy danych: http://localhost:8080/h2-console/ 

Nazwa bazy: jdbc:h2:mem:inMemoryDB

Login: sa

Hasło:
### Jak włączyć i przetestować
1. Otwórz projekt w Intelllij i uruchom entrypoint w `RecruitmentTaskApplication.java` lub poprzez CLI: `mvn spring-boot:run`
2. Włącz Swagger UI lub CLI: `curl -X GET "http://localhost:8080/users/abc" -H "accept: */*"`