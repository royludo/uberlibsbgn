package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeListener;

public interface BboxFeature extends HasPropertyChangeListener, PropertyChangeListener {

    @Nonnull
    Rectangle2D getBbox();

    void setBbox(Rectangle2D rect);

    boolean isBboxDefined();

    // TODO find another way, this is ugly
    void registerBboxToPropertySender(HasPropertyChangeListener listener);

    default void setPosition(double x, double y) {
        Rectangle2D oldbbox = this.getBbox();
        this.setBbox(new Rectangle2D(x, y, oldbbox.getWidth(), oldbbox.getHeight()));
    }

    default void setPositionRelativeToGlyph(BboxFeature glyph, double relativeX, double relativeY) {
        Rectangle2D otherBbox = glyph.getBbox();
        this.setPosition(otherBbox.getMinX() + relativeX, otherBbox.getMinY() + relativeY);
    }

    void setPositionRelativeToParent(double relativeX, double relativeY);

}
