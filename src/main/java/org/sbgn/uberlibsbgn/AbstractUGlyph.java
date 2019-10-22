package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;
import org.sbgn.bindings.Bbox;
import org.sbgn.bindings.Glyph;
import org.sbgn.uberlibsbgn.glyphfeatures.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

abstract public class AbstractUGlyph<T extends AbstractUGlyph> implements BboxFeature {

    private GlyphType glyphType;
    private UGlyphClass uGlyphClass;

    private String id;

    /**
     * Useful representation of the bounding box that can be used for geometry operations.
     */
    private BboxFeature bboxFeature;

    final Logger logger = LoggerFactory.getLogger(AbstractUGlyph.class);

    /**
     * This cannot be used by client code as not enough information is provided.
     * We need to have at least the class.
     */
    private AbstractUGlyph() {
        logger.trace("Create AbstractUGlyph");
        this.id = UUID.randomUUID().toString();
        logger.trace("Assigned random id: {}", this.id);
        this.bboxFeature = new BboxFeatureImpl(this, EventType.BBOX.getEventKey());

        /*this.indexNode = new IndexNode(this, DefaultUMapFactory.getDefaultUMap().getIndexManager());
        DefaultUMapFactory.getDefaultUMap().add(this);*/
        
    }

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        this();
        logger.trace("Create AbstractUGlyph with class: {}", clazz);
        this.glyphType = GlyphType.fromGlyphClazz(GlyphClazz.fromClazz(clazz));
        this.uGlyphClass = UGlyphClass.fromGlyphClazz(GlyphClazz.fromClazz(clazz));

    }

    public GlyphType getGlyphType() {
        return glyphType;
    }

    public UGlyphClass getUGlyphClass() {
        return uGlyphClass;
    }


    public String getId() {
        return this.id;
    }


    @Nonnull
    @Override
    public Rectangle2D getBbox() {
        return bboxFeature.getBbox();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T setBbox(Rectangle2D rect) {
        return (T) bboxFeature.setBbox(rect);
    }

    @Override
    public boolean isBboxDefined() {
        return bboxFeature.isBboxDefined();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        bboxFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        bboxFeature.removePropertyChangeListener(listener);
    }

    public boolean accept(IHierarchicalVisitor v) {
        return v.visitLeaf(this);
    }

    public void accept(IVisitor simpleVisitor) {
        simpleVisitor.visit(this);
    }

    public boolean hasLabelFeature() {
        return this instanceof LabelFeature;
    }

}