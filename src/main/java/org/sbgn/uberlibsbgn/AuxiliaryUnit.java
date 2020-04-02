package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.*;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

// pb with this kind of objects, they are the features of other glyphs, but being glyphs themselves,
// they also have features.
public abstract class AuxiliaryUnit extends AbstractUGlyph<AuxiliaryUnit> implements LabelFeature, AuxUnitFeature {

    private LabelFeature labelFeature;
    private AuxUnitFeature auxUnitFeature;

    protected AuxiliaryUnit(String clazz, EventType labelEventType, String separator) {
        super(clazz);
        this.labelFeature = new LabelFeatureImpl(this, labelEventType);
        this.auxUnitFeature = new AuxUnitFeatureImpl(this, separator);
    }

    @Override
    public AbstractUGlyph setLabel(String label) {
        return labelFeature.setLabel(label);
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
    public AbstractUGlyph setLabelBbox(Rectangle2D rect) {
        return labelFeature.setLabelBbox(rect);
    }

    @Override
    protected AuxiliaryUnit self() {
        return this;
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
}
