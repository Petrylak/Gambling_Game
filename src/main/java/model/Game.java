package model;

import java.util.List;

public class Game {
    private int reward;
    private int chances;
    private int additionalChanceOrReward;
    private boolean usedSecondChance;
    private boolean endRound;
    private Box chosenBox;
    private List<Box> createdBoxes;

    public Game(){

    }

    public Game(int reward,int chances,int additionalChanceOrReward,Box chosenBox,
                boolean endRound, boolean usedSecondChance, List<Box> createdBoxes){

        this.reward = reward;
        this.chances = chances;
        this.additionalChanceOrReward = additionalChanceOrReward;
        this.chosenBox = chosenBox;
        this.usedSecondChance = usedSecondChance;
        this.endRound = endRound;
        this.createdBoxes = createdBoxes;
    }

    public boolean isEndRound() {
        return endRound;
    }

    public void setEndRound(boolean endRound) {
        this.endRound = endRound;
    }


    public void setEndRoundRound(boolean endRound) {
        this.endRound = endRound;
    }

    public List<Box> getCreatedBoxes() {
        return createdBoxes;
    }

    public void setCreatedBoxes(List<Box> createdBoxes) {
        this.createdBoxes = createdBoxes;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public int getAdditionalChanceOrReward() {
        return additionalChanceOrReward;
    }

    public void setAdditionalChanceOrReward(int additionalChanceOrReward) {
        this.additionalChanceOrReward = additionalChanceOrReward;
    }

    public Box getChosenBox() {
        return chosenBox;
    }

    public void setChosenBox(Box chosenBox) {
        this.chosenBox = chosenBox;
    }

    public boolean isUsedSecondChance() {
        return usedSecondChance;
    }

    public void setUsedSecondChance(boolean usedSecondChance) {
        usedSecondChance = usedSecondChance;
    }
}
