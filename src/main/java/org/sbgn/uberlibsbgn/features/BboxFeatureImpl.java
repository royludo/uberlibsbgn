package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BboxFeatureImpl<T extends AbstractUGlyph<T>> implements BboxFeature {

    private AbstractUGlyph<T> uGlyph;
    private Rectangle2D bbox;
    private final PropertyChangeSupport pcs;
    private EventType eventType; // to separate bbox of uglyphs from labels or others

    final Logger logger = LoggerFactory.getLogger(BboxFeatureImpl.class);

    public BboxFeatureImpl(AbstractUGlyph<T> uGlyph, EventType eventType) {
        logger.trace("Create BboxFeature");
        this.uGlyph = uGlyph;
        this.pcs = new PropertyChangeSupport(uGlyph);
        this.eventType = eventType;
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
    public AbstractUGlyph<T> setBbox(Rectangle2D newBbox) {
        // throw event
        Rectangle2D oldBbox = this.getBbox();
        this.bbox = newBbox;

        this.pcs.firePropertyChange(eventType.getEventKey(), oldBbox, newBbox);
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
