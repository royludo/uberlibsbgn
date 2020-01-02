package org.sbgn.uberlibsbgn.glyphfeatures;

public interface ArcChangeListener {

    void arcAddedAsSource(ArcChangeEvent e);

    void arcAddedAsTarget(ArcChangeEvent e);

    void arcRemovedAsSource(ArcChangeEvent e);

    void arcRemovedAsTarget(ArcChangeEvent e);
}
