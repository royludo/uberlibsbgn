package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Rectangle2D;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeEvent;
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
        this.bbox = new Rectangle2D(0,0,0,0);
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
        return new Rectangle2D(bbox.getMinX(), bbox.getMinY(), bbox.getWidth(), bbox.getHeight());
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
        return !(this.bbox.getWidth() <= 0 || this.bbox.getHeight() <= 0);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        String eventName = propertyChangeEvent.getPropertyName();

        if(eventName.equals(EventType.BBOX.getEventKey())) { // parent glyph changed
            // this is not circular because a glyph won't be registered as listening to itself
            AbstractUGlyph source = (AbstractUGlyph) propertyChangeEvent.getSource();
            Rectangle2D oldbbox = (Rectangle2D) propertyChangeEvent.getOldValue();
            Rectangle2D newbbox = (Rectangle2D) propertyChangeEvent.getOldValue();
            // TODO continue

        }
    }
}
