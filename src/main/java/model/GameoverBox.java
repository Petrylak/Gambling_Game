package model;

import support.AdditionalReward;
import support.AdditionalRewardCode;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class GameoverBox extends Box {

    public GameoverBox(String name, boolean gameOver) {
        super(name, gameOver);
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
                //   newGameBuilder.resetChosenBoxes(game.getCreatedBoxes());
                game.setAdditionalChanceOrReward(0);
                game.setUsedSecondChance(true);
                Collections.shuffle(game.getCreatedBoxes());
            } else {
                game.setReward(game.getReward() + game.getAdditionalChanceOrReward());
                game.setAdditionalChanceOrReward(0);
                game.setEndRound(true);
                if (game.getReward() == 0 && game.getAdditionalChanceOrReward() == 0) {
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_LOSE"));

                } else if (game.getReward() > 0 && game.getAdditionalChanceOrReward() == 0) {
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_REWARD")
                            + game.getReward()
                            + properties.getProperty("CURRENCY"));

                }

            }


            System.out.println();
        }
    }

    @Override
    public void actionSimulationGame(Game game, List<Integer> rewardsList) {
        AdditionalRewardCode additionalRewardCode = new AdditionalRewardCode();
        if (game.getChances() == 1) {
            game.setChances(0);
            game.getChosenBox().setChosen(true);
        } else {

            game.setAdditionalChanceOrReward(additionalRewardCode.randomAdditionalRewardSimulation(game));
            if (game.getAdditionalChanceOrReward() == 1) {
                game.setAdditionalChanceOrReward(0);
                game.setUsedSecondChance(true);
                Collections.shuffle(game.getCreatedBoxes());
            } else {
                game.setReward(game.getReward() + game.getAdditionalChanceOrReward());
                game.setAdditionalChanceOrReward(0);
                rewardsList.add(game.getReward());
                game.setEndRound(true);
            }


        }
    }
}

