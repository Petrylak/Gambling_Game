package service;

import model.Box;
import model.ChanceBox;
import model.GameoverBox;
import model.RewardBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class NewGameBuilder  {

    public List<Box> createBoxes2(Properties properties) {


        List<Box> yourCreatedBoxes = new ArrayList<>();


        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_100EURO_BOXES")); i++) {
            yourCreatedBoxes.add(new RewardBox("100", 100));
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_20EURO_BOXES")); i++) {
            yourCreatedBoxes.add(new RewardBox("20", 20));
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_5EURO_BOXES")); i++) {
            yourCreatedBoxes.add(new RewardBox("5", 5));
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_GAME_OVER_BOXES")); i++) {
            yourCreatedBoxes.add(new GameoverBox("Game Over", true));
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("NUMBER_OF_CHANCE_BOXES")); i++) {
            yourCreatedBoxes.add(new ChanceBox("Chance", true));
        }

        Collections.shuffle(yourCreatedBoxes);

        return (yourCreatedBoxes);
    }

    public void resetChosenBoxes(List<Box> boxes) {
        boxes.forEach(box->box.setChosen(false));
    }


    public String toString(List<Box> createdBoxes) {

        int cardNumber = 1;
        String makingBoxesLog = null;
        for (Box i : createdBoxes) {
            makingBoxesLog += ("\nPudełko nr." + (cardNumber++) + " : " + i.getName());
        }
        return("Pomieszane pudełka: " + makingBoxesLog);
    }
}
