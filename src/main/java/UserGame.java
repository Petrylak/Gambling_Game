import model.Box;
import model.Game;
import service.NewGameBuilder;
import support.AdditionalRewardCode;
import support.BoxOperations;

import java.util.List;
import java.util.Properties;

public class UserGame {

    void userGames(Properties properties) {

        NewGameBuilder newGameBuilder = new NewGameBuilder();
        AdditionalRewardCode additionalReward = new AdditionalRewardCode();
        BoxOperations boxOperations = new BoxOperations();

        List<Box> boxes = newGameBuilder.createBoxes2(properties);
        System.out.println(newGameBuilder.toString(boxes));

        Game userGame = new Game
                (0, 0, 0,
                        null, false, false, boxes);

        do {
            System.out.println();
            System.out.println(properties.getProperty("TEXT_SELECT_NUMBER"));
            boxOperations.showBoxesNotInUse(userGame.getCreatedBoxes());
            boxOperations.choosingBox(properties, userGame).actionUserGame(userGame, properties, additionalReward);
        } while (!userGame.isEndRound());
    }
}