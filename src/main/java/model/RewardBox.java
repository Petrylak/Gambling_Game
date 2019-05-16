package model;

import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

public class RewardBox extends Box {

    private int reward;

    public RewardBox(String name, int reward) {
        super(name);
        this.reward=reward;
    }

    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
        game.setReward(reward + game.getReward());
        setChosen(true);
        System.out.println();
        System.out.println(properties.getProperty("TEXT_GAME_BALANCE")
                + game.getReward()
                + properties.getProperty("CURRENCY"));
    }
    @Override
    public void actionSimulation(Game userGame, NewGameBuilder newGameBuilder) {

        userGame.setReward(userGame.getReward() + reward);
    }
}
