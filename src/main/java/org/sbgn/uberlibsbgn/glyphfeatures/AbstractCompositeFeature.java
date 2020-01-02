package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractCompositeFeature implements CompositeFeature {

    private List<AbstractUGlyph> children;
    private List<CompositeChangeListener> compositeChangeListeners;
    private Predicate<AbstractUGlyph> includeCondition;

    public AbstractCompositeFeature(Predicate<AbstractUGlyph> includeCondition) {
        this.includeCondition = includeCondition;
        this.children = new ArrayList<>();
        this.compositeChangeListeners = new ArrayList<>();
    }

    @Override
    public AbstractUGlyph addChild(AbstractUGlyph child) {
        if(this.canBeIncluded(child)) {
            this.children.add(child);

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

    @Override
    public AbstractUGlyph removeChild(AbstractUGlyph child) {
        int i = this.children.indexOf(child);
        if(i == -1) {
            throw new IllegalArgumentException("Glyph "+child.getId()+" is not a child of this glyph "+this);
        }

        this.children.remove(i);

        // throw change event
        CompositeChangeEvent cce = new CompositeChangeEvent(
                this, Collections.singletonList(i), Collections.singletonList(child));
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            listener.compositeChildRemoved(cce);
        }

        return child;
    }

    @Override
    public List<AbstractUGlyph> getChildren() {
        return this.children;
    }

    @Override
    public Predicate<AbstractUGlyph> getIncludePermission() {
        return this.includeCondition;
    }

    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.add(listener);
    }

    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.remove(listener);
    }

}
