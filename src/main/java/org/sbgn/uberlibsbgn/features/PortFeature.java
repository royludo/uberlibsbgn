package org.sbgn.uberlibsbgn.features;


import javafx.geometry.Orientation;
import javafx.geometry.Point2D;

import java.beans.PropertyChangeListener;

public interface PortFeature extends HasPropertyChangeListener, PropertyChangeListener {

    Point2D[] getPorts();
    Point2D getLHSPort();
    Point2D getRHSPort();
    Orientation getPortOrientation();
    void setPortOrientation(Orientation orientation);
    void setPortDistance(float portDistance);

}
