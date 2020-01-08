package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlyphFactory {

    private UMap map;

    final Logger logger = LoggerFactory.getLogger(GlyphFactory.class);

    public GlyphFactory(UMap map) {
        logger.trace("Create GlyphFactory");
        this.map = map;

    }

   /* public static GlyphFactory createFactory(UMap map, UGlyphClass clazz) {
        return new GlyphFactory(map, clazz);
    }*/

    public GlyphBuilder newGlyphOfType(UGlyphClass clazz) {
       return new GlyphBuilder(clazz, map);
   }

    public GlyphBuilder<Macromolecule> macromolecule() {
       return new GlyphBuilder<>(UGlyphClass.MACROMOLECULE, map);
   }

    public GlyphBuilder<SimpleChemical> simpleChemical() {
        return new GlyphBuilder<>(UGlyphClass.SIMPLE_CHEMICAL, map);
    }

    public GlyphBuilder<Complex> complex() {
        return new GlyphBuilder<>(UGlyphClass.COMPLEX, map);
    }
}
