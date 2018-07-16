package FindThePair.service;

import FindThePair.CreateBoard.ICreadBoard;
import FindThePair.CreateBoard.ICreadBoard1Level;
import FindThePair.CreateBoard.ICreadBoard2Level;
import FindThePair.CreateGameButtons.Create1LevelButtons;
import FindThePair.CreateGameButtons.Create2LevelButtons;
import FindThePair.CreateGameButtons.ICreateAllButtons;
import FindThePair.FindThePairs;
import FindThePair.enums.EnumIconOfButtons1Level;
import FindThePair.enums.EnumIconOfButtons2Level;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Window;
import javafx.application.Application;
import javafx.stage.*;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.*;
import java.util.List;

/**
 * Created by Nesrin Ulgay on 4/14/2018.
 */
public class GameService extends Application {
    int sayac = 0;
    int count = 0;
    private Long oyunSeviyesi;

    FontAwesome firstFont;
    FontAwesome secondFont;

    List<FontAwesome>  controlList ;
    List<Button> buttonListDiff;

    public List<HorizontalLayout> createBoard(Long horizontalLayoutSayisi, ICreadBoard iPutButtonsOnBoard, Long oyunSeviyesi) {
        List<HorizontalLayout> horizontalLayouts = null;
        if (oyunSeviyesi == null || oyunSeviyesi == 1L) {
            iPutButtonsOnBoard = new ICreadBoard1Level ();

        } else {
            iPutButtonsOnBoard = new ICreadBoard2Level ();
        }

        horizontalLayouts = iPutButtonsOnBoard.putButtonsOnBoard ();

        return horizontalLayouts;
    }

    public void putButtonsOnBoard(List<Button> buttonList, List<HorizontalLayout> horizontalLayoutList, VerticalLayout mainLayout) {
        int size = 0;
        int horSize = horizontalLayoutList.size ();

        for (HorizontalLayout components : horizontalLayoutList) {
            mainLayout.addComponent (components);
        }

        for (int sayac = 0; sayac < horizontalLayoutList.size (); sayac++) {
            HorizontalLayout horizontalLayout = (HorizontalLayout) mainLayout.getComponent (sayac);

            for (int a = size; a <= horSize - 1; a++) {
                horizontalLayout.addComponent (buttonList.get (a));
            }
            size = horSize;
            horSize = horSize + horizontalLayoutList.size ();

        }

    }

    public List<Button> createButtonKonumAndFont(List<Button> buttonList, Map<Map<Long, Long>, FontAwesome> konumIcon, Long oyunSeviyesi) {
        Long xkonum = 1L;
        Long ykonum = 1L;
        controlList = new ArrayList<> ();
        for (Button button : buttonList) {
            sayac = 0;
            Map<Long, Long> konum = new HashMap<> ();
            konum.put (xkonum, ykonum);
            konumIcon = new HashMap<> ();
            FontAwesome fontAwesome = giveRandomIcon (oyunSeviyesi);
            konumIcon.put (konum, fontAwesome);
            button.setData (konumIcon);
            xkonum++;
            ykonum++;

        }
        return buttonList;
    }

    private FontAwesome giveRandomIcon(Long oyunSeviyesi) {

        List<FontAwesome> fontAwesomeList = null;
        if (oyunSeviyesi == 1L) {
            fontAwesomeList = getFontAwesomeList1Level ();

        } else {
            fontAwesomeList = getFontAwesomeList2Level ();

        }
        FontAwesome fontAwesome = createRandomFont (fontAwesomeList);

        controlList.add (fontAwesome);
        return fontAwesome;

    }

    private FontAwesome createRandomFont(List<FontAwesome> fontAwesomeList) {

        FontAwesome fontAwesome = getFontAwesome (fontAwesomeList);
        for (FontAwesome awesome : controlList) {
            if (fontAwesome.equals (awesome)) {
                sayac++;
            }
        }
        if (sayac == 2) {
            sayac = 0;
            return createRandomFont (fontAwesomeList);

        } else {
            return fontAwesome;
        }

    }

    private FontAwesome getFontAwesome(List<FontAwesome> fontAwesomeList1Level) {
        int i = getRandomListNumber (fontAwesomeList1Level);
        return fontAwesomeList1Level.get (i);
    }

