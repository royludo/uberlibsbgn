package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * A CompositeFeatureImpl that has no include conditions (can include everything) and no associated glyph, as
 * it is used for a UMap.
 */
public class MapRootFeature implements CompositeFeature {


    private List<AbstractUGlyph> children;
    private List<CompositeChangeListener> compositeChangeListeners;

    final Logger logger = LoggerFactory.getLogger(MapRootFeature.class);

    public MapRootFeature() {
        logger.trace("Create MapRootFeature");
        this.children = new ArrayList<>();
        this.compositeChangeListeners = new ArrayList<>();
    }

    @Override
    public List<AbstractUGlyph> getChildren() {
        return children;
    }

    @Override
    public AbstractUGlyph addChild(AbstractUGlyph child) {
        logger.trace("maproot addChild "+child.getId());
        this.children.add(child);
        // throw change event
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            logger.trace("fire child added event for "+listener);
            listener.compositeChildAdded(new CompositeChangeEvent(this, Collections.singletonList(child)));
        }
        return child;
    }

    @Override
    public AbstractUGlyph removeChild(AbstractUGlyph child) {
        int i = this.children.indexOf(child);
        if(i == -1) {
            throw new IllegalArgumentException("Glyph "+child.getId()+" is not a child of the map root");
        }

        this.children.remove(i);

        // throw change event
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            listener.compositeChildRemoved(new CompositeChangeEvent(
                    this, Collections.singletonList(i), Collections.singletonList(child)));
        }

        return child;
    }

    @Override
    public Predicate<AbstractUGlyph> getIncludePermission() {
        return uGlyph -> true;
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        if (this.hasChildren()) {
            for (AbstractUGlyph child : this.getChildren()) {
                if (!child.accept(v)) {
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public void accept(IVisitor simpleVisitor) {
        if (this.hasChildren()) {
            for (AbstractUGlyph child : this.getChildren()) {
                child.accept(simpleVisitor);
            }
        }
    }

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.add(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.remove(listener);
    }
}
