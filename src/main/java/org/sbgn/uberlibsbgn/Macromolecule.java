package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeatureImpl;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeatureImpl;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;

public class Macromolecule extends AbstractUGlyph<Macromolecule> implements MultimerFeature, LabelFeature,
        ComplexIncludible {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;

    protected Macromolecule() {
        super("macromolecule");
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this);
    }

    @Override
    public Macromolecule setMultimer(boolean isMultimer) {
        return(Macromolecule) this.multimerFeature.setMultimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public Macromolecule setLabel(String label) {
        return (Macromolecule) this.labelFeature.setLabel(label);
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
    public AbstractUGlyph setLabelBbox(Rectangle2D rect) {
        return labelFeature.setLabelBbox(rect);
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
}
