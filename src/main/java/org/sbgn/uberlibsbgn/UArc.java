package org.sbgn.uberlibsbgn;

import org.sbgn.ArcClazz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UArc extends USBGNEntity {

    private String id;
    private UArcClass uArcClass;

    private AbstractUGlyph source;
    private AbstractUGlyph target;

    private Point2D start, end;
    private List<Point2D> steps;

    final Logger logger = LoggerFactory.getLogger(UArc.class);

    private UArc(String clazz) {
        this.id = UUID.randomUUID().toString();
        this.uArcClass = UArcClass.fromArcClazz(ArcClazz.fromClazz(clazz));
    }

    public UArc(String clazz, AbstractUGlyph from, AbstractUGlyph to ) {
        this(clazz);
        this.source = from;
        this.target = to;
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
}
