package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LabelFeatureImpl implements LabelFeature {

    private AbstractUGlyph uGlyph;

    private BboxFeature bboxFeature;

    private final PropertyChangeSupport pcs;

    private String label = "";

    private EventType eventType; // to separate label of EPNs from labels of UnitOfInfo or other stuff

    final Logger logger = LoggerFactory.getLogger(LabelFeatureImpl.class);

    public LabelFeatureImpl(AbstractUGlyph uGlyph, EventType eventType) {
        logger.trace("Create LabelFeature");
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
        this.eventType = eventType;
        this.bboxFeature = new BboxFeatureImpl(this.uGlyph, EventType.LABELBBOX);
    }

    // TODO try to find a better way to build this than constructor + then another mandatory function
    public void addlistener() {
        this.bboxFeature.registerBboxToPropertySender(this.uGlyph);
    }

    @Override
    public AbstractUGlyph setLabel(String newLabel) {
        logger.trace("Set label to {}", newLabel);
        String oldLabel = this.getLabel();
        this.label = newLabel;

        this.pcs.firePropertyChange(eventType.getEventKey(), oldLabel, newLabel);
        return this.uGlyph;
    }

    @Override
    @Nonnull
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean hasLabel() {
        return !label.isEmpty();
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
    public boolean labelHasBbox() {
        return this.bboxFeature != null && this.bboxFeature.isBboxDefined();
    }

    @Override
    public Rectangle2D getLabelBbox() {
        return this.bboxFeature.getBbox();
    }

    @Override
    public AbstractUGlyph setLabelBbox(Rectangle2D rect) {
        // event will be thrown by the bboxFeature
        //this.bboxFeature = new BboxFeatureImpl(this.uGlyph, EventType.LABELBBOX);
        this.bboxFeature.setBbox(rect);
        return this.uGlyph;
    }
}
