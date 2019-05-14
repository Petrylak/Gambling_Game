package model;

import lombok.Getter;
import lombok.Setter;
import support.AdditionalRewardCode;

import java.util.Properties;

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

    public Game action(Game game, Properties properties, AdditionalRewardCode additionalRewardCode) {return game;}
}
