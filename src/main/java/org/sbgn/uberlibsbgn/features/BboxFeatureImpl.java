package org.sbgn.uberlibsbgn.features;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.transform.Translate;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BboxFeatureImpl implements BboxFeature {

    private AbstractUGlyph uGlyph;
    private Rectangle2D bbox;
    private final PropertyChangeSupport pcs;
    private EventType eventType; // to separate bbox of uglyphs from labels or others

    final Logger logger = LoggerFactory.getLogger(BboxFeatureImpl.class);

    public BboxFeatureImpl(AbstractUGlyph uGlyph, EventType eventType) {
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
    public void setBbox(Rectangle2D newBbox) {
        // throw event
        Rectangle2D oldBbox = this.getBbox();
        this.bbox = newBbox;

        logger.trace("pcs fire propery change {}", eventType);
        this.pcs.firePropertyChange(eventType.getEventKey(), oldBbox, newBbox);
    }

    @Override
    public boolean isBboxDefined() {
        return !(this.bbox.getWidth() <= 0 || this.bbox.getHeight() <= 0);
    }

    @Override
    public void registerBboxToPropertySender(HasPropertyChangeListener listener) {
        listener.addPropertyChangeListener(this);
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
        logger.trace("Event {} {}", eventName, this.getBbox());

        if(eventName.equals(EventType.BBOX.getEventKey()) || eventName.equals(EventType.UNITOFINFOBBOX.getEventKey())) { // parent glyph changed
            // this is not circular because a glyph won't be registered as listening to itself
            //AbstractUGlyph source = (AbstractUGlyph) propertyChangeEvent.getSource();
            Rectangle2D oldbbox = (Rectangle2D) propertyChangeEvent.getOldValue();
            Rectangle2D newbbox = (Rectangle2D) propertyChangeEvent.getNewValue();
            Translate t = Utilities.getTranslateFromRect(oldbbox, newbbox);
            logger.trace("translate {}", t);
            Point2D bboxLocation = new Point2D(this.bbox.getMinX(), this.bbox.getMinY());
            Point2D transformedLocation = t.transform(bboxLocation);
            this.setBbox(new Rectangle2D(transformedLocation.getX(), transformedLocation.getY(),
                    this.bbox.getWidth(), this.bbox.getHeight()));
            logger.trace("new bbox {}", this.getBbox());

        }
    }
}
