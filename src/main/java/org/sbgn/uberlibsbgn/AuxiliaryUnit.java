package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.features.*;

import java.beans.PropertyChangeListener;

// pb with this kind of objects, they are the features of other glyphs, but being glyphs themselves,
// they also have features.
public abstract class AuxiliaryUnit extends AbstractUGlyph implements LabelFeature, AuxUnitFeature {

    private LabelFeature labelFeature;
    private AuxUnitFeature auxUnitFeature;

    protected AuxiliaryUnit(String clazz, EventType labelEventType, String separator, AbstractUGlyph parentGlyph) {
        super(clazz, parentGlyph.getMap());
        this.labelFeature = new LabelFeatureImpl(this, labelEventType);
        ((LabelFeatureImpl) this.labelFeature).addlistener();
        this.auxUnitFeature = new AuxUnitFeatureImpl(this, separator, parentGlyph);
    }

    @Override
    public void setLabel(String label) {
        labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return labelFeature.getLabel();
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
    public void setLabelBboxPositionRelativeToParent(double relativeX, double relativeY) {
        labelFeature.setLabelBboxPositionRelativeToParent(relativeX, relativeY);
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
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        labelFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        labelFeature.removePropertyChangeListener(listener);
    }

    @Override
    public String getKey() {
        return auxUnitFeature.getKey();
    }

    @Override
    public void setKey(String key) {
        auxUnitFeature.setKey(key);
    }

    @Override
    public String getValue() {
        return auxUnitFeature.getValue();
    }

    @Override
    public void setValue(String value) {
        auxUnitFeature.setValue(value);
    }

    @Override
    public String getSeparator() {
        return auxUnitFeature.getSeparator();
    }

    @Override
    public AbstractUGlyph getParent() {
        return auxUnitFeature.getParent();
    }
}
