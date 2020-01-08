package org.sbgn.uberlibsbgn.glyphfeatures;

import java.util.List;

public interface HasCompositeChangeListener {

    void addCompositeChangeListener(CompositeChangeListener listener);

    void removeCompositeChangeListener(CompositeChangeListener listener);

    List<CompositeChangeListener> getCompositeChangeListeners();
}
