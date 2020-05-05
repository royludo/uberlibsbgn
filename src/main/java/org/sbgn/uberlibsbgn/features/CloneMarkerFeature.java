package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;

public interface CloneMarkerFeature extends HasPropertyChangeListener {

    void setCloneMarker(boolean isCloneMarker);

    boolean hasCloneMarker();

    LabelFeature getCloneLabel();

    boolean hasCloneLabel();

    void parseLibSBGNCloneMarker(Glyph.Clone cloneElement);
}
