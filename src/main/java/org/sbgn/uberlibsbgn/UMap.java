package org.sbgn.uberlibsbgn;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.sbgn.uberlibsbgn.UGlyphClass.MACROMOLECULE;

/**
 * UMap is the only object we are sure to be unique in practice, as it is the parent for everything.
 * Not a singleton, but will contain all the rest of the information. It is the sbgn file.
 * SO it should be the host for all kind of managers and indexes.
 *
 * To prevent difficulties with managing Uobjects indexes, disallow any object creation outside of
 * the map context. Everything must be created through the map. So management can be done within the map context.
 *
 * For submap management: a UMap should contain submaps linked together, with a main map.
 *
 * Should store some default values the user could specify on map creation. Like default size of glyph.
 */
public class UMap {

    private List<AbstractUGlyph> glyphList;

    private org.sbgn.bindings.Map map;

    private IndexManager indexManager;

    private String id;

    private GlyphFactory glyphFactory;

    public UMap() {
        this.id = "default";
        this.glyphList = new ArrayList<>();
        this.indexManager = new IndexManager();
        this.glyphFactory = new GlyphFactory(this);

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
        //glyph.addPropertyChangeListener(this.indexManager);
        // notify index manager of change
        //this.indexManager.relationChangeAdded(new RelationChangeEvent(glyph, null, null));
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

    public GlyphFactory getFactory() {
        return this.glyphFactory;
    }
}
