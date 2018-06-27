package FindThePair.enums;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

/**
 * Created by Universal on 4/14/2018.
 */
public enum EnumIconOfButtons2Level implements Resource {

    AMBULANCE(FontAwesome.AMBULANCE),
    ANGELLIST(FontAwesome.ANGELLIST),
    ARROW_CIRCLE_O_RIGHT(FontAwesome.ARROW_CIRCLE_O_RIGHT),
    BELL(FontAwesome.BELL),
    BUG(FontAwesome.BUG),
    BUILDING(FontAwesome.BUILDING),
    BUILDING_O(FontAwesome.BUILDING_O),
    APPLE(FontAwesome.APPLE),
    CHECK(FontAwesome.CHECK),
    CHECK_SQUARE_O(FontAwesome.CHECK_SQUARE_O),
    CODEPEN(FontAwesome.CODEPEN),
    EXPAND(FontAwesome.EXPAND),
    EYE(FontAwesome.EYE),
    FILE_WORD_O(FontAwesome.FILE_WORD_O),
    FLAG(FontAwesome.FLAG),
    HAND_PEACE_O(FontAwesome.HAND_PEACE_O),
    INBOX(FontAwesome.INBOX),
    CONTAO(FontAwesome.CONTAO);

    private final FontAwesome fontAwesome;

    EnumIconOfButtons2Level(FontAwesome fontAwesome1) {
        this.fontAwesome = fontAwesome1;
    }

    public FontAwesome getFontAwesome() {
        return fontAwesome;
    }

    @Override
    public String getMIMEType() {
        return null;
    }
}
