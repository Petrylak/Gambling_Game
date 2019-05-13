package games;

import boxes.Box;
import games.service.NewGameBuilder;
import games.support.AdditionalReward;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserGame extends NewGameBuilder {


    public void userGames(List<Box> boxes) {
        boolean round = true;
        int userChoose;
        int chances = 0;
        int reward = 0;
        boolean isUsedSecondChance = false;
        int additionalChanceOrReward = 0;

        do {
            System.out.println();
            System.out.println("Wybierz jedno z pudełek: ");
            boxesNotInUse(boxes);
            Box boxChoosed = choosingBox(boxes);
            if (boxChoosed.getName() == "Chance") {
                System.out.println();
                System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
                chances++;
            }
            if (boxChoosed.isGameOver() && chances == 0) {
                System.out.println();
                Collections.shuffle(boxes);
                additionalChanceOrReward=randomAdditionalReward(isUsedSecondChance);
                if(additionalChanceOrReward == 1){
                   resetChosenBoxes(boxes);
                   isUsedSecondChance=true;
                }else {
                    reward+=additionalChanceOrReward;
                    additionalChanceOrReward = 0;
                }
                    if (reward == 0 && additionalChanceOrReward == 0) {
                        System.out.println();
                        System.out.println("Przykro nam tym razem nie udało Ci się nic wygrać");
                        round = false;
                    } else if (reward > 0 && additionalChanceOrReward == 0){
                        System.out.println();
                        System.out.println("Gra skończona udało Ci się wygrać: " + reward + " $");
                        round = false;
                    }



            } else if (boxChoosed.isGameOver() && chances == 1) {
                chances--;
                System.out.println();
                System.out.println("Wykorzystałeś posiadaną szanse !");
                boxChoosed.setChosen(true);
            } else {
                boxChoosed.setChosen(true);
                reward += boxChoosed.getReward();
                System.out.println();
                System.out.println("Udało Ci się wygrać do tej pory: " + reward + " $");
            }
        } while (round);
    }


    private static Box choosingBox(List<Box> boxes) {
        Scanner scanner = new Scanner(System.in);
        int userChooseCard = 0;
        boolean error = true;
        do {
            try {
                userChooseCard = scanner.nextInt() - 1;
                if (boxes.get(userChooseCard).isChosen()) {
                    do {
                        System.out.println("Ta skrzynka została już wybrana, pozostałe skrzynki do wyboru to: ");
                        boxesNotInUse(boxes);
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

    private static void boxesNotInUse(List<Box> boxes) {
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


