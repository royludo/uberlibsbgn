package org.sbgn.uberlibsbgn.features;

public interface CompositeChangeListener {

    void compositeChildAdded(CompositeChangeEvent e);

    void compositeChildRemoved(CompositeChangeEvent e);
}
