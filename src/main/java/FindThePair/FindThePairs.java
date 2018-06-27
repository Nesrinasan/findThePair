package FindThePair;

import javax.servlet.annotation.WebServlet;

import FindThePair.CreateBoard.ICreadBoard1Level;
import FindThePair.CreateBoard.ICreadBoard2Level;
import FindThePair.CreateGameButtons.Create1LevelButtons;
import FindThePair.CreateGameButtons.Create2LevelButtons;
import FindThePair.CreateGameButtons.ICreateAllButtons;
import FindThePair.CreateBoard.ICreadBoard;
import FindThePair.service.GameService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("Pair")
public class FindThePairs extends UI implements View {

    private VerticalLayout mainLayout = new VerticalLayout ();
    private VerticalLayout layout1Level = new VerticalLayout ();

    private ICreateAllButtons iCreateAllButtons = null;
    private ICreadBoard iPutButtonsOnBoard = null;

    private Long oyunSeviyesi = null;

    private PopupView popupView;

    GameService gameService = new GameService ();

    public FindThePairs() {
        setContent (mainLayout);
        inits ();

    }

    public void inits() {

        List<Button> buttonList = new ArrayList<> ();
        Map<Map<Long, Long>, FontAwesome> konumIcon = new HashMap<> ();

        List<HorizontalLayout> horizontalLayoutList;

        if (oyunSeviyesi == null) {
            this.oyunSeviyesi = 1L;

            Button btnDevam = new Button ("Devam Eder misin tatlım?");
            PopupView components = setWindow (btnDevam);
            mainLayout.addComponent (components);

            iCreateAllButtons = new Create1LevelButtons ();
            buttonList = iCreateAllButtons.createAllButtons ();
            buttonList = gameService.createButtonKonumAndFont (buttonList, konumIcon, oyunSeviyesi);

            iPutButtonsOnBoard = new ICreadBoard1Level ();
            horizontalLayoutList = iPutButtonsOnBoard.putButtonsOnBoard ();

            gameService.putButtonsOnBoard (buttonList, horizontalLayoutList, layout1Level);
            gameService.startGame (buttonList);

            mainLayout.addComponent (layout1Level);

        } else if (oyunSeviyesi == 2L) {
            VerticalLayout layout2Level = new VerticalLayout ();
            layout1Level.setVisible (false);
            layout2Level.setVisible (true);

            iCreateAllButtons = new Create2LevelButtons ();
            buttonList = iCreateAllButtons.createAllButtons ();

            iPutButtonsOnBoard = new ICreadBoard2Level ();
            horizontalLayoutList = iPutButtonsOnBoard.putButtonsOnBoard ();

            buttonList = gameService.createButtonKonumAndFont (buttonList, konumIcon, oyunSeviyesi);
            gameService.putButtonsOnBoard (buttonList, horizontalLayoutList, layout2Level);
            gameService.startGame (buttonList);
            mainLayout.addComponent (layout2Level);

        }
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public PopupView setWindow(Button btnDevam) {
        popupView = new PopupView ("Devam", btnDevam);
        btnDevam.addClickListener (new Button.ClickListener () {

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                oyunSeviyesi = 2L;
                inits ();
//                ConfirmDialog confirmDialog = new ConfirmDialog();
//                ConfirmDialog.show (UI.getCurrent ().getUI (), "d", new ConfirmDialog.Listener () {
//                    @Override
//                    public void onClose(ConfirmDialog confirmDialog) {
//                        if (confirmDialog.isConfirmed ()) {
//                            oyunSeviyesi = 2L;
//                            inits ();
//                        }
//                    }
//                });

//                UI.getCurrent ().addWindow (confirmDialog);
            }
        });
        return popupView;
    }

    public Long getOyunSeviyesi() {
        return oyunSeviyesi;
    }

    public void setOyunSeviyesi(Long oyunSeviyesi) {
        this.oyunSeviyesi = oyunSeviyesi;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    }

    @WebServlet(urlPatterns = "/*", name = "FindThePairServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = FindThePairs.class, productionMode = false)
    public static class FindThePairServlet extends VaadinServlet {
    }
}
