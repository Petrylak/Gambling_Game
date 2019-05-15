package model;

import support.AdditionalRewardCode;

import java.util.List;
import java.util.Properties;

public class ChanceBox extends Box {


    public ChanceBox(String name, boolean chance) {
        super(name, chance);
    }


    @Override
    public void actionUserGame(Game game, Properties properties, AdditionalRewardCode additionalRewardCode){
        System.out.println();
        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }

    @Override
    public void actionSimulationGame(Game game, List<Integer> rewardsList){
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }


}
