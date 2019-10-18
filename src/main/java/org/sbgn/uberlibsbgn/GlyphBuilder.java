package org.sbgn.uberlibsbgn;

import org.sbgn.uberlibsbgn.glyphfeatures.HasCompositeChangeListener;
import org.sbgn.uberlibsbgn.glyphfeatures.HasPropertyChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlyphBuilder<T extends AbstractUGlyph> {

    private final T glyph;
    private UMap map;

    final Logger logger = LoggerFactory.getLogger(GlyphBuilder.class);

    public GlyphBuilder(UGlyphClass clazz, UMap map) {
        logger.trace("Create GlyphBuilder for class: {}", clazz);
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
        logger.trace("Build glyph and add to map root");
        if(glyph instanceof HasCompositeChangeListener) {
            ((HasCompositeChangeListener) glyph).addCompositeChangeListener(map.getIndexManager());
        }

        if(glyph instanceof HasPropertyChangeListener) {
            ((HasPropertyChangeListener) glyph).addPropertyChangeListener(map.getIndexManager());
        }


        // fire event add glyph to root of the map ?
        map.add(glyph); // TODO don't do if asChildOf is used

        return glyph;
    }
}
