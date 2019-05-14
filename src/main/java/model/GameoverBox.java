package model;

public class GameoverBox extends Box {

    public GameoverBox(String name, boolean gameOver) {
        super(name, gameOver);
    }

    @Override
    public Game action(Game game) {
        if (game.getChances() == 1) {

        } else {
            System.out.println();
            game.setEndRound(true);
        }return game;
    }
}
