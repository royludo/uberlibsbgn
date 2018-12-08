package org.sbgn.uberlibsbgn;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class IndexManager implements PropertyChangeListener, RelationChangeListener {

    /*
    responsible for maintaining correct datastructures
    ensure uniqueness of ids

    glyphclass index
    id index
    label index, exact and regexp search
    inclusion tree, depth and breadth first iterating through all map (as the composite feature
    allow it from specific component)
    quadtree for collision and inclusion detection
     */

    private Map<String, AbstractUGlyph> idMap;
    private DefaultTreeModel inclusionTree;
    private DefaultMutableTreeNode inclusionTreeRoot;
    //private Map<String, DefaultMutableTreeNode> idTreeMap;

    public IndexManager() {
        this.idMap = new HashMap<>();
        // empty root node will represent the default outside container
        this.inclusionTreeRoot = new DefaultMutableTreeNode();
        this.inclusionTree = new DefaultTreeModel(this.inclusionTreeRoot);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();
        switch(evt.getPropertyName()) {
            case "class": break;
            // update class
            case "id":
                this.idMap.remove((String) evt.getOldValue());
                this.idMap.put((String) evt.getNewValue(), sourceGlyph);
                break;
        }

    }

    public Map<String, AbstractUGlyph> getIdMap() {
        return idMap;
    }

    @Override
    public void relationChangeAdded(RelationChangeEvent evt) {
        AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();
        DefaultMutableTreeNode glyphTreeNode = sourceGlyph.getIndexNode().getInclusionTreeNode();
        sourceGlyph.getIndexNode().getInclusionTreeNode().removeFromParent();

        if(evt.getNewParent() != null) {
            AbstractUGlyph newParent = evt.getNewParent();
            DefaultMutableTreeNode newParentTreeNode = newParent.getIndexNode().getInclusionTreeNode();
            newParentTreeNode.add(glyphTreeNode);
        }
        else { // include in default container
            this.inclusionTreeRoot.add(glyphTreeNode);
        }
    }

    @Override
    public void relationChangeRemoved(RelationChangeEvent evt) {
        AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();
        DefaultMutableTreeNode glyphTreeNode = sourceGlyph.getIndexNode().getInclusionTreeNode();
        sourceGlyph.getIndexNode().getInclusionTreeNode().removeFromParent();

        if(evt.getOldParent() != null) {
            AbstractUGlyph oldParent = evt.getOldParent();
            DefaultMutableTreeNode oldParentTreeNode = oldParent.getIndexNode().getInclusionTreeNode();
            oldParentTreeNode.remove(glyphTreeNode);
        }
        else { // remove from default container
            this.inclusionTreeRoot.remove(glyphTreeNode);
        }

    }
}
