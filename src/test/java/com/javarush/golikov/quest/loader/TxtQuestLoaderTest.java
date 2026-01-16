package com.javarush.golikov.quest.loader;

import com.javarush.golikov.quest.model.Quest;
import com.javarush.golikov.quest.model.QuestNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TxtQuestLoaderTest {
    @Test
    @DisplayName("Test load valid quest returns Quest with start node")
    void testLoadValidQuestReturnsQuestWithStartNode() throws Exception {

        String data = """
            ? start
            ? You are on a farm
            + Open barn -> win
            - Run away -> lose

            ? win
            ? You win!

            ? lose
            ? You lose!
            """;

        Quest quest = TxtQuestLoader.load(
                new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                "farm"
        );

        assertNotNull(quest);
        assertEquals("farm", quest.getId());
        assertEquals("Farm Quest", quest.getTitle());

        QuestNode start = quest.getStart();
        assertNotNull(start);
        assertEquals(2, start.choices().size());
    }

    @Test
    @DisplayName("Test load quest without start node throws RuntimeException")
    void testLoadQuestWithoutStartNodeThrowsException() {

        String data = """
            ? intro
            ? No start here
            """;

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> TxtQuestLoader.load(
                        new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                        "bad"
                )
        );

        assertEquals("Quest has no 'start' node", ex.getMessage());
    }

    @Test
    @DisplayName("Test node text without node id throws RuntimeException")
    void testNodeTextWithoutNodeIdThrowsException() {

        String data = """
            ? Just text without id
            """;

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> TxtQuestLoader.load(
                        new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                        "bad"
                )
        );

        assertTrue(ex.getMessage().startsWith("Node text without node id"));
    }

    @Test
    @DisplayName("Test choice without node id throws RuntimeException")
    void testChoiceWithoutNodeIdThrowsException() {

        String data = """
            + Choice without node -> next
            """;

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> TxtQuestLoader.load(
                        new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                        "bad"
                )
        );

        assertTrue(ex.getMessage().startsWith("Choice without node id"));
    }

    @Test
    @DisplayName("Test invalid choice format throws RuntimeException")
    void testInvalidChoiceFormatThrowsException() {

        String data = """
            ? start
            ? Question
            + Invalid format
            """;

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> TxtQuestLoader.load(
                        new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                        "bad"
                )
        );

        assertTrue(ex.getMessage().startsWith("Invalid choice format"));
    }

//    @Test
//    @DisplayName("Test positive and negative choices parsed correctly")
//    void testPositiveAndNegativeChoicesParsedCorrectly() throws Exception {
//
//        String data = """
//            ? start
//            ? Question
//            + Yes -> win
//            - No -> lose
//
//            ? win
//            ? You win
//
//            ? lose
//            ? You lose
//            """;
//
//        Quest quest = TxtQuestLoader.load(
//                new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
//                "test",
//                "Test Quest"
//        );
//
//        QuestNode start = quest.getStart();
//
//        assertEquals(2, start.choices().size());
//        assertTrue(start.choices().get(0).positive());
//        assertFalse(start.choices().get(1).positive());
//    }
}