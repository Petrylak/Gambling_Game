package main;

import model.Box;
import model.Game;
import support.AdditionalRewardCode;
import support.BoxOperations;
import support.NewGameBuilder;

import java.util.List;
import java.util.Properties;

public class UserGame {

    void userGames(Properties properties) {

        NewGameBuilder newGameBuilder = new NewGameBuilder();
        AdditionalRewardCode additionalReward = new AdditionalRewardCode();
        BoxOperations boxOperations = new BoxOperations();

        List<Box> boxes = newGameBuilder.createBoxes(properties);

        Game userGame = new Game
                (0, 0, 0,
                        null, false, false, boxes);

        do {
            System.out.println();
            System.out.println(properties.getProperty("TEXT_SELECT_NUMBER"));
            boxOperations.showBoxesNotInUse(userGame.getCreatedBoxes());
            boxOperations.chosingBox(properties, userGame).actionUserGame(userGame, properties, additionalReward);
        } while (!userGame.isEndRound());
    }
}