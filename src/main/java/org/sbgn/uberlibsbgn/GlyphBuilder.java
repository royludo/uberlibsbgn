package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.HasCompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.HasPropertyChangeListener;

public class GlyphBuilder<T extends AbstractUGlyph> {

    private final T glyph;
    private UMap map;

    public GlyphBuilder(UGlyphClass clazz, UMap map) {
        this.map = map;
        try {
            Class c = UGlyphClass.getClass(clazz);
            glyph = (T) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("could not instantiate");
        }
    }

   /* public static GlyphFactory createFactory(UMap map, UGlyphClass clazz) {
        return new GlyphFactory(map, clazz);
    }*/

    public GlyphBuilder asChildOf(AbstractUGlyph parentGlyph) {
        // do stuff
        // parentGlyph.addChild(this.glyph);
        return this;
    }

    public T build() {

        if(glyph instanceof HasCompositeChangeListener) {
            ((HasCompositeChangeListener) glyph).addCompositeChangeListener(map.getIndexManager());
        }

        if(glyph instanceof HasPropertyChangeListener) {
            ((HasPropertyChangeListener) glyph).addPropertyChangeListener(map.getIndexManager());
        }

        return glyph;
    }
}
