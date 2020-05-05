package org.sbgn.uberlibsbgn;

import javafx.geometry.Rectangle2D;
import org.sbgn.ArcClazz;
import org.sbgn.bindings.Glyph;
import org.sbgn.bindings.Label;
import org.sbgn.uberlibsbgn.features.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Stream;

public class Macromolecule extends EPN implements MultimerFeature, LabelFeature, ArcFeature, UnitOfInfoParentFeature,
        CloneMarkerFeature, ComplexIncludible {

    private MultimerFeature multimerFeature;
    private LabelFeature labelFeature;
    private ArcFeature arcFeature;
    private UnitOfInfoParentFeature unitOfInfoParentFeature;
    private CloneMarkerFeature cloneMarkerFeature;

    final Logger logger = LoggerFactory.getLogger(Macromolecule.class);

    protected Macromolecule(CompositeFeature parent, UMap parentMap, String id) {
        super("macromolecule", parent, parentMap, id);
        logger.trace("Create Macromolecule");
        this.multimerFeature = new MultimerFeatureImpl(this);
        this.labelFeature = new LabelFeatureImpl(this, EventType.LABEL);
        this.arcFeature = new ArcFeatureImpl(this);
        this.unitOfInfoParentFeature = new UnitOfInfoParentFeatureImpl(this);
        this.cloneMarkerFeature = new CloneMarkerFeatureImpl(this);

        // TODO that's fishy
        ((LabelFeatureImpl) this.labelFeature).addlistener(); // this needs to be at the end of constructor
    }

    @Override
    public void setMultimer(boolean isMultimer) {
        this.multimerFeature.setMultimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return this.multimerFeature.isMultimer();
    }

    @Override
    public void setLabel(String label) {
        this.labelFeature.setLabel(label);
    }

    @Override
    public String getLabel() {
        return this.labelFeature.getLabel();
    }

    @Override
    public boolean hasLabel() {
        return labelFeature.hasLabel();
    }

    @Override
    public boolean labelHasBbox() {
        return labelFeature.labelHasBbox();
    }

    @Override
    public Rectangle2D getLabelBbox() {
        return labelFeature.getLabelBbox();
    }

    @Override
    public void setLabelBbox(Rectangle2D rect) {
        labelFeature.setLabelBbox(rect);
    }

    @Override
    public void setLabelBboxPosition(double x, double y) {
        labelFeature.setLabelBboxPosition(x, y);
    }

    @Override
    public void setLabelBboxPositionRelativeToGlyph(BboxFeature glyph, double relativeX, double relativeY) {
        labelFeature.setLabelBboxPositionRelativeToGlyph(glyph, relativeX, relativeY);
    }

    @Override
    public void setLabelBboxPositionRelativeToParent(double relativeX, double relativeY) {
        labelFeature.setLabelBboxPositionRelativeToParent(relativeX, relativeY);
    }

    @Override
    public void parseLibSbgnLabel(Label sbgnLabel) {
        labelFeature.parseLibSbgnLabel(sbgnLabel);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        labelFeature.addPropertyChangeListener(listener);
        multimerFeature.addPropertyChangeListener(listener);
        cloneMarkerFeature.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        labelFeature.removePropertyChangeListener(listener);
        multimerFeature.removePropertyChangeListener(listener);
        cloneMarkerFeature.removePropertyChangeListener(listener);
    }

    @Override
    public List<UArc> getArcs() {
        return arcFeature.getArcs();
    }

    @Override
    public List<UArc> getIncomingArcs() {
        return arcFeature.getIncomingArcs();
    }

    @Override
    public List<UArc> getOutgoingArcs() {
        return arcFeature.getOutgoingArcs();
    }

    @Override
    public UArc addArcTo(ArcClazz arcClazz, AbstractUGlyph glyph) {
        return arcFeature.addArcTo(arcClazz, glyph);
    }

    @Override
    public UArc addArcFrom(ArcClazz arcClazz, AbstractUGlyph glyph) {
        return arcFeature.addArcFrom(arcClazz, glyph);
    }

    @Override
    public void addIncomingArc(UArc arc) {
        arcFeature.addIncomingArc(arc);
    }

    @Override
    public void addOutgoingArc(UArc arc) {
        arcFeature.addOutgoingArc(arc);
    }

    @Override
    public boolean removeArc(UArc arc) {
        return arcFeature.removeArc(arc);
    }

    @Override
    public void addArcChangeListener(ArcChangeListener listener) {
        arcFeature.addArcChangeListener(listener);
    }

    @Override
    public void removeArcChangeListener(ArcChangeListener listener) {
        arcFeature.removeArcChangeListener(listener);
    }

    @Override
    public AbstractUGlyph addUnitOfInfo(String key, String value) {
        return unitOfInfoParentFeature.addUnitOfInfo(key, value);
    }

    @Override
    public UnitOfInfo getUnitOfInfoWithKey(String key) {
        return unitOfInfoParentFeature.getUnitOfInfoWithKey(key);
    }

    @Override
    public Stream<UnitOfInfo> getUnitOfInfoStream() {
        return unitOfInfoParentFeature.getUnitOfInfoStream();
    }

    @Override
    public List<UnitOfInfo> getUnitsOfInfoWithRegex(String regex) {
        return unitOfInfoParentFeature.getUnitsOfInfoWithRegex(regex);
    }

    @Override
    public boolean hasUnitOfInfo() {
        return unitOfInfoParentFeature.hasUnitOfInfo();
    }

    @Override
    public boolean hasUnitOfInfoWithKey(String key) {
        return unitOfInfoParentFeature.hasUnitOfInfoWithKey(key);
    }

    @Override
    public boolean hasUnitOfInfoWithRegex(String regex) {
        return unitOfInfoParentFeature.hasUnitOfInfoWithRegex(regex);
    }

    @Override
    public UnitOfInfo removeUnitOfInfoWithKey(String key) {
        return unitOfInfoParentFeature.removeUnitOfInfoWithKey(key);
    }

    @Override
    public List<UnitOfInfo> removeUnitsOfInfoWithRegex(String regex) {
        return unitOfInfoParentFeature.removeUnitsOfInfoWithRegex(regex);
    }

    @Override
    public void parseLibSBGNGlyph(Glyph sbgnGlyph) {
        // TODO
    }

    @Override
    public void setCloneMarker(boolean isCLoneMarker) {
        cloneMarkerFeature.setCloneMarker(isCLoneMarker);
    }

    @Override
    public boolean hasCloneMarker() {
        return cloneMarkerFeature.hasCloneMarker();
    }

    @Override
    public LabelFeature getCloneLabel() {
        return cloneMarkerFeature.getCloneLabel();
    }

    @Override
    public boolean hasCloneLabel() {
        return cloneMarkerFeature.hasCloneLabel();
    }

    @Override
    public void parseLibSBGNCloneMarker(Glyph.Clone cloneElement) {
        this.cloneMarkerFeature.parseLibSBGNCloneMarker(cloneElement);
    }
}
