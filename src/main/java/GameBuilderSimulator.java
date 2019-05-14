import model.Box;
import service.NewGameBuilder;
import support.AdditionalReward;

import java.util.*;

public class GameBuilderSimulator {

    private int doYouChoseChance(List<Box> boxesNotInUse, int randomSelection) {
        if (boxesNotInUse.get(randomSelection).getName().equals("Chance")) {
            return 1;
        } else return 0;
    }

    public List<Integer> symulationGames(List<Box> boxes, Properties properties) {
        int chances = 0;
        int reward;
        boolean round;
        boolean isUsedSecondChance;
        int additionalChanceOrReward = 0;
        NewGameBuilder newGameBuilder = new NewGameBuilder();
        List<Integer> wyniki = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_100EURO_BOXES")); i++) {
            reward = 0;
            round = true;
            newGameBuilder.resetChosenBoxes(boxes);
            Collections.shuffle(boxes);
            isUsedSecondChance = false;
            do {
                List<Box> boxesNotInUse = new ArrayList<>(getBoxesNotInUse(boxes));
                int randomSelection = random.nextInt(boxesNotInUse.size());// Wybierz jedną z pozostałych
                boxesNotInUse.get(randomSelection);
                chances+=doYouChoseChance(boxesNotInUse,randomSelection);
                if (boxesNotInUse.get(randomSelection).isGameOver() && chances == 0) {
                    additionalChanceOrReward = randomAdditionalReward(isUsedSecondChance);

                    if (additionalChanceOrReward == 1) {
                        newGameBuilder.resetChosenBoxes(boxes);
                        isUsedSecondChance = true;
                    } else {
                        reward += additionalChanceOrReward;
                        additionalChanceOrReward = 0;
                        wyniki.add(reward);
                        round = false;

                    }


                } else if (boxesNotInUse.get(randomSelection).isGameOver() && chances == 1) {
                    chances--;
                    boxesNotInUse.get(randomSelection).setChosen(true);
                } else {
                    boxesNotInUse.get(randomSelection).setChosen(true);
                    reward += boxesNotInUse.get(randomSelection).getReward();
                }
            } while (round);
        }
        return wyniki;
    }

    //Tworzy liste pozostałych skrzynek na której działa symulacja
    private static List<Box> getBoxesNotInUse(List<Box> boxes) {
        List<Box> boxesNotInUse = new ArrayList<Box>();
        for (Box box : boxes) {
            if (!box.isChosen()) {
                boxesNotInUse.add(box);
            }
        }
        return boxesNotInUse;
    }

    private static int randomAdditionalReward(boolean isUsedSecondChance) {
        AdditionalReward additionalReward;

        if (isUsedSecondChance) {//bez chance
            additionalReward = AdditionalReward.randomRewardWithoutChance();
            return additionalReward.getReward();
        } else {//wszystkie z chance
            additionalReward = AdditionalReward.randomReward();
            if ((additionalReward == AdditionalReward.SMALL) ||
                    (additionalReward == AdditionalReward.MEDIUM) ||
                    (additionalReward == AdditionalReward.LARGE)) {
                return additionalReward.getReward();
            } else return 1;
        }
    }
}
