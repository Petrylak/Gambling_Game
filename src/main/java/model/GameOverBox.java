package model;

import support.AdditionalRewardCode;
import support.NewGameBuilder;

import java.util.List;
import java.util.Properties;

public class GameOverBox extends Box {

    public GameOverBox(String name) {
        super(name);
    }

    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
        if (game.getChances() == 1) {
            game.setChances(0);
            game.getChosenBox().setChosen(true);
            System.out.println();
            System.out.println(properties.getProperty("TEXT_WARNING_USECHANCE"));
        } else {
            additionalRewardCode.randomAdditionalReward(properties, game);
            if (game.getAdditionalChanceOrReward() == 1) {
                game.setAdditionalChanceOrReward(0);
                game.setUsedSecondChance(true);
                NewGameBuilder newGameBuilder = new NewGameBuilder();
                game.setCreatedBoxes(newGameBuilder.createBoxes(properties));
            } else {
                game.setReward(game.getReward() + game.getAdditionalChanceOrReward());
                game.setAdditionalChanceOrReward(0);
                game.setEndRound(true);
                if (game.getReward() == 0 && game.getAdditionalChanceOrReward() == 0) {
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_LOSE"));
                    game.setEndRound(true);
                } else if (game.getReward() > 0 && game.getAdditionalChanceOrReward() == 0) {// tu jest blad do poprawy
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_REWARD")
                            + game.getReward()
                            + properties.getProperty("CURRENCY"));
                    game.setEndRound(true);
                }

            }
            System.out.println();
        }
    }

    @Override
    public void actionSimulation(Game userGame, NewGameBuilder newGameBuilder, List<Long> balance) {

        if (userGame.getChances() == 0) {
            userGame.setAdditionalChanceOrReward(randomAdditionalReward(userGame.isUsedSecondChance()));

            if (userGame.getAdditionalChanceOrReward() == 1) {
                newGameBuilder.resetChosenBoxes(userGame.getCreatedBoxes());
                userGame.setUsedSecondChance(true);
            } else {
                userGame.setReward(userGame.getAdditionalChanceOrReward() + userGame.getReward());
                balance.add(userGame.getReward());
                userGame.setEndRound(true);

            }

        } else if (userGame.getChances() == 1) {
            userGame.setChances(0);

        }


    }

    private static int randomAdditionalReward(boolean isUsedSecondChance) {
        AdditionalReward additionalReward;

        if (isUsedSecondChance) {
            additionalReward = AdditionalReward.randomRewardWithoutChance();
            return additionalReward.getReward();
        } else {
            additionalReward = AdditionalReward.randomReward();
            if ((additionalReward == AdditionalReward.SMALL) ||
                    (additionalReward == AdditionalReward.MEDIUM) ||
                    (additionalReward == AdditionalReward.LARGE)) {
                return additionalReward.getReward();
            } else return 1;
        }
    }
}