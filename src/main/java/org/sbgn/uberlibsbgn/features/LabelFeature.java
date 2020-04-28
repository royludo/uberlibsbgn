package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.bindings.Label;

public interface LabelFeature extends HasPropertyChangeListener {

    void setLabel(String label);

    String getLabel();

    boolean hasLabel();

    boolean labelHasBbox();

    Rectangle2D getLabelBbox();

    void setLabelBbox(Rectangle2D rect);

    void setLabelBboxPosition(double x, double y);

    void setLabelBboxPositionRelativeToGlyph(BboxFeature glyph, double relativeX, double relativeY);

    void setLabelBboxPositionRelativeToParent(double relativeX, double relativeY);

    void parseLibSbgnLabel(Label sbgnLabel);
}
