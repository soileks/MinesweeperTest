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
import ru.petrov.test.service.InterfaceGameService;

@RestController
@CrossOrigin(origins = "https://minesweeper-test.studiotg.ru/")
public class MinesweeperController {
    private final InterfaceGameService gameService;

    @Autowired
    public MinesweeperController(InterfaceGameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newGame(@RequestBody GameSettings settings){
        Game game = gameService.createNewGame(settings);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/turn")
    public ResponseEntity<?> makeTurn(@RequestBody TurnRequest turn){
        Game game = gameService.makeTurn(turn);
        return ResponseEntity.ok(game);
    }


}
