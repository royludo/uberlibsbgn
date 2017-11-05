package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public interface IMultimerFeature {

    AbstractUGlyph multimer();

    AbstractUGlyph multimer(boolean isMultimer);

    boolean isMultimer();
}
