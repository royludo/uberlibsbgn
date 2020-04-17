package org.sbgn.uberlibsbgn.style;

import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.OptionalDouble;

public interface StrokeStyle {

    Optional<Color> getStrokeColor();
    void setStrokeColor(Color c);
    OptionalDouble getStrokeWidth();
    void setStrokeWidth(double width);
}
