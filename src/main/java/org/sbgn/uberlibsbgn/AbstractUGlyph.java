package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;
import org.sbgn.bindings.Bbox;
import org.sbgn.bindings.Glyph;
import org.sbgn.uberlibsbgn.glyphfeatures.BboxFeature;
import org.sbgn.uberlibsbgn.glyphfeatures.BboxFeatureImpl;

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

    private String id;

    /**
     * Useful representation of the bounding box that can be used for geometry operations.
     */
    private BboxFeature bboxFeature;

    /**
     * This cannot be used by client code as not enough information is provided.
     * We need to have at least the class.
     */
    private AbstractUGlyph() {
        this.id = UUID.randomUUID().toString();
        this.bboxFeature = new BboxFeatureImpl(this, "bbox");

        /*this.indexNode = new IndexNode(this, DefaultUMapFactory.getDefaultUMap().getIndexManager());
        DefaultUMapFactory.getDefaultUMap().add(this);*/
        
    }

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        this();
        this.glyphType = GlyphType.fromGlyphClazz(GlyphClazz.fromClazz(clazz));

    }

    public GlyphType getGlyphType() {
        return glyphType;
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

}