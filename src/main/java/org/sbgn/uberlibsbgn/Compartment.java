package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import org.sbgn.GlyphClazz;
import org.sbgn.bindings.Bbox;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.features.*;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Compartment extends EPN implements CompositeFeature, LabelFeature {

    private CompositeFeature compositeFeature;
    private LabelFeature labelFeature;

    protected Compartment(CompositeFeature parent, UMap parentMap, String id) {
        super("compartment", parent, parentMap, id);
        this.compositeFeature = new CompositeFeatureImpl(this, epn -> true);
        this.labelFeature = new LabelFeatureImpl(this, EventType.LABEL);
        ((LabelFeatureImpl) this.labelFeature).addlistener();
    }

    @Override
    public List<EPN> getChildren() {
        return compositeFeature.getChildren();
    }

    @Override
    public Optional<AbstractUGlyph> getGlyph() {
        return compositeFeature.getGlyph();
    }

    @Override
    public EPN add(EPN child) {
        return compositeFeature.add(child);
    }

    @Override
    public EPN removeChild(EPN child) {
        return compositeFeature.removeChild(child);
    }

    @Override
    public Predicate<EPN> getIncludePermission() {
        return this.compositeFeature.getIncludePermission();
    }

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeFeature.addCompositeChangeListener(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeFeature.removeCompositeChangeListener(listener);
    }

    @Override
    public List<CompositeChangeListener> getCompositeChangeListeners() {
        return compositeFeature.getCompositeChangeListeners();
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        return this.compositeFeature.accept(v);
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
    public void parseLibSbgnLabel(Label sbgnLabel) {
        labelFeature.parseLibSbgnLabel(sbgnLabel);
    }

    @Override
    public void parseLibSBGNGlyph(Glyph sbgnGlyph) {
        // basic checks
        if(!sbgnGlyph.getClazz().equals(GlyphClazz.COMPARTMENT.getClazz())) {
            throw new IllegalArgumentException("Trying to create compartment from glyph with class: " + sbgnGlyph.getClazz());
        }

        super.parseLibSBGNGlyph(sbgnGlyph);

        if(sbgnGlyph.getLabel() != null) {
            this.parseLibSbgnLabel(sbgnGlyph.getLabel());
        }

    }
}
