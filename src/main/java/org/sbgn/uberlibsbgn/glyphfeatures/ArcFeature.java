package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.ArcClazz;
import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UArc;
import org.sbgn.uberlibsbgn.UArcClass;

import java.util.List;

public interface ArcFeature extends HasArcChangeListener {

    List<UArc> getArcs();

    List<UArc> getIncomingArcs();
    void addIncomingArc(UArc arc);
    List<UArc> getOutgoingArcs();
    void addOutgoingArc(UArc arc);

    UArc addArcTo(ArcClazz arcClazz, AbstractUGlyph glyph);
    UArc addArcFrom(ArcClazz arcClazz, AbstractUGlyph glyph);

    boolean removeArc(UArc arc);
}
