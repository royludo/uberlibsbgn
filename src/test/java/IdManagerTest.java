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
        public void uuidStrategyShouldGiveInvalidUUIDAsIs() {
            IdManager im = new IdManager(IdManager.IdStrategy.UUID);
            String _uuid = im.getNewId();
            assertThat(_uuid).startsWith("_");
            assertThatThrownBy(() -> {
                UUID uuid3 = UUID.fromString(_uuid);
            }).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        public void uuidStrategyShouldGiveValidUuidWhen1stCharIsStripped() {
            IdManager im = new IdManager(IdManager.IdStrategy.UUID);
            String _uuid = im.getNewId();
            assertThat(_uuid).startsWith("_");
            String uuid = _uuid.substring(1);
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
            assertThat(id).isEqualTo("_1");
        }

        @Test
        public void incrementStrategyShouldStartAt1AndIncrement() {
            String id = im.getNewId();
            String id2 = im.getNewId();
            String id3 = im.getNewId();
            assertThat(id2).isEqualTo("_2");
            assertThat(id3).isEqualTo("_3");
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
        public void customIdShouldBeXMLValid() {
            String id = im.useCustomId("26");
            assertThat(id).startsWith("_");
        }

        @Test
        public void customIdClashWithIncrementIsAvoided() {
            String id1 = im.getNewId();
            String custom = im.useCustomId("2");
            String id3 = im.getNewId();
            assertThat(id1).isEqualTo("_1");
            assertThat(custom).isEqualTo("_2");
            assertThat(id3).isEqualTo("_3");
        }

        @Test
        public void dontAddUnderscoreIfCustomIdAlreadyHasOne() {
            String id = im.useCustomId("_ID");
            assertThat(id).isEqualTo("_ID");
        }


    }

}
