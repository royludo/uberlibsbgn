package features;

import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.Complex;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.UMap;
import org.sbgn.uberlibsbgn.features.BboxFeature;
import org.sbgn.uberlibsbgn.features.BboxFeatureImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BboxTest {

    UMap map = new UMap(Language.PD);
    Macromolecule m = map.getFactory().macromolecule();
    Complex c = map.getFactory().complex();
    Rectangle2D differentNumbers = new Rectangle2D(1,2,3,4);
    Rectangle2D square10At10_10 = new Rectangle2D(10, 10, 10,10);
    Rectangle2D rect20_10at30_30 = new Rectangle2D(30, 30, 20, 10);

    @Nested
    class BasicGlyph {

        @Test
        void defaultCoordsShouldBeZeros() {
            assertThat(m.getBbox().getMinX()).isEqualTo(0);
            assertThat(m.getBbox().getMinY()).isEqualTo(0);
        }

        @Test
        void settersShouldSetCorrectly() {
            m.setBbox(differentNumbers);
            assertThat(m.getBbox().getMinX()).isEqualTo(1);
            assertThat(m.getBbox().getMinY()).isEqualTo(2);
            assertThat(m.getBbox().getWidth()).isEqualTo(3);
            assertThat(m.getBbox().getHeight()).isEqualTo(4);
        }

        @Test
        void setPositionShouldOnlyChangePosition() {
            m.setPosition(4, 5);
            assertThat(m.getBbox().getMinX()).isEqualTo(4);
            assertThat(m.getBbox().getMinY()).isEqualTo(5);
            assertThat(m.getBbox().getWidth()).isEqualTo(0);
            assertThat(m.getBbox().getHeight()).isEqualTo(0);
        }

        @Test
        void relativePositionShouldWork() {
            c.setBbox(rect20_10at30_30);
            m.setPositionRelativeToGlyph(c, 2, 3);
            assertThat(m.getBbox().getMinX()).isEqualTo(32);
            assertThat(m.getBbox().getMinY()).isEqualTo(33);
        }

        @Test
        void setPositionRelativeToParentWhenParentExistShouldWork() {
            c.setBbox(rect20_10at30_30);
            c.add(m);
            m.setPositionRelativeToParent(2, 3);
            assertThat(m.getBbox().getMinX()).isEqualTo(32);
            assertThat(m.getBbox().getMinY()).isEqualTo(33);
        }

        @Test
        void setPositionRelativeToParentShouldThrowExceptionWhenAtMapRoot() {
            assertThatThrownBy(() -> m.setPositionRelativeToParent(2, 3))
                    .isInstanceOf(RuntimeException.class);
        }

    }

    @Nested
    class Label {

        @Test
        void setPositionRelativeToParentForLabelShouldWork() {
            m.setBbox(square10At10_10);
            m.setLabelBboxPositionRelativeToParent(2,3);
            assertThat(m.getLabelBbox().getMinX()).isEqualTo(12);
            assertThat(m.getLabelBbox().getMinY()).isEqualTo(13);
        }

    }



}
