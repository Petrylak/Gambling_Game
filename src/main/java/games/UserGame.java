package games;

import model.Box;
import games.service.NewGameBuilder;
import games.support.AdditionalReward;
import model.Game;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserGame {

private static void chosenBoxIsChance(Box chosenBox, Game userGame){
     if (chosenBox.getName().equals("Chance")) {
        System.out.println();
        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
        userGame.setChances(1);
    }
}

    public void userGames() {

        NewGameBuilder newGameBuilder = new NewGameBuilder();
        List<Box> boxes = newGameBuilder.createBoxes();

        System.out.println(newGameBuilder.toString(boxes));//Wypisanie listy skrzynek
        Game userGame = new Game
                (0,0,0,
                        null,false,false, boxes);

        boolean isUsedSecondChance = false;
        Box chosenBox;
        int additionalChanceOrReward;

        do {
            System.out.println();
            System.out.println("Wybierz numer: ");
            showBoxesNotInUse(boxes);//Pokaż skrzynki które nie zostały wybrane
            chosenBox=chosenBox(boxes);//Wybór skrzynki
            chosenBoxIsChance(chosenBox,userGame);//Sprawdzenie czy skrzynka jest szansą






            if (chosenBox.isGameOver() && userGame.getChances() == 0) {
                System.out.println();
                Collections.shuffle(boxes);
                additionalChanceOrReward=randomAdditionalReward(isUsedSecondChance);
                if(additionalChanceOrReward == 1){
                   newGameBuilder.resetChosenBoxes(boxes);
                   isUsedSecondChance=true;
                }else {
                    userGame.setReward(userGame.getReward()+additionalChanceOrReward);
                    additionalChanceOrReward = 0;
                }



                    if (userGame.getReward() == 0 && additionalChanceOrReward == 0) {
                        System.out.println();
                        System.out.println("Przykro nam tym razem nie udało Ci się nic wygrać");
                        userGame.setEndRound(true);
                    } else if (userGame.getReward() > 0 && additionalChanceOrReward == 0){
                        System.out.println();
                        System.out.println("Gra skończona udało Ci się wygrać: " + userGame.getReward() + " $");
                        userGame.setEndRound(true);
                    }



            } else if (chosenBox.isGameOver() && userGame.getChances() == 1) {
                userGame.setChances(0);
                System.out.println();
                System.out.println("Wykorzystałeś posiadaną szanse !");
                chosenBox.setChosen(true);
            } else {
                chosenBox.setChosen(true);
                userGame.setReward(userGame.getReward()+chosenBox.getReward());
                System.out.println();
                System.out.println("Udało Ci się wygrać do tej pory: " + userGame.getReward() + " $");
            }
        } while (!userGame.isEndRound());
    }


    private static Box chosenBox(List<Box> boxes) {
        Scanner scanner = new Scanner(System.in);
        int userChooseCard = 0;
        boolean error = true;
        do {
            try {
                userChooseCard = scanner.nextInt() - 1;
                if (boxes.get(userChooseCard).isChosen()) {
                    do {
                        System.out.println("Ta skrzynka została już wybrana, pozostałe skrzynki do wyboru to: ");
                        showBoxesNotInUse(boxes);
                        System.out.println();
                        System.out.println("Podaj jeden z powyższych numerów: ");
                        userChooseCard = scanner.nextInt() - 1;
                    } while (boxes.get(userChooseCard).isChosen());
                }
                System.out.println();
                System.out.println("Skrzynka wybrana przez Ciebie to : ");
                System.out.println(boxes.get(userChooseCard).getName());
                error = false;
                return boxes.get(userChooseCard);

            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Wybierz skrzynkę od 1 do 12: ");
            }
        } while (error);
        return boxes.get(userChooseCard);
    }

    private static void showBoxesNotInUse(List<Box> boxes) {
        for (Box box : boxes) {
            if (!box.isChosen()) {
                System.out.print(boxes.indexOf(box) + 1 + " ");
            }
        }
    }

    private static int randomAdditionalReward(boolean isUsedSecondChance) {
        AdditionalReward additionalReward;

        if(isUsedSecondChance){
            System.out.println("Wykorzystałeś już drugą szanse jednak masz nadal szanse na wylosowanie dodatkowej nagrody w postaci: ");
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription());
            System.out.println("Wylosowałeś:");
            additionalReward = AdditionalReward.randomRewardWithoutChance();
            System.out.println(additionalReward.getDescription());
            return additionalReward.getReward();
        }else {
            System.out.println("Masz szansę na wylosowanie dodatkowej nagrody w postaci: ");
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription() + " / " +
                    AdditionalReward.CHANCE.getDescription());
            System.out.println("Wylosowałeś:");
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


