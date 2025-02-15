package ru.petrov.test.service;

import ru.petrov.test.model.Game;

import org.springframework.stereotype.Service;
import ru.petrov.test.model.Game;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private static final int MAX_WIDTH = 30;
    private static final int MAX_HEIGHT = 30;
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Game createNewGame(int width, int height, int minesCount) {
        if (width < 1 || width > MAX_WIDTH) {
            throw new IllegalArgumentException("Ширина поля должна быть от 2 до " + MAX_WIDTH);
        }
        if (height < 1 || height > MAX_HEIGHT) {
            throw new IllegalArgumentException("Высота поля должна быть от 2 до " + MAX_HEIGHT);
        }
        if (minesCount < 1 || minesCount >= width * height) {
            throw new IllegalArgumentException("Количество мин должно быть от 1 до " + (width * height - 1));
        }

        String gameId = UUID.randomUUID().toString();
        Game game = new Game(gameId,height, width,  minesCount);
        games.put(gameId, game);
        return game;
    }

    public Game makeTurn(String gameId, int col, int row) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Игра не найдена");
        }
        if (game.isCompleted()) {
            throw new IllegalStateException("Игра уже завершена");
        }

        game.revealCell(row, col);

        if(game.isCompleted()){
            revealAllBombs(game,"X");
        }
        else {
            // Проверяем, выиграл ли игрок
            if (checkWinCondition(game)) {
                game.setCompleted(true);
                revealAllBombs(game,"M");
            }
        }
        return game;
    }


    private void revealAllBombs(Game game, String flag) {
        boolean[][] bombs = game.getBombs();
        String[][] field = game.getField();
        boolean[][] revealed = game.getRevealed();

        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                if (bombs[i][j]) {
                    field[i][j] = flag; // Показываем все бомбы
                }
                if(!revealed[i][j]){
                    game.revealCell(i,j);
                }
            }
        }
    }

    private boolean checkWinCondition(Game game) {
        boolean[][] bombs = game.getBombs();
        boolean[][] revealed = game.getRevealed();
        for (int i = 0; i < game.getHeight(); i++) {
            for (int j = 0; j < game.getWidth(); j++) {
                // Если есть неоткрытая ячейка без бомбы, игра не завершена
                if (!bombs[i][j] && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
