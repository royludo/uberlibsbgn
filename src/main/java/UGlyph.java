import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;

import java.util.UUID;

public class UGlyph {

    private Glyph glyph;

    public UGlyph() {
        glyph = new Glyph();

        glyph.setId("_"+ UUID.randomUUID());
        glyph.setClazz("macromolecule");
    }

    public UGlyph(String label) {
        this();

        Label sbgnLabel = new Label();
        sbgnLabel.setText(label);
        glyph.setLabel(sbgnLabel);

    }

    public UGlyph(String label, String clazz) {
        this(label);
        glyph.setClazz(clazz);
    }


    public UGlyph(Glyph glyph) {
        this.glyph = glyph;
    }

    public Glyph getGlyph() {
        return glyph;
    }

    public static class Builder {
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
    }
}
