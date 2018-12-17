package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.bindings.Bbox;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BboxFeatureImpl implements BboxFeature {

    private AbstractUGlyph uGlyph;
    private Rectangle2D bbox;
    private final PropertyChangeSupport pcs;
    private String eventName;

    public BboxFeatureImpl(AbstractUGlyph uGlyph, String eventName) {
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
        this.eventName = eventName;
        this.bbox = new Rectangle2D.Double();
        // add bbox to the sbgn glyph directly ? we don't know if label or glyph...
    }

    /**
     * Returns a copy of the bbox
     * @return
     */
    @Nonnull
    @Override
    public Rectangle2D getBbox() {
        // we don't want a bbox to be modified outside of this class
        // as it would mess the indexing
        return new Rectangle2D.Double(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
    }

    @Override
    public AbstractUGlyph setBbox(Rectangle2D newBbox) {
        // throw event
        Rectangle2D oldBbox = this.getBbox();
        this.bbox = newBbox;

        this.pcs.firePropertyChange(eventName, oldBbox, newBbox);
        return this.uGlyph;
    }

    @Override
    public boolean isBboxDefined() {
        return !this.bbox.isEmpty();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
