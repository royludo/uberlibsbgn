package org.sbgn.uberlibsbgn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UMap {

    List<AbstractUGlyph> glyphList;
    Map<String, AbstractUGlyph> idIndex;
    org.sbgn.bindings.Map map;

    public UMap(List<AbstractUGlyph> glyphs) {
        this.glyphList = glyphs;
        this.idIndex = new HashMap<>();
        map = new org.sbgn.bindings.Map();
        for(AbstractUGlyph uglyph: this.glyphList) {
            idIndex.put(uglyph.getGlyph().getId(), uglyph);
            map.getGlyph().add(uglyph.getGlyph());
        }
    }

    public List<AbstractUGlyph> filterGlyphs(Predicate<AbstractUGlyph> p) {
        return this.glyphList.stream().filter(p).collect(Collectors.toList());
    }
}
