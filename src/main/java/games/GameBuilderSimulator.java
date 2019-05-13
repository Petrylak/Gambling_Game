package games;

import boxes.Box;
import games.service.NewGameBuilder;
import games.support.AdditionalReward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameBuilderSimulator extends NewGameBuilder {

    public List<Integer> symulationGames(List<Box> boxes) {
        int chances = 0;
        int reward;
        boolean round;
        boolean isUsedSecondChance;
        int additionalChanceOrReward = 0;
        List<Integer> wyniki = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < GameConfiguration.NUMBER_OF_SIMULATIONS; i++) {
            reward = 0;
            round = true;
            resetChosenBoxes(boxes);
            Collections.shuffle(boxes);
            isUsedSecondChance = false;
            do {
                List<Box> boxesNotInUse = new ArrayList<>(getBoxesNotInUse(boxes));
                int randomSelection = random.nextInt(boxesNotInUse.size());// Wybierz jedną z pozostałych
                boxesNotInUse.get(randomSelection);
                if (boxesNotInUse.get(randomSelection).getName().equals("Chance")) {
                    chances++;
                }
                if (boxesNotInUse.get(randomSelection).isGameOver() && chances == 0) {
                    additionalChanceOrReward = randomAdditionalReward(isUsedSecondChance);

                    if (additionalChanceOrReward == 1) {
                        resetChosenBoxes(boxes);
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
