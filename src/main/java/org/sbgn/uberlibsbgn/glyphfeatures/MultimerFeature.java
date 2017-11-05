package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.uberlibsbgn.AbstractUGlyph;

public class MultimerFeature<T extends AbstractUGlyph & IMultimerFeature> implements IMultimerFeature {

    private AbstractUGlyph uGlyph;
    private boolean isMultimer;

    public MultimerFeature(AbstractUGlyph<T> uGlyph) {
        this.uGlyph = uGlyph;
        this.isMultimer = false;
    }

    @Override
    public AbstractUGlyph multimer() {

        if(this.isMultimer()) { // do nothing
            return this.uGlyph;
        }

        this.uGlyph.getGlyph().setClazz(this.uGlyph.getGlyph().getClazz()+" multimer");
        this.isMultimer = true;
        return this.uGlyph;
    }

    @Override
    public AbstractUGlyph multimer(boolean isMultimer) {
        if(this.isMultimer()) {
            if(!isMultimer) { // change to not multimer
                this.isMultimer = false;
                this.uGlyph.getGlyph().setClazz(this.uGlyph.getGlyph().getClazz().replace(" multimer", ""));
                return this.uGlyph;
            }
        }
        else {
            if(isMultimer) {  // change to multimer
                return this.multimer();
            }
        }
        return this.uGlyph;
    }

    @Override
    public boolean isMultimer() {
        return this.isMultimer;
    }
}
