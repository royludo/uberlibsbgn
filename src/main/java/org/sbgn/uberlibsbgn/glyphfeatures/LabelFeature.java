package org.sbgn.uberlibsbgn.glyphfeatures;

import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.AbstractUGlyph;

import java.util.Optional;

public class LabelFeature<T extends AbstractUGlyph & ILabelFeature> implements ILabelFeature {

    private AbstractUGlyph<T> uGlyph;

    public LabelFeature(AbstractUGlyph<T> uGlyph) {
        this.uGlyph = uGlyph;
    }

    @Override
    public AbstractUGlyph<T> setLabel(String label) {
        Label sbgnLabel = new Label();
        sbgnLabel.setText(label);
        this.uGlyph.getGlyph().setLabel(sbgnLabel);
        return this.uGlyph;
    }

    @Override
    public String getLabel() {
        return this.uGlyph.getGlyph().getLabel().getText();
    }
}
