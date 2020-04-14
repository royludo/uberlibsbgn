package features;

import org.junit.jupiter.api.Test;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.features.BboxFeature;
import org.sbgn.uberlibsbgn.features.BboxFeatureImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class BboxTest {

    UMap map = new UMap(Language.PD);
    Macromolecule m = map.getFactory().macromolecule().build();

    @Test
    void defaultCoordsShouldBeZeros() {
        assertThat(m.getBbox().getMinX()).isEqualTo(0);
        assertThat(m.getBbox().getMinY()).isEqualTo(0);
    }
}
