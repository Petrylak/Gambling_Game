package support;

import model.AdditionalReward;
import model.Game;

import java.util.Properties;

public class AdditionalRewardCode {
    public Game randomAdditionalReward(Properties properties, Game game) {
        AdditionalReward additionalReward;

        if(game.isUsedSecondChance()){
            System.out.println(properties.getProperty("TEXT_WARNING_USEDSECCONDCHANCE"));
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = AdditionalReward.randomRewardWithoutChance();
            System.out.println(additionalReward.getDescription());
            game.setReward(game.getReward() + additionalReward.getReward());
            return game;
        }else {
            System.out.println(properties.getProperty("TEXT_GAME_ADDITIONALREWARDCHANCE"));
            System.out.println(AdditionalReward.SMALL.getDescription() + " / " +
                    AdditionalReward.MEDIUM.getDescription() + " / " +
                    AdditionalReward.LARGE.getDescription() + " / " +
                    AdditionalReward.CHANCE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = AdditionalReward.randomReward();
            System.out.println(additionalReward.getDescription());
            if ((additionalReward == AdditionalReward.SMALL) ||
                    (additionalReward == AdditionalReward.MEDIUM) ||
                    (additionalReward == AdditionalReward.LARGE)) {
                game.setReward(game.getReward() + additionalReward.getReward());
                return game;
            } else {
                game.setAdditionalChanceOrReward(1);
                return game;
            }
        }
    }
}
