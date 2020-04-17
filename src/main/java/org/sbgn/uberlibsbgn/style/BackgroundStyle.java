package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;

import java.util.Optional;

public interface BackgroundStyle {

    Optional<BackgroundType> getBackgroundType();
    void setBackgroundType(BackgroundType backgroundType);

    Optional<Color> getBackgroundColor();
    void setBackgroundColor(Color color);

    Optional<LinearGradient> getBackgroundLinearGradient();
    void setBackgroundLinearGradient(LinearGradient linearGradient);
}
