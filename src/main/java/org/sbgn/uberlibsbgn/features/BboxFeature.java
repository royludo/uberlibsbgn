package org.sbgn.uberlibsbgn.features;

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
}