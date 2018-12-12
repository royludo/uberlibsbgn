package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.ILabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.IMultimerFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.LabelFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.MultimerFeature;

import javax.crypto.Mac;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Macromolecule extends AbstractUGlyph<Macromolecule> implements IMultimerFeature, ILabelFeature,
        ComplexIncludible {

    private MultimerFeature<Macromolecule> multimerFeature;
    private LabelFeature<Macromolecule> labelFeature;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    protected Macromolecule() {
        super("macromolecule");
        this.multimerFeature = new MultimerFeature<>(this);
        this.labelFeature = new LabelFeature<>(this);
    }

    @Override
    public Macromolecule multimer() {
        return (Macromolecule) this.multimerFeature.multimer();
    }

    @Override
    public Macromolecule multimer(boolean isMultimer) {
        return(Macromolecule) this.multimerFeature.multimer(isMultimer);
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
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        labelFeature.addPropertyChangeListener(listener);
        multimerFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        labelFeature.removePropertyChangeListener(listener);
        multimerFeature.removePropertyChangeListener(listener);
    }

}
