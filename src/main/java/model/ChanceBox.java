package model;

import support.AdditionalRewardCode;

import java.util.Properties;

public class ChanceBox extends Box {


    public ChanceBox(String name, boolean chance) {
        super(name, chance);
    }


    @Override
    public Game action(Game game, Properties properties, AdditionalRewardCode additionalRewardCode){
        System.out.println();
        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
        game.setChances(1);
        game.getChosenBox().setChosen(true);
        return game;
    }
}
