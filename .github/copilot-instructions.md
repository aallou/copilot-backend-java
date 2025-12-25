# Instructions — Contexte rapide

## Contexte du projet

- Stack: Java 21, Spring Boot 3.4.5, Maven
- Point d'entrée: `com.example.demo.DemoApplication`
- Structure principale:
```
src/
├── main/
│   ├── java/
│   │   ├── controller/    # REST controllers
│   │   ├── service/       # Business logic
│   │   ├── dto/           # Data transfer objects
│   │   ├── repository/    # JPA / Mongo repositories
│   │   └── config/        # Spring configuration classes
│   └── resources/         # application.properties, static, templates
└── test/
    └── java/              # Tests (unit/integration)
```

## Conventions et bonnes pratiques spécifiques

- Packages recommandés: `controller`, `service`, `repository`, `config`, `dto`, `model`, `exception`
- Architecture: Controllers → Services → Repositories
- DTOs: utiliser pour sorties/inputs REST; ne pas exposer les entités JPA directement
- Injection: préférer injection par constructeur
- Exceptions: centraliser les mappings HTTP avec `@ControllerAdvice`
- Logging: utiliser `org.slf4j.Logger` via `LoggerFactory`


### Bonnes pratiques Java 21

- Profiter des nouveautés: `record` pour DTO immuables simples, `sealed` pour hiérarchies contrôlées, et les `pattern matching` où cela clarifie le code.
- Préférer les `var` pour les variables locales quand cela améliore la lisibilité, mais l'éviter pour les signatures publiques.
- Utiliser les API améliorées des collections et `Stream` pour un code plus déclaratif; évitez les chaînes de transformations complexes non testées.
- Utiliser `try-with-resources` pour gérer les ressources et fermer automatiquement.
- Préférer l'immuabilité pour DTOs et objets partagés.
- Éviter les API expérimentales/preview en production sans validation; documenter l'usage si nécessaire.
- Profiter des améliorations de performance JDK 21 (GC, compilateur) mais mesurer avant d'optimiser.

#### Exemples courts Java 21

- `record` DTO (immuable simple):
```java
public record HelloDto(String message) {}
```

- `try-with-resources` (fermeture automatique):
```java
try (var in = new FileInputStream("config.properties")) {
    // lire la ressource
}
```

- `pattern matching` basique pour `instanceof`:
```java
Object obj = ...;
if (obj instanceof String s) {
    // utiliser s directement
}
```

#### Bonnes pratiques Spring

1) Injection par constructeur et beans stateless
```java
@Service
public class UserService {
        private final UserRepository repo;
        public UserService(UserRepository repo) { this.repo = repo; }
}
```

2) Externaliser la configuration avec `@ConfigurationProperties`
```java
@ConfigurationProperties(prefix = "app")
public class AppProperties { private String name; /* getters/setters */ }
```

3) Validation automatique des DTOs (Bean Validation)
```java
public record CreateUserDto(
        @NotBlank String username,
        @Email String email
) {}

@PostMapping("/users")
public ResponseEntity<Void> create(@Valid @RequestBody CreateUserDto dto) { ... }
```

4) Séparer la logique: `Controller` (API) → `Service` (métier) → `Repository` (persistance)
```java
@Repository interface UserRepository extends JpaRepository<User, Long> {}
@Service class UserService { /* logique métier */ }
@RestController class UserController { /* endpoints */ }
```

5) Tests d'API rapides avec `@WebMvcTest` + `MockMvc`
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired MockMvc mvc;
    @MockBean UserService svc;
}
```

## Non-negotiable constraints & Code rules
- Performance-first
- Security by default
- Production-ready code only
- Clean architecture
- No business logic in controllers
- Null-safe code
- Commits: "feat: thing" or "fix: thing"

## Commandes utiles
```bash
# Lancer les tests
mvn -q -DskipTests=false test

# Compiler et packager
mvn -q -DskipTests package

# Lancer l'application (dev)
mvn spring-boot:run
```

## Instructions
Utilise les instructions de test unitaire dans `.github/instructions/unit-test.instructions.md` pour générer des tests unitaires conformes aux bonnes pratiques Java 21 et Spring Boot.