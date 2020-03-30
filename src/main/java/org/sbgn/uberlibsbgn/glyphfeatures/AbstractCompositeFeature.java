package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.EPN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractCompositeFeature implements CompositeFeature {

    private List<EPN> children;
    private List<CompositeChangeListener> compositeChangeListeners;
    private Predicate<EPN> includeCondition;

    final Logger logger = LoggerFactory.getLogger(AbstractCompositeFeature.class);

    public AbstractCompositeFeature(Predicate<EPN> includeCondition) {
        this.includeCondition = includeCondition;
        this.children = new ArrayList<>();
        this.compositeChangeListeners = new ArrayList<>();
    }

    @Override
    public EPN add(EPN child) {
        if(this.canInclude(child)) {
            this.children.add(child);
            child.setParent(this);

            // throw change event
            CompositeChangeEvent cce = new CompositeChangeEvent(this, Collections.singletonList(child));
            for(CompositeChangeListener listener: this.compositeChangeListeners) {
                listener.compositeChildAdded(cce);
            }

            return child;
        }
        else {
            throw new IllegalArgumentException("Glyph "+child.getId()+" can't be included in this glyph "+this);
        }
    }

    // TODO what to do with the parent ??
    @Override
    public EPN removeChild(EPN child) {
        logger.trace("Remove child with id: {}", child.getId());
        int i = this.children.indexOf(child);
        if(i == -1) {
            throw new IllegalArgumentException("Glyph "+child.getId()+" is not a child of this glyph "+this);
        }

        this.children.remove(i);

        // throw change event
        CompositeChangeEvent cce = new CompositeChangeEvent(
                this, Collections.singletonList(i), Collections.singletonList(child));
        logger.trace("Send event to listeners {}", cce);
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            listener.compositeChildRemoved(cce);
        }

        return child;
    }

    @Override
    public List<EPN> getChildren() {
        return this.children;
    }

    @Override
    public Predicate<EPN> getIncludePermission() {
        return this.includeCondition;
    }

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.add(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.remove(listener);
    }

    @Override
    public List<CompositeChangeListener> getCompositeChangeListeners() {
        return this.compositeChangeListeners;
    }
}
