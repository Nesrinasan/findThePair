package FindThePair.CreateGameButtons;

import FindThePair.enums.EnumIconOfButtons1Level;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesrin ÜLGAY on 4/7/2018.
 */
public class Create1LevelButtons implements ICreateAllButtons {

    @Override
    public List<Button> createAllButtons() {
        List<Button> buttonList = new ArrayList<> ();
        for (int i = 1; i <= 16L; i++) {
            Button button = new Button ();
            button.setWidth ("70px");
            button.setHeight ("70px");
            button.setIcon (FontAwesome.CIRCLE_O);
            buttonList.add (button);
        }
        return buttonList;
    }
}
