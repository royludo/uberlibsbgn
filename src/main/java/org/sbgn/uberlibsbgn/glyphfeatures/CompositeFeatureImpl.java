package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import java.util.*;
import java.util.function.Predicate;

/**
 * Takes care of the nesting structure, maintains appropriate efficient data structure.
 */
public class CompositeFeatureImpl implements CompositeFeature {

    private AbstractUGlyph uGlyph;
    private Predicate<AbstractUGlyph> includeCondition;
    private List<AbstractUGlyph> children;

    private List<CompositeChangeListener> compositeChangeListeners;

    public CompositeFeatureImpl(AbstractUGlyph uGlyph, Predicate<AbstractUGlyph> includeCondition) {
        this.uGlyph = uGlyph;
        this.includeCondition = includeCondition;
        this.children = new ArrayList<>();

        this.compositeChangeListeners = new ArrayList<>();
    }

    public AbstractUGlyph addChild(AbstractUGlyph child) {
        if(this.canBeIncluded(child)) {
            /*this.uGlyph.getIndexNode().
                    getIndexManager().
                    relationChangeAdded(new RelationChangeEvent(child, null, this.uGlyph));
            this.uGlyph.addPropertyChangeListener(this.uGlyph.getIndexNode().getIndexManager());*/

            this.children.add(child);
            //this.uGlyph.getGlyph().getGlyph().add(child.getGlyph());

            // throw change event
            for(CompositeChangeListener listener: this.compositeChangeListeners) {
                listener.compositeChildAdded(new CompositeChangeEvent(this.uGlyph, Collections.singletonList(child)));
            }

            return child;
        }
        else {
            throw new IllegalArgumentException("Glyph "+child.getId()+" can't be included in this glyph "+this.uGlyph.getId());
        }
    }

    @Override
    public AbstractUGlyph removeChild(AbstractUGlyph child) {
        int i = this.children.indexOf(child);
        if(i == -1) {
            throw new IllegalArgumentException("Glyph "+child.getId()+" is not a child of this glyph "+this.uGlyph.getId());
        }

        //this.uGlyph.getGlyph().getGlyph().remove(child.getGlyph());
        this.children.remove(i);

        // throw change event
        for(CompositeChangeListener listener: this.compositeChangeListeners) {
            listener.compositeChildRemoved(new CompositeChangeEvent(
                    this.uGlyph, Collections.singletonList(i), Collections.singletonList(child)));
        }

        return child;
    }

    /*@Override
    public List<AbstractUGlyph> getAllChildren() {
        List<AbstractUGlyph> result = new ArrayList<>();
        for(Enumeration e = this.uGlyph.getIndexNode().getInclusionTreeNode().children();
            e.hasMoreElements();) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();

            if(n.getChildCount() > 0) {
                result.addAll(((CompositeFeature) n.getUserObject()).getAllChildren());
            }
            result.add((AbstractUGlyph) n.getUserObject());
        }
        return result;
    }*/

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
