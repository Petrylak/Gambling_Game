package model;

import lombok.Getter;
import lombok.Setter;
import support.AdditionalRewardCode;
import support.NewGameBuilder;

import java.util.List;
import java.util.Properties;

@Getter
@Setter
public abstract class Box {
    private String name;
    private boolean isChosen;

    public Box(String name) {
        this.name = name;
    }


    abstract public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalReward);

    abstract public void actionSimulation(Game game, NewGameBuilder newGameBuilder, List<Long> balance);
}