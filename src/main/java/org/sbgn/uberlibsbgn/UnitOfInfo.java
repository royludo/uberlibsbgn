package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.features.EventType;

public class UnitOfInfo extends AuxiliaryUnit {

    protected UnitOfInfo(AbstractUGlyph parentGlyph, String id) {
        super("unit of information", EventType.UNITOFINFOLABEL, ":", parentGlyph, id);
    }

}
