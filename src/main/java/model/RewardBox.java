package model;

import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.util.List;
import java.util.Properties;

public class RewardBox extends Box {

    public RewardBox(String name, int reward) {
        super(name, reward);
    }

    @Override
    public void action(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
        game.setReward(game.getChosenBox().getReward() + game.getReward());
        game.getChosenBox().setChosen(true);
        System.out.println();
        System.out.println(properties.getProperty("TEXT_GAME_BALANCE")
                + game.getReward()
                + properties.getProperty("CURRENCY"));
    }

    public void action2(Game userGame, NewGameBuilder newGameBuilder, List<Integer> listOfRewards) {

        userGame.setReward(userGame.getReward() + userGame.getChosenBox().getReward());
    }
}
