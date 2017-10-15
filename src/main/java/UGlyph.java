import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;

import java.util.UUID;

/**
 * Default concrete glyph
 */
public class UGlyph extends AbstractUGlyph<UGlyph>{

    public UGlyph(String clazz) {
        super(clazz);
    }

    /*public UGlyph(String label, String clazz) {
        super(clazz);
        this.setLabel(label);
    }*/


    /*public UGlyph(Glyph glyph) {
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

        public UGlyph build() {
            return new UGlyph(this);
        }

        public Builder label(String label) {
            Label sbgnLabel = new Label();
            sbgnLabel.setText(label);
            this.getGlyph().setLabel(sbgnLabel);
            return this;
        }
    }

    private UGlyph(Builder builder) {
        this(builder.glyph);
    }*/
}
