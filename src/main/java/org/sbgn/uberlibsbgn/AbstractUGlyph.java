package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.FillRule;
import org.sbgn.GlyphClazz;
import org.sbgn.uberlibsbgn.features.*;
import org.sbgn.uberlibsbgn.style.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.sbgn.uberlibsbgn.GlyphType.AUXILIARY_UNIT;

abstract public class AbstractUGlyph extends USBGNEntity implements BboxFeature, GlyphStyle {

    private GlyphType glyphType;
    private UGlyphClass uGlyphClass;


    /**
     * Useful representation of the bounding box that can be used for geometry operations.
     */
    private BboxFeature bboxFeature;

    private GlyphStyle style;

    final Logger logger = LoggerFactory.getLogger(AbstractUGlyph.class);

    /**
     * Minimal possible constructor for a glyph.
     * @param clazz
     */
    public AbstractUGlyph(String clazz) {
        logger.trace("Create AbstractUGlyph with class: {}", clazz);
        logger.trace("Assigned random id: {}", this.getId());
        this.glyphType = GlyphType.fromGlyphClazz(GlyphClazz.fromClazz(clazz));
        this.uGlyphClass = UGlyphClass.fromGlyphClazz(GlyphClazz.fromClazz(clazz));

        EventType bboxEventType;
        if(glyphType == AUXILIARY_UNIT) {
            bboxEventType = EventType.UNITOFINFOBBOX;
        }
        else {
            bboxEventType = EventType.BBOX;
        }

        this.style = new GlyphStyleImpl();
        this.bboxFeature = new BboxFeatureImpl(this, bboxEventType);

    }

    public GlyphType getGlyphType() {
        return glyphType;
    }

    public UGlyphClass getUGlyphClass() {
        return uGlyphClass;
    }


    @Nonnull
    @Override
    public Rectangle2D getBbox() {
        return bboxFeature.getBbox();
    }

    @Override
    public void setBbox(Rectangle2D rect) {
        bboxFeature.setBbox(rect);
    }

    @Override
    public void setPositionRelativeToParent(double relativeX, double relativeY) {
        bboxFeature.setPositionRelativeToParent(relativeX, relativeY);
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

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        bboxFeature.propertyChange(propertyChangeEvent);
    }

    @Override
    public void registerBboxToPropertySender(HasPropertyChangeListener listener) {
        bboxFeature.registerBboxToPropertySender(listener);
    }

    @Override
    public Optional<Color> getStrokeColor() {
        return style.getStrokeColor();
    }

    @Override
    public void setStrokeColor(Color c) {
        style.setStrokeColor(c);
    }

    @Override
    public OptionalDouble getStrokeWidth() {
        return style.getStrokeWidth();
    }

    @Override
    public void setStrokeWidth(double width) {
        style.setStrokeWidth(width);
    }

    @Override
    public Optional<FillRule> getFillRule() {
        return style.getFillRule();
    }

    @Override
    public void setFillRule(FillRule fillRule) {
        style.setFillRule(fillRule);
    }

    @Override
    public Optional<FontWeight> getFontWeight() {
        return style.getFontWeight();
    }

    @Override
    public void setFontWeight(FontWeight fontWeight) {
        style.setFontWeight(fontWeight);
    }

    @Override
    public Optional<FontFamily> getFontFamily() {
        return style.getFontFamily();
    }

    @Override
    public void setFontFamily(FontFamily fontFamily) {
        style.setFontFamily(fontFamily);
    }

    @Override
    public Optional<FontStyle> getFontStyle() {
        return style.getFontStyle();
    }

    @Override
    public void setFontStyle(FontStyle fontStyle) {
        style.setFontStyle(fontStyle);
    }

    @Override
    public Optional<HTextAnchor> getHTextAnchor() {
        return style.getHTextAnchor();
    }

    @Override
    public void setHTextAnchor(HTextAnchor hTextAnchor) {
        style.setHTextAnchor(hTextAnchor);
    }

    @Override
    public Optional<VTextAnchor> getVTextAnchor() {
        return style.getVTextAnchor();
    }

    @Override
    public void setVTextAnchor(VTextAnchor vTextAnchor) {
        style.setVTextAnchor(vTextAnchor);
    }

    @Override
    public OptionalDouble getFontSize() {
        return style.getFontSize();
    }

    @Override
    public void setFontSize(double fontSize) {
        style.setFontSize(fontSize);
    }

    @Override
    public Optional<BackgroundType> getBackgroundType() {
        return style.getBackgroundType();
    }

    @Override
    public void setBackgroundType(BackgroundType backgroundType) {
        style.setBackgroundType(backgroundType);
    }

    @Override
    public Optional<Color> getBackgroundColor() {
        return style.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(Color color) {
        style.setBackgroundColor(color);
    }

    @Override
    public Optional<LinearGradient> getBackgroundLinearGradient() {
        return style.getBackgroundLinearGradient();
    }

    @Override
    public void setBackgroundLinearGradient(LinearGradient linearGradient) {
        style.setBackgroundLinearGradient(linearGradient);
    }
}