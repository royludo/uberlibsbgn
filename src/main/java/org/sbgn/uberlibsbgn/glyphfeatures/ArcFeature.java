package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.UArc;

import java.util.List;

public interface ArcFeature extends HasArcChangeListener {

    List<UArc> getArcs();

    List<UArc> getIncomingArcs();

    List<UArc> getOutgoingArcs();

    boolean addIncomingArc(UArc arc);
    boolean addOutgoingArc(UArc arc);

    boolean removeArc(UArc arc);
}
