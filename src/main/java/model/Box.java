package model;

import lombok.Getter;
import lombok.Setter;
import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.math.BigInteger;
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


    abstract public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode);

    abstract public void actionSimulation(Game game, NewGameBuilder newGameBuilder, BigInteger balance);
}