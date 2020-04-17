package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;

import java.util.Optional;

public class MapStyleImpl implements MapStyle {

    private BackgroundType backgroundType;
    private Color backgroundColor;
    private LinearGradient backgroundLinearGradient;


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
}
