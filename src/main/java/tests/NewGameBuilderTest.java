package tests;

import model.ChanceBox;
import model.GameOverBox;
import org.junit.Before;
import org.junit.Test;
import model.Box;
import support.IOProperties;
import support.NewGameBuilder;

import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class NewGameBuilderTest {

    private NewGameBuilder gameBuilder;
    private List<Box> boxes;
    private IOProperties ioProperties = new IOProperties();
    private Properties properties = ioProperties.getPropertiesFromFile();



    @Before
    public void prepare(){
        gameBuilder = new NewGameBuilder();
        boxes= gameBuilder.createBoxes(properties);
    }

    @Test
    public void shouldCreateTwelveBoxes(){
        System.out.println(boxes.size());
        assertTrue(boxes.size()==12);
    }

    @Test
    public void shouldContainThreeGameOverBoxes(){
        assertThat(boxes.stream().filter(box -> box instanceof GameOverBox).count(),is(3L));
    }

    @Test
    public void shouldSetAllBoxToNotChosen(){
        gameBuilder.resetChosenBoxes(boxes);

        assertFalse(boxes.stream().filter(Box::isChosen).count()>0);
    }
}
