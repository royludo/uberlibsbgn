package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PortFeatureImpl implements PortFeature {

    private AbstractUGlyph uGlyph;

    private final PropertyChangeSupport pcs;

    private Point2D lPort, rPort;
    private Orientation orientation;
    static float DEFAULT_DISTANCE = 5f;
    private float portDistance;

    final Logger logger = LoggerFactory.getLogger(PortFeatureImpl.class);

    public PortFeatureImpl(AbstractUGlyph uGlyph) {
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
        this.portDistance = DEFAULT_DISTANCE;
        this.orientation = Orientation.HORIZONTAL;
        Point2D[] ports = this.portsPosition();
        this.lPort = ports[0];
        this.rPort = ports[1];
    }

    private Point2D[] portsPosition() {
        Rectangle2D bbox = this.uGlyph.getBbox();
        Point2D lport, rport;
        if(this.orientation == Orientation.HORIZONTAL) {
            double centerY = bbox.getMinY() + bbox.getHeight() / 2;
            double lX = bbox.getMinX() - this.portDistance;
            double rX = bbox.getMaxX() + this.portDistance;
            lport = new Point2D(lX, centerY);
            rport = new Point2D(rX, centerY);
        }
        else {
            double centerX = bbox.getMinX() + bbox.getWidth() / 2;
            double topY = bbox.getMinY() - this.portDistance;
            double bottomY = bbox.getMaxY() + this.portDistance;
            lport = new Point2D(centerX , topY);
            rport = new Point2D(centerX , bottomY);
        }

        return new Point2D[]{lport, rport};
    }

    private void updatePortsAndFireEvents() {
        Point2D oldLPort = this.lPort;
        Point2D oldRPort = this.rPort;

        Point2D[] ports = this.portsPosition();
        this.lPort = ports[0];
        this.rPort = ports[1];

        this.pcs.firePropertyChange(EventType.PORTPOSITION.getEventKey(), oldLPort, this.lPort);
        this.pcs.firePropertyChange(EventType.PORTPOSITION.getEventKey(), oldRPort, this.rPort);
    }

    @Override
    public Point2D[] getPorts() {
        return new Point2D[]{this.lPort, this.rPort};
    }

    @Override
    public Point2D getLHSPort() {
        return this.lPort;
    }

    @Override
    public Point2D getRHSPort() {
        return this.rPort;
    }

    @Override
    public Orientation getPortOrientation() {
        return this.orientation;
    }

    @Override
    public void setPortOrientation(Orientation orientation) {
        if(orientation == this.orientation) return;

        Orientation oldOrientation = this.orientation;
        this.orientation = orientation;
        this.pcs.firePropertyChange(EventType.PORTORIENTATION.getEventKey(), oldOrientation, this.orientation);

        this.updatePortsAndFireEvents();

    }

    @Override
    public void setPortDistance(float portDistance) {
        if(portDistance == this.portDistance) return;

        float oldPortDistance = this.portDistance;
        this.portDistance = portDistance;
        this.pcs.firePropertyChange(EventType.PORTDISTANCE.getEventKey(), oldPortDistance, this.portDistance);

        this.updatePortsAndFireEvents();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    // listen to parent bbox change to update port position
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals(EventType.BBOX.getEventKey())) {
            Point2D[] ports = this.portsPosition();
            this.lPort = ports[0];
            this.rPort = ports[1];
        }
    }
}
