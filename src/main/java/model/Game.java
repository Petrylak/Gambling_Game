package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Game {
    private int reward;
    private int chances;
    private int additionalChanceOrReward;
    private int userNumber;
    private int simulationNumber;
    private boolean usedSecondChance;
    private boolean endRound;
    private Box chosenBox;
    private List<Box> createdBoxes;
    private List<Box> boxesNotInUse;

    public Game() {

    }

    public Game(int reward, int chances, int additionalChanceOrReward, Box chosenBox,
                boolean endRound, boolean usedSecondChance, List<Box> createdBoxes) {

        this.reward = reward;
        this.chances = chances;
        this.additionalChanceOrReward = additionalChanceOrReward;
        this.chosenBox = chosenBox;
        this.usedSecondChance = usedSecondChance;
        this.endRound = endRound;
        this.createdBoxes = createdBoxes;
    }

}