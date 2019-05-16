import model.Box;
import model.Game;
import service.NewGameBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class GameBuilderSimulator {

    public List<Integer> symulationGames2(Properties properties) {


        System.out.println("Ładowanie symulacji...");

        List<Integer> wyniki = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS")); i++) {
            NewGameBuilder newGameBuilder = new NewGameBuilder();
            List<Box> list = newGameBuilder.createBoxes2(properties);
            Game userGame = new Game

                    (0, 0, 0,
                            null, false, false, list);

            do {

                List<Box> boxesNotInUse = new ArrayList<>(getBoxesNotInUse(userGame.getCreatedBoxes()));
                simulationChosingBox(boxesNotInUse, userGame);

                userGame.getChosenBox().action2(userGame, newGameBuilder, wyniki);


            } while (!userGame.isEndRound());

        }
        return wyniki;
    }

    public static void simulationChosingBox(List<Box> boxesNotInUse, Game game) {
        Random random = new Random();
        game.setSimulationNumber(random.nextInt(boxesNotInUse.size()));
        game.setChosenBox(boxesNotInUse.get(game.getSimulationNumber()));
        boxesNotInUse.get(game.getSimulationNumber()).setChosen(true);
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
}