    private int getRandomListNumber(List<FontAwesome> fontAwesomeList1Level) {
        Random random = new Random ();
        return random.nextInt (fontAwesomeList1Level.size ());
    }

    public void startGame(List<Button> buttonList) {

        for (Button button : buttonList) {
            button.addClickListener (new Button.ClickListener () {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    count++;
                    try {
                        game (button);
                        if (count == 2) {
                            buttonListDiff = getButtonListDiff (buttonList);
                        }
                        if (count == 3) {
                            compareButtons (buttonListDiff);
                        }
                        controlOfWin (buttonList);

                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
            });
        }
    }

    class runThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep (1000);

            } catch (Exception e) {
                e.printStackTrace ();
            }


        }
    }

    private void controlOfWin(List<Button> buttonList) {
        boolean allMatch = buttonList.stream ()
                .allMatch (button -> button.getCaption () != null && button.getCaption ().equals ("X"));
        if (allMatch) {
            Notification.show ("You Win", Notification.TYPE_HUMANIZED_MESSAGE);

//            FindThePairs findThePair = new FindThePairs ();
//            findThePair.setOyunSeviyesi (2L);
//            findThePair.inits ();
//
        }
    }

    public void compareButtons(List<Button> buttonList) throws InterruptedException {

        if (buttonList.get (0) != null && buttonList.get (1) != null && !buttonList.get (0).getIcon ().equals (buttonList.get (1).getIcon ())) {
            buttonList.get (0).setIcon (FontAwesome.CIRCLE_O);
            buttonList.get (1).setIcon (FontAwesome.CIRCLE_O);

        } else {
            buttonList.get (0).setEnabled (false);
            buttonList.get (1).setEnabled (false);
            buttonList.get (0).setCaption ("X");
            buttonList.get (1).setCaption ("X");
        }

        buttonListDiff = new ArrayList<> ();
        count = 1;

    }

    private void game(Button button) throws InterruptedException {

        Map<Map<Long, Long>, FontAwesome> buttonData = (Map<Map<Long, Long>, FontAwesome>) button.getData ();
        Set<Map<Long, Long>> maps = buttonData.keySet ();
        for (Map<Long, Long> map : maps) {
            FontAwesome anafont = buttonData.get (map);

            if (count == 1) {
                button.setIcon (anafont);
                firstFont = anafont;
            } else {
                secondFont = anafont;
                button.setIcon (anafont);
            }
        }
    }

    public List<Button> createButtons(List<Button> buttonList, ICreateAllButtons iCreateAllButtons) {

        if(oyunSeviyesi == 1L){
            iCreateAllButtons = new Create1LevelButtons ();
        }else{
            iCreateAllButtons = new Create2LevelButtons ();
        }

        buttonList = iCreateAllButtons.createAllButtons ();
        return buttonList;
    }

    public List<FontAwesome> getFontAwesomeList1Level() {
        List<FontAwesome> fontAwesomeList = new ArrayList<> ();

        EnumIconOfButtons1Level[] values = EnumIconOfButtons1Level.values ();
        for (EnumIconOfButtons1Level value : values) {
            fontAwesomeList.add (value.getFontAwesome ());
        }

        return fontAwesomeList;
    }


    public List<FontAwesome> getFontAwesomeList2Level() {
        List<FontAwesome> fontAwesomeList = new ArrayList<> ();

        EnumIconOfButtons2Level[] values = EnumIconOfButtons2Level.values ();
        for (EnumIconOfButtons2Level value : values) {
            fontAwesomeList.add (value.getFontAwesome ());
        }

        return fontAwesomeList;
    }

    public List<Button> getButtonListDiff(List<Button> buttonList) {
        List<Button> buttons = new ArrayList<> ();

        for (Button button : buttonList) {
            if (!button.getIcon ().equals (FontAwesome.CIRCLE_O) && button.getCaption () == null) {
                buttons.add (button);
            }

        }
        return buttons;
    }

    public void timer(Button button) {
        new Timer ().schedule (new TimerTask () {
            @Override
            public void run() {
                button.setIcon (FontAwesome.CIRCLE_O);
            }
        }, 0, 20000);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Window components = new Window ();
    }

    public Long getOyunSeviyesi() {
        return oyunSeviyesi;
    }

    public void setOyunSeviyesi(Long oyunSeviyesi) {
        this.oyunSeviyesi = oyunSeviyesi;
    }
}
