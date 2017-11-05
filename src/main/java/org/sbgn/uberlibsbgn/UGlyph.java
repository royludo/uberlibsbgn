package org.sbgn.uberlibsbgn;

/**
 * Default concrete glyph
 */
public class UGlyph extends AbstractUGlyph{

    public UGlyph(String clazz) {
        super(clazz);
    }

    /*public org.sbgn.uberlibsbgn.UGlyph(String label, String clazz) {
        super(clazz);
        this.setLabel(label);
    }*/


    /*public org.sbgn.uberlibsbgn.UGlyph(Glyph glyph) {
        this.glyph = glyph;
    }*/


    /*public static class Builder {
        private Glyph glyph;

        private Glyph getGlyph() {
            if(this.glyph == null) {
                this.glyph = new Glyph();
            }
            return this.glyph;
        }

        public org.sbgn.uberlibsbgn.UGlyph build() {
            return new org.sbgn.uberlibsbgn.UGlyph(this);
        }

        public Builder label(String label) {
            Label sbgnLabel = new Label();
            sbgnLabel.setText(label);
            this.getGlyph().setLabel(sbgnLabel);
            return this;
        }
    }

    private org.sbgn.uberlibsbgn.UGlyph(Builder builder) {
        this(builder.glyph);
    }*/
}
