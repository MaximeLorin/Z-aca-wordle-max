## Jouer une partie

### 1. Créer la partie
```mermaid
sequenceDiagram
    participant M as Main
    participant GM as GameManager
    participant DS as DictionaryService
    participant GR as GameRepository
    M->>+GM: createGame(wordLength, maxAttempts)
    GM->>DS: getRandomWord(lang)
    DS->>GM: 
    GM->>GM: new Game(word, maxAttemps)
    GM->>GR: save(newGame)
    GM->>-M: newGame
```

### 2. Faire un essai

```mermaid
sequenceDiagram
    participant M as Main
    participant GM as GameManager
    participant DS as DictionaryService
    participant G as Game
    participant GR as GameRepository
    M->>+GM: attemptWord(gameId, word)
    GM->>GR: findById(gameId)
    GR->>GM: 
    GM->>G: game.getWordLength()
    G->>GM: 
    GM->>DS: wordExists(lang, word) ?
    GM->>GM: word has right length ?
    GM->>G: game.attempt(word)
    GM->>GR: save(game)
    GM->>-M: game
```

### 3. Déroulement d'une partie

```mermaid
sequenceDiagram
    participant M as Main
    participant GM as GameManager
    loop
        M->>GM: createGame(lang)
        loop until game win or lose
            M->>M: user type word
            M->>GM: attemptWord(gameId, word)
            M->>M: display game state
        end
    end
```

### Modélisation

```mermaid
classDiagram
    Game *--RoundResult

    class Game {
        -String tid
        -String word
        -String language
        
        +getLanguage() String
        +getGameState() GameState
        +getRoundResults() List~RoundResult~
        +attempt(word)
        +getWord()
    }
    
    class RoundResult {
        -String[] letters
        -ValidationLetter[] validationLetters
    }
    
    class GameState{
        <<enumeration>>
        WIN
        LOSS
        IN_PROGRESS
    }
    
    class ValidationLetter{
        <<enumeration>>
        GOOD_POSITION
        WRONG_POSITION
        NOT_IN_WORD
    }
```