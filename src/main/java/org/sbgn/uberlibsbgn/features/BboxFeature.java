package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import java.awt.geom.Rectangle2D;

public interface BboxFeature<T extends AbstractUGlyph<T>> extends HasPropertyChangeListener {

    @Nonnull
    Rectangle2D getBbox();

    AbstractUGlyph<T> setBbox(Rectangle2D rect);

    boolean isBboxDefined();
}
