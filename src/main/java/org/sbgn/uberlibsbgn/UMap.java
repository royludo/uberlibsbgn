package org.sbgn.uberlibsbgn;

import org.sbgn.Language;
import org.sbgn.uberlibsbgn.glyphfeatures.*;
import org.sbgn.uberlibsbgn.indexing.DefaultIndexes;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
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
public class UMap extends USBGNEntity implements CompositeFeature {

    //private List<AbstractUGlyph> glyphList;

    private org.sbgn.bindings.Map map;

    private IndexManager indexManager;

    private String id;

    private GlyphFactory glyphFactory;

    private CompositeFeature mapRoot;

    private Language sbgnLanguage;

    final Logger logger = LoggerFactory.getLogger(UMap.class);

    public UMap(Language sbgnLanguage) {
        logger.trace("Create UMap");

        if(sbgnLanguage == Language.ER) {
            throw new UnsupportedOperationException("uberlibsbgn only supports PD and AF maps");
        }

        this.id = "default";
        this.sbgnLanguage = sbgnLanguage;
        this.mapRoot = new MapRootFeature();
        //this.glyphList = new ArrayList<>();
        this.indexManager = new IndexManager(mapRoot);
        this.glyphFactory = new GlyphFactory(this);



        map = new org.sbgn.bindings.Map();

    }

    public UMap(Language sbgnLanguage, List<EPN> glyphs) {
        this(sbgnLanguage);

        logger.trace("Add list of glyphs");
        for(EPN uglyph: glyphs) {
            this.add(uglyph);
        }

    }

    public Collection<AbstractUGlyph> getAllGlyphs() {
        return this.getIndexManager().getAllGlyphs();
    }

    public AbstractUGlyph getGlyph(String id) {
        return this.getIndexManager().getGlyph(id);
    }

    public List<AbstractUGlyph> filterGlyphs(Predicate<AbstractUGlyph> p) {
        logger.trace("Filter glyphs with predicate {}", p.toString());
        return this.getIndexManager().getAllGlyphs().stream().filter(p).collect(Collectors.toList());
    }

    /*public List<AbstractUGlyph> glyphsWithClass(GlyphClazz clazz) {
        return this.classGlyphIndex.get(clazz);
    }*/

    // add to root of the map
    public void add(EPN glyph) {
        logger.trace("add glyph {}", glyph.getId());
        this.mapRoot.addChild(glyph);
        //this.map.getGlyph().add(glyph.getGlyph());
        //glyph.addPropertyChangeListener(this.indexManager);
        // notify index manager of change
        //this.indexManager.relationChangeAdded(new RelationChangeEvent(glyph, null, null));
    }

    public void addArc(String clazz, AbstractUGlyph from, AbstractUGlyph to) {
        UArc arc = new UArc(clazz,from, to);

    }

    public void remove(AbstractUGlyph glyph) {
        logger.trace("remove glyph {}", glyph.getId());
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

    public Set<AbstractUGlyph> glyphsWithLabel(String label) {
        logger.trace("search glyphs with label: {}", label);
        return ((LabelIndex) this.indexManager.getIndex(DefaultIndexes.LABEL.getIndexKey())).getGlyphs(label);
    }

    public Set<AbstractUGlyph> glyphsWithLabelRegexp(String regexp) {
        logger.trace("search glyphs with label regexp: {}", regexp);
        return ((LabelIndex) this.indexManager.getIndex(DefaultIndexes.LABEL.getIndexKey())).getGlyphsWithRegexp(regexp);
    }

    protected CompositeFeature getMapRoot() {
        return mapRoot;
    }

    public void addIndex(Index index) {
        indexManager.addIndex(index);
    }

    public void removeIndex(String indexLabel) {
        indexManager.removeIndex(indexLabel);
    }

    public void accept(IVisitor simpleVisitor) {
        mapRoot.accept(simpleVisitor);
    }


    @Override
    public List<EPN> getChildren() {
        return mapRoot.getChildren();
    }

    @Override
    public Optional<AbstractUGlyph> getGlyph() {
        return mapRoot.getGlyph();
    }

    @Override
    public EPN addChild(EPN child) {
        return mapRoot.addChild(child);
    }

    @Override
    public EPN removeChild(EPN child) {
        return mapRoot.removeChild(child);
    }

    @Override
    public Predicate<EPN> getIncludePermission() {
        return mapRoot.getIncludePermission();
    }

    @Override
    public boolean hasChildren() {
        return mapRoot.hasChildren();
    }

    @Override
    public boolean canInclude(EPN child) {
        return mapRoot.canInclude(child);
    }

    @Override
    public boolean accept(IHierarchicalVisitor v) {
        return mapRoot.accept(v);
    }

    @Override
    public void addCompositeChangeListener(CompositeChangeListener listener) {
        mapRoot.addCompositeChangeListener(listener);
    }

    @Override
    public void removeCompositeChangeListener(CompositeChangeListener listener) {
        mapRoot.removeCompositeChangeListener(listener);
    }

    @Override
    public List<CompositeChangeListener> getCompositeChangeListeners() {
        return mapRoot.getCompositeChangeListeners();
    }
}
