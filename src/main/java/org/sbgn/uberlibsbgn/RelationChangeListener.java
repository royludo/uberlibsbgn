package org.sbgn.uberlibsbgn;

import java.util.EventListener;

public interface RelationChangeListener extends EventListener {

    void relationChangeAdded(RelationChangeEvent evt);
    void relationChangeRemoved(RelationChangeEvent evt);
    default void relationChangeMoved(RelationChangeEvent evt) {
        this.relationChangeRemoved(new RelationChangeEvent(evt.getSource(), evt.getOldParent(), null));
        this.relationChangeAdded(new RelationChangeEvent(evt.getSource(), null, evt.getNewParent()));
    }

}
