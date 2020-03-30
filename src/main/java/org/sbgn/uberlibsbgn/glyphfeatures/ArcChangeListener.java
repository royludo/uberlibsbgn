package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.UArc;

public interface ArcChangeListener {

    void arcAddedAsSource(ArcChangeEvent e);

    void arcAddedAsTarget(ArcChangeEvent e);

    void arcRemovedAsSource(ArcChangeEvent e);

    void arcRemovedAsTarget(ArcChangeEvent e);

    void arcCreated(UArc uArc);

    void arcDeleted(UArc uArc);
}
