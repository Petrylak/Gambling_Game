import model.Box;
import service.NewGameBuilder;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int userChoose = 0;
        boolean game = true;

        UserGame userGame = new UserGame();
        GameBuilderSimulator gameBuilderSimulator = new GameBuilderSimulator();
        NewGameBuilder newGameBuilder = new NewGameBuilder();
        IOProperties ioProperties = new IOProperties();
        Properties properties = ioProperties.getPropertiesFromFile();

        List<Box> boxes = newGameBuilder.createBoxes2(properties);




        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println(properties.getProperty("TEXT_SELECT_OPTION"));
                System.out.println(properties.getProperty("TEXT_OPTION_NEWGAME"));
                System.out.println(properties.getProperty("TEXT_OPTION_NEWSIMULATION"));
                System.out.println(properties.getProperty("TEXT_OPTION_CLOSE"));
                userChoose = scanner.nextInt();


                switch (userChoose) {
                    case 1: {
                        userGame.userGames(properties);
                        break;
                    }
                    case 2: {
                        int sum = 0;
                        int avarageReward;
                        List<Integer> boxesFromSimulation = new ArrayList<>
                                (gameBuilderSimulator.symulationGames(boxes, properties));// do zmiany boxes

                        System.out.println(boxesFromSimulation);


                        for (int i = 0; i < boxesFromSimulation.size(); i++) {
                            sum += boxesFromSimulation.get(i);
                        }
                        avarageReward = sum / Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS"));

                        System.out.println(properties.getProperty("TEXT_SUM_REWARDS")
                                + Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS"))
                                + properties.getProperty("TEXT_SUM_REWARDS2")
                                + sum
                                + properties.getProperty("CURRENCY"));

                        System.out.println(properties.getProperty("TEXT_AVERAGE_WINS")
                                + Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS"))
                                + properties.getProperty("TEXT_AVERAGE_WINS2")
                                + avarageReward
                                + properties.getProperty("CURRENCY"));

                    }
                    case 3: {
                        game = false;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } catch (InputMismatchException mismatchE) {
                System.out.println(properties.getProperty("TEXT_WARNING_INT"));
            }

        } while (game);
    }


}