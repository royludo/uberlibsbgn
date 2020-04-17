package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.FillRule;

import java.util.Optional;
import java.util.OptionalDouble;

public interface OtherStyle {

    Optional<FillRule> getFillRule();
    void setFillRule(FillRule fillRule);

    Optional<FontWeight> getFontWeight();
    void setFontWeight(FontWeight fontWeight);

    Optional<FontFamily> getFontFamily();
    void setFontFamily(FontFamily fontFamily);

    Optional<FontStyle> getFontStyle();
    void setFontStyle(FontStyle fontStyle);

    Optional<HTextAnchor> getHTextAnchor();
    void setHTextAnchor(HTextAnchor hTextAnchor);

    Optional<VTextAnchor> getVTextAnchor();
    void setVTextAnchor(VTextAnchor vTextAnchor);

    OptionalDouble getFontSize();
    void setFontSize(double fontSize);
}
