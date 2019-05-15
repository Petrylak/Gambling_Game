package support;

import model.Box;
import model.Game;

import java.util.*;
import java.util.stream.Collectors;

public class BoxOperations {

    public Box choosingBoxSimulation(Game userGame) {


        chosingSimulationNumber(userGame);
      // System.out.println(userGame.getSimulationNumber());
        userGame.setChosenBox(userGame.getCreatedBoxes().get(userGame.getSimulationNumber()));
        userGame.getChosenBox().setChosen(true);


        return userGame.getChosenBox();
    }

    public Box choosingBox(Properties properties, Game userGame) {
        boolean error = true;
        do {
            try {
                chosingUserNumber(userGame);

                if (userGame.getCreatedBoxes().get(userGame.getUserNumber()).isChosen()) {
                    isChosen(userGame, properties);
                }

                wasntChosen(userGame, properties);
                error = false;
                return userGame.getChosenBox();
            } catch (IndexOutOfBoundsException exception) {
                System.out.println(properties.getProperty("TEXT_SELECT_BOX_RANGE"));
            }
        } while (error);

        return userGame.getChosenBox();
    }

    public void showBoxesNotInUse(List<Box> boxes) {

        boxes.stream().filter(box ->!box.isChosen()).map(box -> boxes.indexOf(box) + 1 + " ").forEach(System.out::print);

//        for (Box box : boxes) {
//            if (!box.isChosen()) {
//                System.out.print(boxes.indexOf(box) + 1 + " ");
//            }
//        }
    }

    private void isChosenSimulation(Game game) {
        do {
            chosingSimulationNumber(game);
        } while (game.getCreatedBoxes().get(game.getUserNumber()).isChosen());
    }

    private void isChosen(Game game, Properties properties) {
        do {
            System.out.println(properties.getProperty("TEXT_WARNING_USEDBOX"));
            showBoxesNotInUse(game.getCreatedBoxes());
            System.out.println();
            System.out.println(properties.getProperty("TEXT_SELECT_ONE_OF_NUMBERS"));
            chosingUserNumber(game);
        } while (game.getCreatedBoxes().get(game.getUserNumber()).isChosen());
    }

    private static void wasntChosen(Game game, Properties properties) {
        System.out.println();
        System.out.println(properties.getProperty("TEXT_GAME_CHOSEN_BOX"));
        System.out.println(game.getCreatedBoxes().get(game.getUserNumber()).getName());
        game.setChosenBox(game.getCreatedBoxes().get(game.getUserNumber()));//tooooo
    }

    private void chosingUserNumber(Game game) {
        Scanner scanner = new Scanner(System.in);
        game.setUserNumber(scanner.nextInt() - 1);
    }

    private void chosingSimulationNumber(Game game) {
        Random random = new Random();
        do {
            game.setBoxesNotInUse(getBoxesNotInUse(game.getCreatedBoxes()));//// TESTOWO
        game.setSimulationNumber(random.nextInt(game.getBoxesNotInUse().size()));
        } while (game.getCreatedBoxes().get(game.getSimulationNumber()).isChosen());
    }
    private static List<Box> getBoxesNotInUse(List<Box> boxes) {
        List<Box> boxesNotInUse = new ArrayList<>();
        for (Box box : boxes) {
            if (!box.isChosen()) {
                boxesNotInUse.add(box);
            }
        }
        return boxesNotInUse;
    }
}