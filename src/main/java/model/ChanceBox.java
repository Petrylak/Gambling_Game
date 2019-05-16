package model;

import service.NewGameBuilder;
import support.AdditionalRewardCode;

import java.util.List;
import java.util.Properties;

public class ChanceBox extends Box {


    public ChanceBox(String name, boolean chance) {
        super(name, chance);
    }


    @Override
    public void action(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {
        System.out.println();
        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
        game.setChances(1);
        game.getChosenBox().setChosen(true);
    }

    @Override
    public void action2(Game userGame, NewGameBuilder newGameBuilder, List<Integer> listOfRewards) {
        userGame.setChances(1);
    }
}