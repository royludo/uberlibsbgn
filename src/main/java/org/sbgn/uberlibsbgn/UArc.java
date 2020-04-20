package org.sbgn.uberlibsbgn;

import javafx.scene.paint.Color;
import org.sbgn.ArcClazz;
import org.sbgn.uberlibsbgn.features.ArcFeature;
import org.sbgn.uberlibsbgn.features.HasPropertyChangeListener;
import org.sbgn.uberlibsbgn.style.ArcStyle;
import org.sbgn.uberlibsbgn.style.ArcStyleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;


public class UArc extends USBGNEntity implements ArcStyle, HasPropertyChangeListener {

    private ArcClazz uArcClass;

    private AbstractUGlyph source;
    private AbstractUGlyph target;

    private Point2D start, end;
    private List<Point2D> steps;

    private ArcStyle style;

    private UMap parentMap;

    private final PropertyChangeSupport pcs;

    final Logger logger = LoggerFactory.getLogger(UArc.class);

    private UArc(ArcClazz clazz, UMap parentMap) {
        super();
        this.uArcClass = clazz;
        this.style = new ArcStyleImpl();
        this.pcs = new PropertyChangeSupport(this);
        this.parentMap = parentMap;
    }

    // should not be public, TODO try to solve
    public UArc(ArcClazz clazz, AbstractUGlyph from, AbstractUGlyph to) {
        this(clazz, from.getMap());

        // TODO add some predicate to check permission for glyph to get arcs
        ArcFeature fromAsArcFeature, toAsArcFeature;
        if(from instanceof ArcFeature) {
            fromAsArcFeature = (ArcFeature) from;
        }
        else {
            throw new IllegalArgumentException("Glyph "+from.getId()+" cannot have arc outgoing");
        }

        if(to instanceof ArcFeature) {
            toAsArcFeature = (ArcFeature) from;
        }
        else {
            throw new IllegalArgumentException("Glyph "+to.getId()+" cannot have arc incoming");
        }

        this.source = from;
        //fromAsArcFeature.addOutgoingArc(this);
        this.target = to;
        //toAsArcFeature.addIncomingArc(this);
    }

    public List<Point2D> getAllPoints() {
        ArrayList<Point2D> result = new ArrayList<>();
        result.add(start);
        result.addAll(steps);
        result.add(end);
        return result;
    }

    @Override
    public UMap getMap() {
        return this.parentMap;
    }

    public AbstractUGlyph getSource() {
        return source;
    }

    public AbstractUGlyph getTarget() {
        return target;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
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
}
