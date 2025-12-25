---
applyTo: "src/test/java/**/*.java"
---

You are generating UNIT TESTS only.

## Mandatory stack
- JUnit
- Mockito
- AssertJ

## Strict rules
- Do NOT load Spring context
- Do NOT use @SpringBootTest
- Mock ALL dependencies
- One class under test only
- Constructor injection only
- No static mocking unless unavoidable

## Test structure
- Given / When / Then
- One behavior per test
- Explicit test names

## Coverage requirements
- Happy path
- Edge cases
- Error handling
- Validation failures

## Forbidden
- Thread.sleep
- Random values
- External systems
- Real DB / Kafka / HTTP calls

## Quality bar
- Deterministic
- Fast (<100ms)
- Readable
- Maintainable

## Output expectations
- Complete test class
- Correct package
- Imports included
- No TODO / placeholder
