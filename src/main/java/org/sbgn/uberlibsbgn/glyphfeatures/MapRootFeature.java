package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * A CompositeFeature that has no include conditions (can include everything).
 */
public class MapRootFeature implements ICompositeFeature {


    private List<AbstractUGlyph> children;
    private List<CompositeChangeListener> compositeChangeListeners;

    public MapRootFeature() {
        this.children = new ArrayList<>();
        this.compositeChangeListeners = new ArrayList<>();
    }

    @Override
    public List<AbstractUGlyph> getChildren() {
        return children;
    }

    @Override
    public AbstractUGlyph addChild(AbstractUGlyph child) {
        System.out.println("maproot addChild");
        this.children.add(child);
        // throw change event
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            System.out.println("fire child added event for "+listener);
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
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.add(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        this.compositeChangeListeners.remove(listener);
    }
}
