package model;

import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

public class ChanceBox extends Box {

    public ChanceBox(String name) {
        super(name);

    }


    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode){
        System.out.println();
        System.out.println(properties.getProperty("TEXT_CONGRATULATIONS_BOXCHANCE"));
        setProperties(game);
    }

    @Override
    public void actionSimulation(Game game, NewGameBuilder newGameBuilder){
        setProperties(game);
    }

    private void setProperties(Game game){
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }


}
