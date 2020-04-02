package org.sbgn.uberlibsbgn.features;

import org.sbgn.uberlibsbgn.AbstractUGlyph;
import org.sbgn.uberlibsbgn.UnitOfInfo;

import java.util.List;
import java.util.stream.Stream;

public interface UnitOfInfoParentFeature {

    AbstractUGlyph addUnitOfInfo(String key, String value);

    UnitOfInfo getUnitOfInfoWithKey(String key);

    Stream<UnitOfInfo> getUnitOfInfoStream();

    List<UnitOfInfo> getUnitsOfInfoWithRegex(String regex);

    boolean hasUnitOfInfo();

    boolean hasUnitOfInfoWithKey(String key);

    boolean hasUnitOfInfoWithRegex(String regex);

    UnitOfInfo removeUnitOfInfoWithKey(String key);

    List<UnitOfInfo> removeUnitsOfInfoWithRegex(String regex);
}
