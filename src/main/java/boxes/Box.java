package boxes;

public class Box {
    private String name;
    private int reward;
    private boolean gameOver;
    private boolean chosen;


    public Box(String name){
        this.name = name;
    }

    public Box(String name, int reward){
        this.name = name;
        this.reward = reward;

    }
    public Box(String name, boolean gameOver){
        this.name = name;
        this.gameOver = gameOver;

    }

    public Box(String name, boolean chance, boolean gameOver){
        this.name = name;
        this.gameOver = gameOver;

    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean choosed) {
        this.chosen = choosed;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
