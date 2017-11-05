package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UMap {

    private List<AbstractUGlyph> glyphList;

    private Map<String, AbstractUGlyph> idGlyphIndex;
    private Map<GlyphClazz, List<AbstractUGlyph>> classGlyphIndex;

    private org.sbgn.bindings.Map map;

    public UMap(List<AbstractUGlyph> glyphs) {
        this.glyphList = glyphs;
        this.idGlyphIndex = new HashMap<>();

        this.classGlyphIndex = new HashMap<>();
        for(GlyphClazz c: GlyphClazz.values()) {
            this.classGlyphIndex.put(c, new ArrayList<>());
        }

        map = new org.sbgn.bindings.Map();
        for(AbstractUGlyph uglyph: this.glyphList) {
            idGlyphIndex.put(uglyph.getGlyph().getId(), uglyph);
            classGlyphIndex.get(uglyph.getClazz()).add(uglyph);
            map.getGlyph().add(uglyph.getGlyph());
        }

    }

    public List<AbstractUGlyph> filterGlyphs(Predicate<AbstractUGlyph> p) {
        return this.glyphList.stream().filter(p).collect(Collectors.toList());
    }

    public List<AbstractUGlyph> glyphsWithClass(GlyphClazz clazz) {
        return this.classGlyphIndex.get(clazz);
    }

    public void add(AbstractUGlyph glyph) {
        this.glyphList.add(glyph);
        this.idGlyphIndex.put(glyph.getId(), glyph);
        this.classGlyphIndex.get(glyph.getClazz()).add(glyph);
        this.map.getGlyph().add(glyph.getGlyph());
    }

    /*
    Find a way to filter with predicates using the indexes to avoid going through all the list each time
    Try to build a stream from a map and apply map-specific predicates that don't only rely on uglyph
    properties but also use map indexes.
     */
}
