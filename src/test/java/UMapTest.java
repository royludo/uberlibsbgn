import org.junit.jupiter.api.Test;
import org.sbgn.Language;
import org.sbgn.uberlibsbgn.Macromolecule;
import org.sbgn.uberlibsbgn.UMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UMapTest {

    UMap map = new UMap(Language.PD);

    @Test
    public void creatingERMapShouldThrowException() {
        assertThatThrownBy(() -> new UMap(Language.ER)).isInstanceOf(UnsupportedOperationException.class);
    }



}
