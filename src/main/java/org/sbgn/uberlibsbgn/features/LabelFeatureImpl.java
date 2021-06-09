package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.bindings.Bbox;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*  this class effectively implements BboxFeature, but if it was really implementing it, we would
*   have names clashing at the glyph class level. (would getBbox() refer to the glyph bbox or the label
* bbox? )
*/
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
    public void setLabel(String newLabel) {
        logger.trace("Set label to {}", newLabel);
        String oldLabel = this.getLabel();
        this.label = newLabel;

        this.pcs.firePropertyChange(eventType.getEventKey(), oldLabel, newLabel);
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
    public void setLabelBbox(Rectangle2D rect) {
        // event will be thrown by the bboxFeature
        //this.bboxFeature = new BboxFeatureImpl(this.uGlyph, EventType.LABELBBOX);
        this.bboxFeature.setBbox(rect);
    }

    public void setLabelBboxPosition(double x, double y) {
        this.bboxFeature.setPosition(x, y);
    }

    public void setLabelBboxPositionRelativeToGlyph(BboxFeature glyph, double relativeX, double relativeY) {
        this.bboxFeature.setPositionRelativeToGlyph(glyph, relativeX, relativeY);
    }

    public void setLabelBboxPositionRelativeToParent(double relativeX, double relativeY) {
        this.bboxFeature.setPositionRelativeToParent(relativeX, relativeY);
    }

    @Override
    public void parseLibSbgnLabel(Label sbgnLabel) {
        String sbgnLabelString = sbgnLabel.getText();
        this.setLabel(sbgnLabelString);

        if(sbgnLabel.getBbox() != null) {
            Bbox sbgnLabelBbox = sbgnLabel.getBbox();
            Rectangle2D labelBbox = Utils.libsbgnBboxToRectangle2D(sbgnLabelBbox);
            this.setLabelBbox(labelBbox);
        }
    }
}
