package games.service;

import model.Box;
import games.GameConfiguration;
import model.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewGameBuilder  {


    public List<Box> createBoxes() {


        List<Box> yourCreatedBoxes = new ArrayList<Box>();


        for (int i = 0; i < GameConfiguration.NUMBER_OF_100EURO_BOXES; i++) {
            yourCreatedBoxes.add(new Box("100", 100));
        }
        for (int i = 0; i < GameConfiguration.NUMBER_OF_20EURO_BOXES; i++) {
            yourCreatedBoxes.add(new Box("20", 20));
        }
        for (int i = 0; i < GameConfiguration.NUMBER_OF_5EURO_BOXES; i++) {
            yourCreatedBoxes.add(new Box("5", 5));
        }
        for (int i = 0; i < GameConfiguration.NUMBER_OF_GAME_OVER_BOXES; i++) {
            yourCreatedBoxes.add(new Box("Game Over", true));
        }
        for (int i = 0; i < GameConfiguration.NUMBER_OF_CHANCE_BOXES; i++) {
            yourCreatedBoxes.add(new Box("Chance", false));
        }

        Collections.shuffle(yourCreatedBoxes);

        return (yourCreatedBoxes);
    }

    public void resetChosenBoxes(List<Box> boxes) {
        for (int i = 0; i < boxes.size(); i++) {
            boxes.get(i).setChosen(false);
        }
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
