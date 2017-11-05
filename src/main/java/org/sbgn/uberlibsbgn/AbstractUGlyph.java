package org.sbgn.uberlibsbgn;

import org.sbgn.GlyphClazz;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;

import java.awt.geom.Rectangle2D;
import java.util.UUID;

abstract public class AbstractUGlyph<T extends AbstractUGlyph> {
    private Glyph glyph;

    private GlyphType glyphType;

    /**
     * Useful representation of the bounding box that can be used for geometry operations.
     */
    private Rectangle2D bbox;

    /**
     * This cannot be used by client code as not enough information is provided.
     * We need to have at least the class.
     */
    private AbstractUGlyph() {
        this.glyph = new Glyph();

        this.glyph.setId("_"+ UUID.randomUUID());
        this.glyph.setClazz("macromolecule");// TODO maybe unnecessary

        this.bbox = new Rectangle2D.Float();
        
    }

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        this();
        glyph.setClazz(clazz);
        this.glyphType = GlyphType.fromGlyphClazz(GlyphClazz.fromClazz(clazz));

    }

    public Glyph getGlyph() {
        return glyph;
    }

    public GlyphType getGlyphType() {
        return glyphType;
    }

    public T setHighMethod() {
        // do something
        return (T) this;
    }

    /*public T setLabel(String label) {
        Label sbgnLabel = new Label();
        sbgnLabel.setText(label);
        this.getGlyph().setLabel(sbgnLabel);
        return (T) this;
    }

    public String getLabel() {
        if(this.getGlyph().getLabel() != null) {
            return this.getGlyph().getLabel().getText();
        }
        return null;
    }*/

    public GlyphClazz getClazz() {
        return GlyphClazz.fromClazz(this.getGlyph().getClazz()); // TODO maybe change this fromClazz behavior
    }

    public String getId() {
        return this.getGlyph().getId();
    }

    // we can't provide a way to modify the class directly from here,
    // because now we have Macromolecules that can change their own class which is inconsistent.
    /*private T setClass(String clazz) {
        this.getGlyph().setClazz(clazz);
        return (T) this;
    }*/
}