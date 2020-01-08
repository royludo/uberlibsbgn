package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.CompositeFeature;

public class Process extends EPN {
    public Process(CompositeFeature parent) {
        super("process", parent);
    }

    @Override
    protected Process self() {
        return this;
    }
}
