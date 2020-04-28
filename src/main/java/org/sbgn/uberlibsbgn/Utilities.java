package org.sbgn.uberlibsbgn;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.transform.Translate;
import org.sbgn.bindings.Bbox;
import org.sbgn.uberlibsbgn.features.BboxFeatureImpl;
import org.sbgn.uberlibsbgn.features.LabelFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities {

    final static Logger logger = LoggerFactory.getLogger(Utilities.class);

    public static String glyphString(AbstractUGlyph abstractUGlyph) {
        String idorlabel;
        if(abstractUGlyph.hasLabelFeature()) {
            idorlabel = ((LabelFeature) abstractUGlyph).getLabel();
        }
        else {
            idorlabel = abstractUGlyph.getId();
        }
        return idorlabel;
    }

    public static Translate getTranslateFromRect(Rectangle2D r1, Rectangle2D r2) {
        Point2D oldLocation = new Point2D(r1.getMinX(), r1.getMinY());
        Point2D newLocation = new Point2D(r2.getMinX(), r2.getMinY());
        logger.trace("old location: {} new location: {}", oldLocation, newLocation);
        Point2D result = newLocation.subtract(oldLocation);
        logger.trace("difference {}", result);
        return new Translate(result.getX(), result.getY());
    }

    public static Rectangle2D libsbgnBboxToRectangle2D(Bbox sbgnBbox) {
        float x = sbgnBbox.getX();
        float y = sbgnBbox.getY();
        float w = sbgnBbox.getW();
        float h = sbgnBbox.getH();
        return new Rectangle2D(x, y, w, h);
    }
}
