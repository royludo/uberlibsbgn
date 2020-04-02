package org.sbgn.uberlibsbgn.features;

public interface HasArcChangeListener {

    void addArcChangeListener(ArcChangeListener listener);

    void removeArcChangeListener(ArcChangeListener listener);
}
