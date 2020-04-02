package org.sbgn.uberlibsbgn;

import org.sbgn.ArcClazz;
import org.sbgn.uberlibsbgn.features.ArcFeature;
import org.sbgn.uberlibsbgn.features.HasPropertyChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public class UArc extends USBGNEntity implements HasPropertyChangeListener {

    private ArcClazz uArcClass;

    private AbstractUGlyph source;
    private AbstractUGlyph target;

    private Point2D start, end;
    private List<Point2D> steps;

    private final PropertyChangeSupport pcs;

    final Logger logger = LoggerFactory.getLogger(UArc.class);

    private UArc(ArcClazz clazz) {
        super();
        this.uArcClass = clazz;
        this.pcs = new PropertyChangeSupport(this);
    }

    // should not be public, TODO try to solve
    public UArc(ArcClazz clazz, AbstractUGlyph from, AbstractUGlyph to ) {
        this(clazz);

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
}
