package org.sbgn.uberlibsbgn;

import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;

import java.util.UUID;

abstract class AbstractUGlyph<T extends AbstractUGlyph> {
    private Glyph glyph;

    /**
     * This cannot be used by client code as not enough information is provided.
     * We need to have at least the class.
     */
    private AbstractUGlyph() {
        glyph = new Glyph();

        glyph.setId("_"+ UUID.randomUUID());
        glyph.setClazz("macromolecule");
    }

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        this();
        glyph.setClazz(clazz);

    }

    public Glyph getGlyph() {
        return glyph;
    }

    public T setLabel(String label) {
        Label sbgnLabel = new Label();
        sbgnLabel.setText(label);
        this.getGlyph().setLabel(sbgnLabel);
        return (T) this;
    }

    public T setClass(String clazz) {
        this.getGlyph().setClazz(clazz);
        return (T) this;
    }
}