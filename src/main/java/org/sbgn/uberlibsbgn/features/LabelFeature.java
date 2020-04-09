package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;

public interface LabelFeature extends HasPropertyChangeListener {

    void setLabel(String label);

    String getLabel();

    boolean hasLabel();

    boolean labelHasBbox();

    Rectangle2D getLabelBbox();

    void setLabelBbox(Rectangle2D rect);
}
