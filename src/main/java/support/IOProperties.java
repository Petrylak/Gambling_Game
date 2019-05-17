package support;

import main.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IOProperties {

    public Properties getPropertiesFromFile() {
        Properties prop = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("gameConfiguration.properties")) {


            if (input == null) {
                System.out.println("Sorry, unable to find gameConfiguration.properties");
            }
            prop.load(input);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

}
