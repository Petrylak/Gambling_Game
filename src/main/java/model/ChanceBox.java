package model;

public class ChanceBox extends Box {


    public ChanceBox(String name, boolean chance) {
        super(name, chance);
    }


    @Override
    public void action(Game game){
        System.out.println();
        System.out.println("Gratulacje ! Trafiłes na pudełko z szansą, kolejne pudełko z Game Over Nie bedzie liczone");
        game.setChances(1);
    }
}
