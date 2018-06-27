package FindThePair.CreateGameButtons;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Universal on 4/7/2018.
 */
public class Create2LevelButtons implements ICreateAllButtons {


    @Override
    public List<Button> createAllButtons() {
        List<Button> buttonList = new ArrayList<> ();
        for (int i = 1; i <= 36L; i++) {
            Button button = new Button ();
            button.setWidth ("70px");
            button.setHeight ("70px");
            button.setIcon (FontAwesome.CIRCLE_O);
            buttonList.add (button);
        }
        return buttonList;
    }
}
