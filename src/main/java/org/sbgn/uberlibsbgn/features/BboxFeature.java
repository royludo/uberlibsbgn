package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeListener;

public interface BboxFeature<T extends AbstractUGlyph<T>> extends HasPropertyChangeListener, PropertyChangeListener {

    @Nonnull
    Rectangle2D getBbox();

    AbstractUGlyph<T> setBbox(Rectangle2D rect);

    boolean isBboxDefined();
}
