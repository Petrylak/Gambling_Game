package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Box {
    private String name;
    private int reward;
    private boolean gameOver;
    private boolean chosen;



    public Box(String name) {
        this.name = name;
    }

    public Box(String name, int reward) {
        this.name = name;
        this.reward = reward;

    }

    public Box(String name, boolean gameOver) {
        this.name = name;
        this.gameOver = gameOver;

    }

    public Box(String name, boolean chance, boolean gameOver) {
        this.name = name;
        this.gameOver = gameOver;

    }

     public void action(){}

    public void action(Game game){}

    public Game action(Game game) {}
}
