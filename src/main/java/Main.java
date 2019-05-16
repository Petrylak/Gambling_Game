import java.util.*;


public class Main {

    public static void main(String[] args) {
        int userChoose = 0;
        boolean game = true;

        UserGame userGame = new UserGame();
        GameBuilderSimulator gameBuilderSimulator = new GameBuilderSimulator();
        IOProperties ioProperties = new IOProperties();
        Properties properties = ioProperties.getPropertiesFromFile();


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
                        newSimulation(gameBuilderSimulator, properties);
                        break;
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

    private static void newSimulation(GameBuilderSimulator gameBuilderSimulator, Properties properties){
        List<Long> balance = gameBuilderSimulator.simulationGames(properties);
        long sum=0;
        for (int i = 0; i < balance.size(); i++) {
            sum += balance.get(i);
        }

        long averageReward = sum / Long.parseLong(properties.getProperty("NUMBER_OF_SIMULATIONS"));


        System.out.println(properties.getProperty("TEXT_SUM_REWARDS")
                + Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS"))
                + properties.getProperty("TEXT_SUM_REWARDS2")
                + sum
                + properties.getProperty("CURRENCY"));

        System.out.println(properties.getProperty("TEXT_AVERAGE_WINS")
                + Integer.parseInt(properties.getProperty("NUMBER_OF_SIMULATIONS"))
                + properties.getProperty("TEXT_AVERAGE_WINS2")
                + averageReward
                + properties.getProperty("CURRENCY"));
    }

}