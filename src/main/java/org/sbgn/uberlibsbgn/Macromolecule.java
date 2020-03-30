package org.sbgn.uberlibsbgn;

import org.sbgn.ArcClazz;
import org.sbgn.uberlibsbgn.glyphfeatures.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Macromolecule extends EPN<Macromolecule> implements MultimerFeature, LabelFeature, ArcFeature,
        ComplexIncludible {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;
    private ArcFeature arcFeature;

    final Logger logger = LoggerFactory.getLogger(Macromolecule.class);

    protected Macromolecule(CompositeFeature parent) {
        super("macromolecule", parent);
        logger.trace("Create Macromolecule");
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this, EventType.LABEL);
        this.arcFeature = new ArcFeatureImpl(this);
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
    protected Macromolecule self() {
        return this;
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
    public List<UArc> getArcs() {
        return arcFeature.getArcs();
    }

    @Override
    public List<UArc> getIncomingArcs() {
        return arcFeature.getIncomingArcs();
    }

    @Override
    public List<UArc> getOutgoingArcs() {
        return arcFeature.getOutgoingArcs();
    }

    @Override
    public UArc addArcTo(ArcClazz arcClazz, AbstractUGlyph glyph) {
        return arcFeature.addArcTo(arcClazz, glyph);
    }

    @Override
    public UArc addArcFrom(ArcClazz arcClazz, AbstractUGlyph glyph) {
        return arcFeature.addArcFrom(arcClazz, glyph);
    }

    @Override
    public void addIncomingArc(UArc arc) {
        arcFeature.addIncomingArc(arc);
    }

    @Override
    public void addOutgoingArc(UArc arc) {
        arcFeature.addOutgoingArc(arc);
    }

    @Override
    public boolean removeArc(UArc arc) {
        return arcFeature.removeArc(arc);
    }

    @Override
    public void addArcChangeListener(ArcChangeListener listener) {
        arcFeature.addArcChangeListener(listener);
    }

    @Override
    public void removeArcChangeListener(ArcChangeListener listener) {
        arcFeature.removeArcChangeListener(listener);
    }
}
