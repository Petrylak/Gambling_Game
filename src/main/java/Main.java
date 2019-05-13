import model.Box;
import games.GameConfiguration;
import games.GameBuilderSimulator;
import games.UserGame;
import games.service.NewGameBuilder;
import model.Game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int userChoose = 0;
        boolean game = true;

        UserGame userGame = new UserGame();

        GameBuilderSimulator gameBuilderSimulator = new GameBuilderSimulator();
        NewGameBuilder newGameBuilder = new NewGameBuilder();
        List<Box> boxes = newGameBuilder.createBoxes();




        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Wybierz opcje: ");
                System.out.println("1 - Nowa Gra");
                System.out.println("2 - Nowa Symulacja");
                System.out.println("3 - Koniec");
                userChoose = scanner.nextInt();


                switch (userChoose) {
                    case 1: {
                        userGame.userGames();
                        break;
                    }
                    case 2: {
                        int sum = 0;
                        int avarageReward;
                        List<Integer> boxesFromSimulation = new ArrayList<>
                                (gameBuilderSimulator.symulationGames(boxes));// do zmiany boxes

                        System.out.println(boxesFromSimulation);


                        for (int i = 0; i < boxesFromSimulation.size(); i++) {
                            sum += boxesFromSimulation.get(i);
                        }
                        avarageReward = sum / GameConfiguration.NUMBER_OF_SIMULATIONS;
                        //  dominanta(boxesFromSimulation);
//                        System.out.println("Po posortowaniu: ");
//                        System.out.println(boxesFromSimulation);

                        //SimulationCalculations simulationCalculations = new SimulationCalculations();
                        System.out.println("Suma wygranych z " + GameConfiguration.NUMBER_OF_SIMULATIONS
                                + " sumulacji wynosi: " + sum + " $");
                        System.out.println("Średnia wygranych z " + GameConfiguration.NUMBER_OF_SIMULATIONS
                                + " sumulacji wynosi: " + avarageReward + " $");

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
                System.out.println("Możesz wprowadzać tylko cyfry !");
            }

        } while (game);
    }


}