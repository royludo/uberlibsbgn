package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.features.*;

import java.beans.PropertyChangeListener;

public class SimpleChemical extends EPN implements MultimerFeature, LabelFeature {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;

    protected SimpleChemical(CompositeFeature parent, UMap parentMap, String id) {
        super("simple chemical", parent, parentMap, id);
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this, EventType.LABEL);
    }

    @Override
    public void setMultimer(boolean isMultimer) {
        this.multimerFeature.setMultimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public void setLabel(String label) {
        this.labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return this.labelFeature.getLabel();
    }

    @Override
    public boolean hasLabel() {
        return labelFeature.hasLabel();
    }

    @Override
    public boolean labelHasBbox() {
        return labelFeature.labelHasBbox();
    }

    @Override
    public Rectangle2D getLabelBbox() {
        return labelFeature.getLabelBbox();
    }

    @Override
    public void setLabelBbox(Rectangle2D rect) {
        labelFeature.setLabelBbox(rect);
    }

    @Override
    public void setLabelBboxPosition(double x, double y) {
        labelFeature.setLabelBboxPosition(x, y);
    }

    @Override
    public void setLabelBboxPositionRelativeToGlyph(BboxFeature glyph, double relativeX, double relativeY) {
        labelFeature.setLabelBboxPositionRelativeToGlyph(glyph, relativeX, relativeY);
    }

    @Override
    public void setLabelBboxPositionRelativeToParent(double relativeX, double relativeY) {
        labelFeature.setLabelBboxPositionRelativeToParent(relativeX, relativeY);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        labelFeature.addPropertyChangeListener(listener);
        multimerFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        labelFeature.removePropertyChangeListener(listener);
        multimerFeature.removePropertyChangeListener(listener);
    }

    @Override
    public void parseLibSbgnLabel(Label sbgnLabel) {
        labelFeature.parseLibSbgnLabel(sbgnLabel);
    }

    @Override
    public void parseLibSBGNGlyph(Glyph sbgnGlyph) {
        // TODO
    }
}
