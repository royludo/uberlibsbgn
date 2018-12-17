package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;

public class GlyphFactory {

    private UMap map;


    public GlyphFactory(UMap map) {
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

    public GlyphBuilder<Complex> complex() {
        return new GlyphBuilder<>(UGlyphClass.COMPLEX, map);
    }
}
