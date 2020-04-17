package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.FillRule;

import java.util.Optional;
import java.util.OptionalDouble;

public class GlyphStyleImpl implements GlyphStyle {

    private FillRule fillRule;
    private FontWeight fontWeight;
    private FontFamily fontFamily;
    private FontStyle fontStyle;
    private VTextAnchor vTextAnchor;
    private HTextAnchor hTextAnchor;
    private double fontSize;
    private BackgroundType backgroundType;
    private Color backgroundColor;
    private LinearGradient backgroundLinearGradient;
    private Color strokeColor;
    private double strokeWidth;


    @Override
    public Optional<FillRule> getFillRule() {
        return Optional.ofNullable(fillRule);
    }

    @Override
    public void setFillRule(FillRule fillRule) {
        this.fillRule = fillRule;
    }

    @Override
    public Optional<FontWeight> getFontWeight() {
        return Optional.ofNullable(fontWeight);
    }

    @Override
    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    @Override
    public Optional<FontFamily> getFontFamily() {
        return Optional.ofNullable(fontFamily);
    }

    @Override
    public void setFontFamily(FontFamily fontFamily) {
        this.fontFamily = fontFamily;
    }

    @Override
    public Optional<FontStyle> getFontStyle() {
        return Optional.ofNullable(fontStyle);
    }

    @Override
    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public Optional<HTextAnchor> getHTextAnchor() {
        return Optional.ofNullable(hTextAnchor);
    }

    @Override
    public void setHTextAnchor(HTextAnchor hTextAnchor) {
        this.hTextAnchor = hTextAnchor;
    }

    @Override
    public Optional<VTextAnchor> getVTextAnchor() {
        return Optional.ofNullable(vTextAnchor);
    }

    @Override
    public void setVTextAnchor(VTextAnchor vTextAnchor) {
        this.vTextAnchor = vTextAnchor;
    }

    @Override
    public OptionalDouble getFontSize() {
        return OptionalDouble.of(fontSize);
    }

    @Override
    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public Optional<BackgroundType> getBackgroundType() {
        return Optional.ofNullable(backgroundType);
    }

    @Override
    public void setBackgroundType(BackgroundType backgroundType) {
        this.backgroundType = backgroundType;
    }

    @Override
    public Optional<Color> getBackgroundColor() {
        return Optional.ofNullable(backgroundColor);
    }

    @Override
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    @Override
    public Optional<LinearGradient> getBackgroundLinearGradient() {
        return Optional.ofNullable(backgroundLinearGradient);
    }

    @Override
    public void setBackgroundLinearGradient(LinearGradient linearGradient) {
        this.backgroundLinearGradient = linearGradient;
    }

    @Override
    public Optional<Color> getStrokeColor() {
        return Optional.ofNullable(this.strokeColor);
    }

    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    @Override
    public OptionalDouble getStrokeWidth() {
        return OptionalDouble.of(this.strokeWidth);
    }

    @Override
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }
}
