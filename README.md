# webclient-rickandmorty

Projeto de estudo do **Spring `WebClient`** (cliente HTTP reativo do WebFlux), consumindo a
[Rick and Morty API](https://rickandmortyapi.com/). A aplicação expõe seus próprios
endpoints REST que buscam personagens e episódios na API externa e devolvem a resposta de
forma reativa (`Mono` / `Flux`). Organizada em **arquitetura hexagonal** (ports & adapters).

**Stack:** Kotlin 1.8 · Spring Boot 3.1 (Web + WebFlux) · Project Reactor · Gradle (Kotlin DSL)
· testes com MockK + kotlin-faker + JUnit 5

## Endpoints

Base: `/webclient`

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/webclient/character/{id}` | Busca um personagem por id |
| `GET` | `/webclient/episodes/{id}` | Busca um episódio por id |
| `GET` | `/webclient/episodes` | Lista os episódios |

Um id inexistente na API externa (`4xx`) é convertido em resposta `404` no formato
*Problem Detail* (RFC 7807).

```bash
curl http://localhost:8080/webclient/character/1
```

```json
{
  "id": "1",
  "name": "Rick Sanchez",
  "status": "Alive",
  "species": "Human",
  "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
  "episode": ["https://rickandmortyapi.com/api/episode/1", "..."]
}
```

## Como funciona

```
GET /webclient/character/{id}
        │
        ▼
CharacterController ──► LoadCharactersUseCase ──► CharacterService ──► WebClient
   (adapter/web)          (core/usecase)          (adapter/integration)     │
                                                                           ▼
                                                          https://rickandmortyapi.com/api
```

O `WebClient` é configurado em `adapter/conf/WebConfig` com a `baseUrl` da Rick and Morty
API. Os *services* de integração fazem as chamadas, tratam status `4xx` lançando
exceções de domínio (`NoSuchCharacterException`, `NoSuchEpisodeException`) e desserializam
o corpo em `Character` / `Episode`.

### Estrutura

```
src/main/kotlin/com/cesarlucasjunior/webclientrickandmorty
├── core/
│   ├── domain/        # Character, Episode, ListOfEpisodes
│   ├── ports/in/      # LoadCharactersInputPort, LoadEpisodesInputPort
│   ├── ports/out/     # LoadCharactersOutputPort, LoadEpisodesOutputPort
│   └── usecase/       # LoadCharactersUseCase, LoadEpisodesUseCase
└── adapter/
    ├── web/           # controllers REST
    ├── integration/   # chamadas à API externa via WebClient
    ├── conf/          # WebConfig (bean do WebClient)
    └── exception/     # exceções de domínio + GlobalExceptionHandler
```

## Como rodar

Pré-requisito: JDK 17+.

```bash
./gradlew bootRun     # http://localhost:8080
```

Não precisa de banco nem de variáveis de ambiente — a única dependência externa é a
Rick and Morty API pública.

## Testes

```bash
./gradlew test
```

## Notas

- A rota base dos controllers continua `/webclient` (`@RequestMapping("/webclient")`) — é o
  prefixo da API, não o nome do projeto.
- `netty-resolver-dns-native-macos` está no build para resolução de DNS em Apple Silicon.
