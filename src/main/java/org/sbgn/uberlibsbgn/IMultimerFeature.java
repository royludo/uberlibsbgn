package org.sbgn.uberlibsbgn;

public interface IMultimerFeature {

    AbstractUGlyph multimer();

    AbstractUGlyph multimer(boolean isMultimer);

    boolean isMultimer();
}
