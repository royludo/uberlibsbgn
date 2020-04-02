package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.awt.geom.Rectangle2D;

public interface LabelFeature extends HasPropertyChangeListener {

    AbstractUGlyph setLabel(String label);

    String getLabel();

    boolean hasLabel();

    boolean labelHasBbox();

    Rectangle2D getLabelBbox();

    AbstractUGlyph setLabelBbox(Rectangle2D rect);
}
