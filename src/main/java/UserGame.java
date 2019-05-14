import model.Box;
import model.ChanceBox;
import service.NewGameBuilder;
import support.AdditionalReward;
import model.Game;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserGame {

//private static void chosenBoxIsChance(Box chosenBox, Game userGame){
//     if (chosenBox.getName().equals("Chance")) {
//        System.out.println();
//        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
//        userGame.setChances(1);
//    }
//}

    public void userGames(Properties properties) {

        NewGameBuilder newGameBuilder = new NewGameBuilder();
        List<Box> boxes = newGameBuilder.createBoxes2(properties);

        System.out.println(newGameBuilder.toString(boxes));//Wypisanie listy skrzynek
        Game userGame = new Game
                (0,0,0,
                        null,false,false, boxes);



        do {
            System.out.println();
            System.out.println(properties.getProperty("TEXT_SELECT_NUMBER"));
            showBoxesNotInUse(userGame.getCreatedBoxes());//Pokaż skrzynki które nie zostały wybrane
            choosingBox(properties, userGame).action();//Wybór skrzynki



       //     userGame.getChosenBox().action();
            //chosenBoxIsChance(userGame.getChosenBox(),userGame);//Sprawdzenie czy skrzynka jest szansą





            if (userGame.getChosenBox().isGameOver() && userGame.getChances() == 0) {
                System.out.println();
                Collections.shuffle(userGame.getCreatedBoxes());
                userGame.setAdditionalChanceOrReward(randomAdditionalReward(userGame.isUsedSecondChance(),properties));
                if(userGame.getAdditionalChanceOrReward() == 1){
                   newGameBuilder.resetChosenBoxes(userGame.getCreatedBoxes());
                   userGame.setUsedSecondChance(true);
                }else {
                    userGame.setReward(userGame.getReward()+userGame.getAdditionalChanceOrReward());
                    userGame.setAdditionalChanceOrReward(0);
                }



                    if (userGame.getReward() == 0 && userGame.getAdditionalChanceOrReward() == 0) {
                        System.out.println();
                        System.out.println(properties.getProperty("TEXT_ENDGAME_LOSE"));
                        userGame.setEndRound(true);
                    } else if (userGame.getReward() > 0 && userGame.getAdditionalChanceOrReward() == 0){
                        System.out.println();
                        System.out.println(properties.getProperty("TEXT_ENDGAME_REWARD")
                                + userGame.getReward()
                                + properties.getProperty("CURRENCY"));
                        userGame.setEndRound(true);
                    }

            } else if (userGame.getChosenBox().isGameOver() && userGame.getChances() == 1) {
                userGame.setChances(0);
                System.out.println();
                System.out.println(properties.getProperty("TEXT_WARNING_USECHANCE"));
                userGame.getChosenBox().setChosen(true);
            } else {
                userGame.getChosenBox().setChosen(true);
                userGame.setReward(userGame.getReward()+userGame.getChosenBox().getReward());
                System.out.println();
                System.out.println(properties.getProperty("TEXT_GAME_BALANCE") + userGame.getReward() + " $");
            }
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
                //return userGame.getCreatedBoxes().get(userChooseCard);
                userGame.setChosenBox(userGame.getCreatedBoxes().get(userChooseCard));
                return userGame.getChosenBox();//toooo
            } catch (IndexOutOfBoundsException exception) {
                System.out.println(properties.getProperty("TEXT_SELECT_BOX_RANGE"));
            }
        } while (error);
        userGame.setChosenBox(userGame.getCreatedBoxes().get(userChooseCard));
        return userGame.getChosenBox();//toooo
       // return userGame.getCreatedBoxes().get(userChooseCard);
    }

    private static void showBoxesNotInUse(List<Box> boxes) {
        for (Box box : boxes) {
            if (!box.isChosen()) {
                System.out.print(boxes.indexOf(box) + 1 + " ");
            }
        }
    }

    private static int randomAdditionalReward(boolean isUsedSecondChance, Properties properties) {
        AdditionalReward additionalReward;

        if(isUsedSecondChance){
            System.out.println(properties.getProperty("TEXT_WARNING_USEDSECCONDCHANCE"));
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = AdditionalReward.randomRewardWithoutChance();
            System.out.println(additionalReward.getDescription());
            return additionalReward.getReward();
        }else {
            System.out.println(properties.getProperty("TEXT_GAME_ADDITIONALREWARDCHANCE"));
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription() + " / " +
                    AdditionalReward.CHANCE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = AdditionalReward.randomReward();
            System.out.println(additionalReward.getDescription());
            if ((additionalReward == AdditionalReward.SMALL) ||
                    (additionalReward == AdditionalReward.MEDIUM) ||
                    (additionalReward == AdditionalReward.LARGE)) {
                return additionalReward.getReward();
            } else return 1;
        }
    }
}

