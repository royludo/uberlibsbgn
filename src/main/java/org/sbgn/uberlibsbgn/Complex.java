package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.features.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Complex extends EPN implements CompositeFeature, MultimerFeature, LabelFeature,
        ComplexIncludible {

    private CompositeFeature compositeFeature;
    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;

    final Logger logger = LoggerFactory.getLogger(Complex.class);

    protected Complex(CompositeFeature parent, UMap parentMap, String id) {
        super("complex", parent, parentMap, id);

        // define which kind of glyph are allowed to be included
        Predicate<EPN> p = epn ->
                epn instanceof ComplexIncludible;

        this.compositeFeature = new CompositeFeatureImpl(this, p);
        this.multimerFeature = new MultimerFeatureImpl(this);
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
        return compositeFeature.getIncludePermission();
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
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        compositeFeature.addCompositeChangeListener(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        compositeFeature.removeCompositeChangeListener(listener);
    }

    @Override
    public List<CompositeChangeListener> getCompositeChangeListeners() {
        return compositeFeature.getCompositeChangeListeners();
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        return this.compositeFeature.accept(v);
    }
}
