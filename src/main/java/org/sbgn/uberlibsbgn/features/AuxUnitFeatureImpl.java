package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.AuxiliaryUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuxUnitFeatureImpl implements AuxUnitFeature {

    private AuxiliaryUnit uGlyph;
    String separator;
    private AbstractUGlyph parentGlyph;

    private final PropertyChangeSupport pcs;

    final Logger logger = LoggerFactory.getLogger(AuxUnitFeatureImpl.class);

    public AuxUnitFeatureImpl(AuxiliaryUnit uGlyph, String separator, AbstractUGlyph parentGlyph) {
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
        this.separator = separator;
        this.parentGlyph = parentGlyph;
    }

    @Override
    public String getKey() {
        String[] parts = this.kvFromLabel();

        // if 1, return whole, if 2 then correctly formed and return key
        // if more then return the first part also, but probably it's malformed
        return parts[0];
    }

    @Override
    public void setKey(String key) {
        String[] parts = this.kvFromLabel();
        String newLabel = String.join(separator,key, parts[1]);
        this.uGlyph.setLabel(newLabel);
    }

    @Override
    public String getValue() {
        String[] parts = this.kvFromLabel();

        if(parts.length == 1) {
            return parts[0];
        }
        else {
            return parts[1];
        }
    }

    @Override
    public void setValue(String value) {
        String[] parts = this.kvFromLabel();
        String newLabel = String.join(separator,parts[0], value);
        this.uGlyph.setLabel(newLabel);
    }

    @Override
    public String getSeparator() {
        return this.separator;
    }

    @Override
    public AbstractUGlyph getParent() {
        return this.parentGlyph;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    // get 1 or 2 strings from the label split at first separator
    private String[] kvFromLabel() {
        return this.uGlyph.getLabel().split(separator, 2);
    }
}
