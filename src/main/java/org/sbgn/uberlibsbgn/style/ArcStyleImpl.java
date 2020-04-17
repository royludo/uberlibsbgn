package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.OptionalDouble;

public class ArcStyleImpl implements ArcStyle {

    private Color strokeColor;
    private double strokeWidth;


    @Override
    public Optional<Color> getStrokeColor() {
        return Optional.ofNullable(this.strokeColor);
    }

    @Override
    public void setStrokeColor(Color c) {
        this.strokeColor = c;
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
