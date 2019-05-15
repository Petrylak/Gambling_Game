package model;

import support.AdditionalRewardCode;

import java.util.List;
import java.util.Properties;

public class RewardBox extends Box {

    public RewardBox(String name, int reward) {
        super(name, reward);
    }
    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
       game.setReward(game.getChosenBox().getReward() + game.getReward());
       game.getChosenBox().setChosen(true);
       System.out.println();
       System.out.println(properties.getProperty("TEXT_GAME_BALANCE")
               + game.getReward()
               + properties.getProperty("CURRENCY"));
        }

    @Override
    public void actionSimulationGame(Game game, List<Integer> rewardsList) {
        game.setReward(game.getChosenBox().getReward() + game.getReward());
        game.getChosenBox().setChosen(true);
    }
}
