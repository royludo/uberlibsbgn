package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.DefaultUMapFactory;
import org.sbgn.uberlibsbgn.RelationChangeEvent;
import sun.reflect.generics.tree.Tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;

/**
 * Takes care of the nesting structure, maintains appropriate efficient data structure.
 * @param <T>
 */
public class CompositeFeature<T extends AbstractUGlyph & ICompositeFeature> implements ICompositeFeature {

    private AbstractUGlyph<T> uGlyph;
    private Predicate<AbstractUGlyph> includeCondition;

    public CompositeFeature(AbstractUGlyph<T> uGlyph, Predicate<AbstractUGlyph> includeCondition) {
        this.uGlyph = uGlyph;
        this.includeCondition = includeCondition;
    }

    public boolean addChild(AbstractUGlyph child) {
        if(this.canBeIncluded(child)) {
            this.uGlyph.getIndexNode().
                    getIndexManager().
                    relationChangeAdded(new RelationChangeEvent(child, null, this.uGlyph));
            this.uGlyph.addPropertyChangeListener(this.uGlyph.getIndexNode().getIndexManager());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<AbstractUGlyph> getAllChildren() {
        List<AbstractUGlyph> result = new ArrayList<>();
        for(Enumeration e = this.uGlyph.getIndexNode().getInclusionTreeNode().children();
            e.hasMoreElements();) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();

            if(n.getChildCount() > 0) {
                result.addAll(((ICompositeFeature) n.getUserObject()).getAllChildren());
            }
            result.add((AbstractUGlyph) n.getUserObject());
        }
        return result;
    }

    @Override
    public List<AbstractUGlyph> getFirstLevelChildren() {
        return Collections.list(this.uGlyph.getIndexNode().getInclusionTreeNode().children());

    }

    @Override
    public Predicate<AbstractUGlyph> getIncludePermission() {
        return this.includeCondition;
    }
}
