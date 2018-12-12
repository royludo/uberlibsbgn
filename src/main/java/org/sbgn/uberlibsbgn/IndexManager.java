package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeEvent;
import org.sbgn.uberlibsbgn.glyphfeatures.CompositeChangeListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Index management and all other structures built on the side should updated first when a USomething is changed.
 * Then after everything is in a consistent state and all information is processed, the original libsbgn
 * structures should be updated.
 *
 * The flow should be: USomething gets changed by user -> index get updated -> libsbgn gets updated
 */
public class IndexManager implements PropertyChangeListener, CompositeChangeListener {

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

        System.out.println("prop change EVENT: "+ evt);

        /*AbstractUGlyph sourceGlyph = (AbstractUGlyph) evt.getSource();
        switch(evt.getPropertyName()) {
            case "class": break;
            // update class
            case "id":
                this.idMap.remove((String) evt.getOldValue());
                this.idMap.put((String) evt.getNewValue(), sourceGlyph);
                break;
        }*/

    }

    public Map<String, AbstractUGlyph> getIdMap() {
        return idMap;
    }

    @Override
    public void compositeChildAdded(CompositeChangeEvent e) {
        System.out.println("compo child added "+e);
    }

    @Override
    public void compositeChildRemoved(CompositeChangeEvent e) {
        System.out.println("compo child removed "+e);
    }
}
