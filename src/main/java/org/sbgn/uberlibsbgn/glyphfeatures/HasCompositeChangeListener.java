package org.sbgn.uberlibsbgn.glyphfeatures;

public interface HasCompositeChangeListener {

    void addCompositeChangeListener(CompositeChangeListener listener);

    void removeCompositeChangeListener(CompositeChangeListener listener);
}
