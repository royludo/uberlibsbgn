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

import static org.sbgn.uberlibsbgn.GlyphType.AUXILIARY_UNIT;

abstract public class AbstractUGlyph<T extends AbstractUGlyph<T>> extends USBGNEntity implements BboxFeature {

    private GlyphType glyphType;
    private UGlyphClass uGlyphClass;

    private String id;

    /**
     * Useful representation of the bounding box that can be used for geometry operations.
     */
    private BboxFeature bboxFeature;

    final Logger logger = LoggerFactory.getLogger(AbstractUGlyph.class);

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        logger.trace("Create AbstractUGlyph with class: {}", clazz);
        this.id = UUID.randomUUID().toString();
        logger.trace("Assigned random id: {}", this.id);
        this.glyphType = GlyphType.fromGlyphClazz(GlyphClazz.fromClazz(clazz));
        this.uGlyphClass = UGlyphClass.fromGlyphClazz(GlyphClazz.fromClazz(clazz));

        EventType bboxEventType;
        if(glyphType == AUXILIARY_UNIT) {
            bboxEventType = EventType.UNITOFINFOBBOX;
        }
        else {
            bboxEventType = EventType.BBOX;
        }

        this.bboxFeature = new BboxFeatureImpl<>(this, bboxEventType);

    }

    protected abstract T self(); // helps with the fluent methods and subclassing

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
        bboxFeature.setBbox(rect);
        return self();
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