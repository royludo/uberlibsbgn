package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface LabelFeature extends HasPropertyChangeListener {

    AbstractUGlyph setLabel(String label);

    String getLabel();

    boolean hasLabel();

    boolean labelHasBbox();

    Rectangle2D getLabelBbox();

    AbstractUGlyph setLabelBbox(Rectangle2D rect);
}
