package FindThePair.CreateBoard;

import com.vaadin.ui.HorizontalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesrin ÜLGAY on 4/7/2018.
 */
public class ICreadBoard2Level implements ICreadBoard {

    @Override
    public List<HorizontalLayout> putButtonsOnBoard() {

        List<HorizontalLayout> horizontalLayoutList = new ArrayList<> ();
        for (int i = 1; i <= 6L; i++) {
            HorizontalLayout horizontalLayout = new HorizontalLayout ();
            horizontalLayoutList.add (horizontalLayout);

        }
        return horizontalLayoutList;
    }
}
