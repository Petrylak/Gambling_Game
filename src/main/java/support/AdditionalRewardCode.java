package support;

import model.Game;

import java.util.Properties;

public class AdditionalRewardCode {
    public Game randomAdditionalReward(Properties properties, Game game) {
        support.AdditionalReward additionalReward;

        if(game.isUsedSecondChance()){
            System.out.println(properties.getProperty("TEXT_WARNING_USEDSECCONDCHANCE"));
            System.out.println(support.AdditionalReward.SMALL.getDescription() + " / " +
                    support.AdditionalReward.MEDIUM.getDescription() + " / " +
                    support.AdditionalReward.LARGE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = support.AdditionalReward.randomRewardWithoutChance();
            System.out.println(additionalReward.getDescription());
            game.setReward(game.getReward() + additionalReward.getReward());
            return game;
        }else {
            System.out.println(properties.getProperty("TEXT_GAME_ADDITIONALREWARDCHANCE"));
            System.out.println(support.AdditionalReward.SMALL.getDescription() + " / " +
                    support.AdditionalReward.MEDIUM.getDescription() + " / " +
                    support.AdditionalReward.LARGE.getDescription() + " / " +
                    support.AdditionalReward.CHANCE.getDescription());
            System.out.println(properties.getProperty("TEXT_GAME_YOUGOT"));
            additionalReward = support.AdditionalReward.randomReward();
            System.out.println(additionalReward.getDescription());
            if ((additionalReward == support.AdditionalReward.SMALL) ||
                    (additionalReward == support.AdditionalReward.MEDIUM) ||
                    (additionalReward == support.AdditionalReward.LARGE)) {
                game.setReward(game.getReward() + additionalReward.getReward());
                return game;
            } else {
                game.setAdditionalChanceOrReward(1);
                return game;
            }
        }
    }

    public int randomAdditionalRewardSimulation(Game game) {
        AdditionalReward additionalReward;

        if (game.isUsedSecondChance()) {//bez chance
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
