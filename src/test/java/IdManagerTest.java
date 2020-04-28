import org.junit.Before;
import org.junit.jupiter.api.*;
import org.sbgn.uberlibsbgn.IdManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

public class IdManagerTest {


    @Nested
    class UUIDSrategy {

        @Test
        public void uuidStrategyShouldGiveValidUuid() {
            IdManager im = new IdManager(IdManager.IdStrategy.UUID);
            String uuid = im.getNewId();
            assertThatCode(() -> {
                UUID uuid3 = UUID.fromString(uuid);
            }).doesNotThrowAnyException();
        }
    }

    @Nested
    class IncrementStrategy {

        IdManager im = new IdManager(IdManager.IdStrategy.SIMPLE_INCREMENT);

        @Test
        public void incrementStrategyShouldStartAt1() {
            String id = im.getNewId();
            assertThat(id).isEqualTo("1");
        }

        @Test
        public void incrementStrategyShouldStartAt1AndIncrement() {
            String id = im.getNewId();
            String id2 = im.getNewId();
            String id3 = im.getNewId();
            assertThat(id2).isEqualTo("2");
            assertThat(id3).isEqualTo("3");
        }
    }

    @Nested
    class CustomIds {

        IdManager im = new IdManager(IdManager.IdStrategy.SIMPLE_INCREMENT);

        @Test
        public void uniquenessOfIdShouldBeEnsured() {
            String id1 = im.useCustomId("test");
            assertThatThrownBy(() -> im.useCustomId("test")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        public void customIdShouldNotBeEmpty() {
            assertThatThrownBy(() -> im.useCustomId("")).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> im.useCustomId("    ")).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        public void customIdClashWithIncrementIsAvoided() {
            String id1 = im.getNewId();
            String custom = im.useCustomId("2");
            String id3 = im.getNewId();
            assertThat(id1).isEqualTo("1");
            assertThat(custom).isEqualTo("2");
            assertThat(id3).isEqualTo("3");
        }

    }

}
