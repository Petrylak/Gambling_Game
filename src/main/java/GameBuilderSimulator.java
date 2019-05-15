import model.Box;
import model.Game;
import service.NewGameBuilder;
import support.AdditionalRewardCode;
import support.BoxOperations;

import java.util.*;
import java.util.stream.Collectors;

public class GameBuilderSimulator {


    public List<Integer> symulationGamesWork(List<Box> boxes) {
        int chances = 0;
        int reward = 0;
        boolean round = true;
        List<Integer> wyniki = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            reward = 0;
            round = true;
            BoxOperations boxOperations = new BoxOperations();
            NewGameBuilder boxReset = new NewGameBuilder();
            boxReset.resetChosenBoxes(boxes);
            Collections.shuffle(boxes);
            do {
                List<Box> boxesNotInUse = new ArrayList<Box>(getBoxesNotInUse(boxes));
                int randomSelection = random.nextInt(boxesNotInUse.size());// Wybierz jedną z pozostałych
                boxesNotInUse.get(randomSelection);
                if (boxesNotInUse.get(randomSelection).getName().equals("Chance")) {
                    chances++;
                }
                if (boxesNotInUse.get(randomSelection).isGameOver() && chances == 0) {
                    wyniki.add(reward);
                    round = false;
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


    public List<Integer> symulationGamesNotWork(Properties properties) {

        List<Integer> rewardsList = new ArrayList<>();


        //userGame.setRewardsList(new ArrayList<>());

        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS")); i++) {

            NewGameBuilder newGameBuilder = new NewGameBuilder();
            BoxOperations boxOperations = new BoxOperations();
            List<Box> boxes = newGameBuilder.createBoxes2(properties);

            Game userGame = new Game
                    (0, 0, 0,
                            null, false, false, boxes, 0, true);



            do {
                boxOperations.choosingBoxSimulation(userGame).actionSimulationGame(userGame,rewardsList);
            } while (!userGame.isEndRound());
        }
        return rewardsList;
    }

    private static List<Box> getBoxesNotInUse(List<Box> boxes) {// testowo
        List<Box> boxesNotInUse = new ArrayList<>();
        for (Box box : boxes) {
            if (!box.isChosen()) {
                boxesNotInUse.add(box);
            }
        }
        return boxesNotInUse;
    }
}
