package org.sbgn.uberlibsbgn;

import javafx.css.CssParser;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import org.eclipse.rdf4j.common.io.ResourceUtil;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleStatement;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Statements;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.features.*;
import org.sbgn.uberlibsbgn.indexing.DefaultIndexes;
import org.sbgn.uberlibsbgn.indexing.Index;
import org.sbgn.uberlibsbgn.indexing.LabelIndex;
import org.sbgn.uberlibsbgn.style.BackgroundType;
import org.sbgn.uberlibsbgn.style.MapStyle;
import org.sbgn.uberlibsbgn.style.MapStyleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.LinkPermission;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
public class UMap extends USBGNEntity implements CompositeFeature/*, MapStyle*/ {

    //private List<AbstractUGlyph> glyphList;

    private org.sbgn.bindings.Map map;

    private IndexManager indexManager;

    private GlyphFactory glyphFactory;

    private CompositeFeature mapRoot;

    private Language sbgnLanguage;

    private MapStyle style;

    private Model rdfModel;

    private IdManager idManager;

    private Properties properties;
    public static Properties defaultProperties;

    final Logger logger = LoggerFactory.getLogger(UMap.class);

    public UMap(Language sbgnLanguage) {
        super("mapId");
        logger.trace("Create UMap");

        if(sbgnLanguage == Language.ER) {
            throw new UnsupportedOperationException("uberlibsbgn only supports PD and AF maps");
        }

        this.sbgnLanguage = sbgnLanguage;
        this.mapRoot = new MapRootFeature();
        this.style = new MapStyleImpl();
        //this.glyphList = new ArrayList<>();
        this.indexManager = new IndexManager(mapRoot);
        this.glyphFactory = new GlyphFactory(this);
        this.rdfModel = new LinkedHashModel();
        this.properties = new Properties(defaultProperties);

        // process the id management property
        IdManager.IdStrategy idStrategy;// = IdManager.IdStrategy.SIMPLE_INCREMENT;
        String idManagementPropName = MapProperties.ID_MANAGEMENT_TYPE.toString();
        String idManagementPropValue = properties.getProperty(idManagementPropName);
        switch (idManagementPropValue) {
            case "uuid":
                idStrategy = IdManager.IdStrategy.UUID;
                break;
            case "increment":
                idStrategy = IdManager.IdStrategy.SIMPLE_INCREMENT;
                break;
            default:
                throw new IllegalArgumentException("Property "+idManagementPropName+" has unrecognized " +
                        " default value: "+idManagementPropValue);
        }
        this.idManager = new IdManager(idStrategy);
    }

    public UMap(Language sbgnLanguage, List<EPN> glyphs) {
        this(sbgnLanguage);

        logger.trace("Add list of glyphs");
        for(EPN uglyph: glyphs) {
            this.add(uglyph);
        }

    }

    /*
      Init the default properties
     */
    static {
        defaultProperties = new Properties();

        try {
            InputStream input = UMap.class.getClassLoader().getResourceAsStream("mapdefaults.properties");
            defaultProperties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("=========> "+defaultProperties);
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

    public MapStyle style() {
        return this.style;
    }

    @Override
    public UMap getMap() {
        return this;
    }

    public Model rdf() {
        return this.rdfModel;
    }

    public IdManager getIdManager() {
        return idManager;
    }

    /*public List<AbstractUGlyph> glyphsWithClass(GlyphClazz clazz) {
        return this.classGlyphIndex.get(clazz);
    }*/

    // add to root of the map
    // same as addChild
    /*public void add(EPN glyph) {
        logger.trace("add glyph {}", glyph.getId());
        this.mapRoot.addChild(glyph);
        //this.map.getGlyph().add(glyph.getGlyph());
        //glyph.addPropertyChangeListener(this.indexManager);
        // notify index manager of change
        //this.indexManager.relationChangeAdded(new RelationChangeEvent(glyph, null, null));
    }*/

    /*public void addArc(ArcClazz clazz, AbstractUGlyph from, AbstractUGlyph to) {
        UArc arc = new UArc(clazz,from, to);

    }*/

    public void remove(AbstractUGlyph glyph) {
        logger.trace("remove glyph {}", glyph.getId());
    }

    /*
    Find a way to filter with predicates using the indexes to avoid going through all the list each time
    Try to build a stream from a map and apply map-specific predicates that don't only rely on uglyph
    properties but also use map indexes.
     */


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
    public EPN add(EPN child) {
        return mapRoot.add(child);
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

    public Properties getProperties() {
        return properties;
    }
}
