package model;

import service.NewGameBuilder;
import support.AdditionalReward;
import support.AdditionalRewardCode;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class GameOverBox extends Box {

    private boolean gameOver;

    public GameOverBox(String name, boolean gameOver) {
        super(name);
        this.gameOver = gameOver;
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
                Collections.shuffle(game.getCreatedBoxes());
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
    public void actionSimulation(Game userGame, NewGameBuilder newGameBuilder, BigInteger balance) {

        if (gameOver && userGame.getChances() == 0) {
            userGame.setAdditionalChanceOrReward(randomAdditionalReward(userGame.isUsedSecondChance()));

            if (userGame.getAdditionalChanceOrReward() == 1) {
                newGameBuilder.resetChosenBoxes(userGame.getCreatedBoxes());
                userGame.setUsedSecondChance(true);
            } else {
                userGame.setReward(userGame.getAdditionalChanceOrReward() + userGame.getReward());
                balance+= (userGame.getReward());
                userGame.setEndRound(true);

            }

        } else if (gameOver && userGame.getChances() == 1) {
            userGame.setChances(0);

        }


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