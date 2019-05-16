package model;

import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

public class ChanceBox extends Box {

    private boolean chance;

    public ChanceBox(String name, boolean chance) {
        super(name);
        this.chance = chance;

    }


    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode){
        System.out.println();
        System.out.println(properties.getProperty("TEXT_CONGRATULATIONS_BOXCHANCE"));
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }

    @Override
    public void actionSimulation(Game game, NewGameBuilder newGameBuilder, BigInteger balance){
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }


}
