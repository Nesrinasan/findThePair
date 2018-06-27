package FindThePair.enums;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

/**
 * Created by Universal on 4/14/2018.
 */
public enum EnumIconOfButtons1Level implements Resource {

    AMBULANCE(FontAwesome.AMBULANCE),
    ANGELLIST(FontAwesome.ANGELLIST),
    ARROW_CIRCLE_O_RIGHT(FontAwesome.ARROW_CIRCLE_O_RIGHT),
    BELL(FontAwesome.BELL),
    BUG(FontAwesome.BUG),
    BUILDING(FontAwesome.BUILDING),
    BUILDING_O(FontAwesome.BUILDING_O),
    CONTAO(FontAwesome.CONTAO);
    private final FontAwesome fontAwesome;

    EnumIconOfButtons1Level(FontAwesome fontAwesome1) {
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
