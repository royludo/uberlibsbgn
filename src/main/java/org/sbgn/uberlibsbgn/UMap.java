package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UMap {

    private List<AbstractUGlyph> glyphList;

    private org.sbgn.bindings.Map map;

    private IndexManager indexManager;

    private String id;

    public UMap() {
        this.id = "default";
        this.glyphList = new ArrayList<>();
        this.indexManager = new IndexManager();

        map = new org.sbgn.bindings.Map();
    }

    public UMap(List<AbstractUGlyph> glyphs) {
        this();

        for(AbstractUGlyph uglyph: glyphs) {
            this.add(uglyph);
        }

    }

    public List<AbstractUGlyph> filterGlyphs(Predicate<AbstractUGlyph> p) {
        return this.glyphList.stream().filter(p).collect(Collectors.toList());
    }

    /*public List<AbstractUGlyph> glyphsWithClass(GlyphClazz clazz) {
        return this.classGlyphIndex.get(clazz);
    }*/

    // add to root of the map
    public void add(AbstractUGlyph glyph) {
        this.glyphList.add(glyph);
        this.map.getGlyph().add(glyph.getGlyph());
        glyph.addPropertyChangeListener(this.indexManager);
        // notify index manager of change
        this.indexManager.relationChangeAdded(new RelationChangeEvent(glyph, null, null));
    }

    public void remove(AbstractUGlyph glyph) {

    }

    /*
    Find a way to filter with predicates using the indexes to avoid going through all the list each time
    Try to build a stream from a map and apply map-specific predicates that don't only rely on uglyph
    properties but also use map indexes.
     */

    public String getId() {
        return id;
    }

    public IndexManager getIndexManager() {
        return indexManager;
    }
}
