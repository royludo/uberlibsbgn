package org.sbgn.uberlibsbgn;

import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import org.sbgn.uberlibsbgn.features.CompositeFeature;
import org.sbgn.uberlibsbgn.features.PortFeature;
import org.sbgn.uberlibsbgn.features.PortFeatureImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Process extends EPN implements PortFeature {

    PortFeature portFeature;

    final Logger logger = LoggerFactory.getLogger(Process.class);

    protected Process(CompositeFeature parent, UMap parentMap) {
        super("process", parent, parentMap);
        logger.trace("create process");
        this.portFeature = new PortFeatureImpl(this);
        this.addPropertyChangeListener(this.portFeature);
    }

    @Override
    public Point2D[] getPorts() {
        return portFeature.getPorts();
    }

    @Override
    public Point2D getLHSPort() {
        return portFeature.getLHSPort();
    }

    @Override
    public Point2D getRHSPort() {
        return portFeature.getRHSPort();
    }

    @Override
    public Orientation getPortOrientation() {
        return portFeature.getPortOrientation();
    }

    @Override
    public void setPortOrientation(Orientation orientation) {
        portFeature.setPortOrientation(orientation);
    }

    @Override
    public void setPortDistance(float portDistance) {
        portFeature.setPortDistance(portDistance);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        portFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        portFeature.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        portFeature.propertyChange(propertyChangeEvent);
    }
}
