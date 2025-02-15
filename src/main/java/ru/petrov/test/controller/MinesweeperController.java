package ru.petrov.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.petrov.test.model.ErrorResponses;
import ru.petrov.test.model.Game;
import ru.petrov.test.model.GameSettings;
import ru.petrov.test.model.TurnRequest;
import ru.petrov.test.service.GameService;

@RestController
@CrossOrigin(origins = "https://minesweeper-test.studiotg.ru/")
public class MinesweeperController {
    private final GameService gameService;
    private Game game;

    @Autowired
    public MinesweeperController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newGame(@RequestBody GameSettings settings){
        try {
             game = gameService.createNewGame(settings.getWidth(), settings.getHeight(), settings.getMines_count());

            return ResponseEntity.ok(game);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ErrorResponses(400, e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
        }

    }

    @PostMapping("/turn")
    public ResponseEntity<?> makeTurn(@RequestBody TurnRequest turn){
        try {
            game = gameService.makeTurn(turn.getGame_id(), turn.getCol(), turn.getRow());
            return ResponseEntity.ok(game);

        }
        catch (IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.badRequest().body( new ErrorResponses(400, e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
        }
    }


}
