import model.Box;
import service.NewGameBuilder;
import model.Game;
import support.AdditionalRewardCode;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserGame {

    public void userGames(Properties properties) {

        NewGameBuilder newGameBuilder = new NewGameBuilder();
        List<Box> boxes = newGameBuilder.createBoxes2(properties);
        AdditionalRewardCode additionalReward = new AdditionalRewardCode();

        System.out.println(newGameBuilder.toString(boxes));//Wypisanie listy skrzynek
        Game userGame = new Game
                (0, 0, 0,
                        null, false, false, boxes);


        do {
            System.out.println();
            System.out.println(properties.getProperty("TEXT_SELECT_NUMBER"));
            showBoxesNotInUse(userGame.getCreatedBoxes());//Pokaż skrzynki które nie zostały wybrane
            choosingBox(properties, userGame).action(userGame, properties, additionalReward);//Wybór skrzynki

        } while (!userGame.isEndRound());
    }


    private static Box choosingBox(Properties properties, Game userGame) {
        Scanner scanner = new Scanner(System.in);
        int userChooseCard = 0;
        boolean error = true;
        do {
            try {
                userChooseCard = scanner.nextInt() - 1;
                if (userGame.getCreatedBoxes().get(userChooseCard).isChosen()) {
                    do {
                        System.out.println(properties.getProperty("TEXT_WARNING_USEDBOX"));
                        showBoxesNotInUse(userGame.getCreatedBoxes());
                        System.out.println();
                        System.out.println(properties.getProperty("TEXT_SELECT_ONE_OF_NUMBERS"));
                        userChooseCard = scanner.nextInt() - 1;
                    } while (userGame.getCreatedBoxes().get(userChooseCard).isChosen());
                }
                System.out.println();
                System.out.println(properties.getProperty("TEXT_GAME_CHOSEN_BOX"));
                System.out.println(userGame.getCreatedBoxes().get(userChooseCard).getName());
                error = false;
                userGame.setChosenBox(userGame.getCreatedBoxes().get(userChooseCard));//tooooo
                return userGame.getChosenBox();
            } catch (IndexOutOfBoundsException exception) {
                System.out.println(properties.getProperty("TEXT_SELECT_BOX_RANGE"));
            }
        } while (error);
        userGame.setChosenBox(userGame.getCreatedBoxes().get(userChooseCard));
        return userGame.getChosenBox();
    }

    private static void showBoxesNotInUse(List<Box> boxes) {
        for (Box box : boxes) {
            if (!box.isChosen()) {
                System.out.print(boxes.indexOf(box) + 1 + " ");
            }
        }
    }
}


