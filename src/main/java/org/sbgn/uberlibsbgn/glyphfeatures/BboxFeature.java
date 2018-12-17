package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.geom.Rectangle2D;

public interface BboxFeature extends HasPropertyChangeListener {

    @Nonnull
    Rectangle2D getBbox();

    AbstractUGlyph setBbox(Rectangle2D rect);

    boolean isBboxDefined();
}
