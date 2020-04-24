package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;
import org.sbgn.uberlibsbgn.features.CompositeFeature;
import org.sbgn.uberlibsbgn.features.HasCompositeChangeListener;
import org.sbgn.uberlibsbgn.features.HasPropertyChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class GlyphFactory {

    private UMap map;

    final Logger logger = LoggerFactory.getLogger(GlyphFactory.class);

    public GlyphFactory(UMap map) {
        logger.trace("Create GlyphFactory");
        this.map = map;

    }
    
    /*public GlyphBuilder<Macromolecule> macromolecule() {
       return new GlyphBuilder<>(UGlyphClass.MACROMOLECULE, map);
   }*/

    public Macromolecule macromolecule() {
        return this.build(UGlyphClass.MACROMOLECULE, map);
    }

    public SimpleChemical simpleChemical() {
        return this.build(UGlyphClass.SIMPLE_CHEMICAL, map);
    }

    public Complex complex() {
        return this.build(UGlyphClass.COMPLEX, map);
    }

    public Process process() {
        return this.build(UGlyphClass.PROCESS, map);
    }

    @SuppressWarnings("unchecked")
    private <T extends EPN> T build(UGlyphClass clazz, UMap map) {
        logger.trace("build glyph of class: {}", clazz);
        T glyph;
        try {
            Class<?> c = UGlyphClass.getClass(clazz);
            glyph = (T) c.getDeclaredConstructor(CompositeFeature.class, UMap.class).newInstance(map, map);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("could not instantiate");
        }

        if(glyph instanceof HasCompositeChangeListener) {
            ((HasCompositeChangeListener) glyph).addCompositeChangeListener(map.getIndexManager());
        }

        if(glyph instanceof HasPropertyChangeListener) {
            ((HasPropertyChangeListener) glyph).addPropertyChangeListener(map.getIndexManager());
        }

        logger.trace("Add to map root");
        // fire event add glyph to root of the map ?
        map.add(glyph);

        return glyph;
    }
}
