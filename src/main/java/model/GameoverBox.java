package model;

import support.AdditionalRewardCode;

import java.util.Collections;
import java.util.Properties;

public class GameoverBox extends Box {

    public GameoverBox(String name, boolean gameOver) {
        super(name, gameOver);
    }

    @Override
    public Game action(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
        if (game.getChances() == 1) {
            game.setChances(0);
            game.getChosenBox().setChosen(true);
            System.out.println();
            System.out.println(properties.getProperty("TEXT_WARNING_USECHANCE"));
        } else {
            additionalRewardCode.randomAdditionalReward(properties,game);
            if(game.getAdditionalChanceOrReward() == 1){
             //   newGameBuilder.resetChosenBoxes(game.getCreatedBoxes());
                game.setAdditionalChanceOrReward(0);
                game.setUsedSecondChance(true);
                Collections.shuffle(game.getCreatedBoxes());
            }else {
                game.setReward(game.getReward()+game.getAdditionalChanceOrReward());
                game.setAdditionalChanceOrReward(0);
                game.setEndRound(true);
                if (game.getReward() == 0 && game.getAdditionalChanceOrReward() == 0) {
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_LOSE"));
                    game.setEndRound(true);
                } else if (game.getReward() > 0 && game.getAdditionalChanceOrReward() == 0){// tu jest blad do poprawy
                    System.out.println();
                    System.out.println(properties.getProperty("TEXT_ENDGAME_REWARD")
                            + game.getReward()
                            + properties.getProperty("CURRENCY"));
                    game.setEndRound(true);
                }

            }



            System.out.println();
        }return game;
    }
}
