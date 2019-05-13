package games.support;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AdditionalReward {
    SMALL("5 $", false, 5),
    MEDIUM("10 $", false, 10),
    LARGE("20 $", false, 20),
    CHANCE("Druga Szansa", true, 0);

    private static final List<AdditionalReward> REWARDS =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = REWARDS.size();
    private static final Random RANDOM = new Random();
    private final String description;
    private boolean chance;
    private int reward;

    AdditionalReward(String description, boolean chance, int reward) {
        this.description = description;
        this.reward = reward;
        this.chance = chance;
    }

    public static AdditionalReward randomReward() {
        return REWARDS.get(RANDOM.nextInt(SIZE));
    }

    public static AdditionalReward randomRewardWithoutChance() {
        return REWARDS.get(RANDOM.nextInt(SIZE - 1));
    }

    public String getDescription() {
        return description;
    }

    public boolean isChance() {
        return chance;
    }

    public int getReward() {
        return reward;
    }
}
