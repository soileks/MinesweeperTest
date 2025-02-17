package ru.petrov.test.service;

import ru.petrov.test.model.Game;
import ru.petrov.test.model.GameSettings;
import ru.petrov.test.model.TurnRequest;

public interface InterfaceGameService {
    Game createNewGame(GameSettings settings);
    Game makeTurn(TurnRequest turn);

}
