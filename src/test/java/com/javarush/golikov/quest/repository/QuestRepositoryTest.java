package com.javarush.golikov.quest.repository;

import com.javarush.golikov.quest.model.Quest;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestRepositoryTest {

    @BeforeEach
    void clearRepositoryBeforeEachTest() {
        QuestRepository.clear();
    }

    @Test
    @DisplayName("Test loadTxt stores quest and get returns it")
    void testLoadTxtStoresQuestAndGetReturnsIt() throws Exception {

        String data = """
            ? start
            ? Question
            + Yes -> win

            ? win
            ? Win
            """;

        QuestRepository.loadTxt(
                new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                "test"
        );

        Quest quest = QuestRepository.get("test");

        assertNotNull(quest);
        assertEquals("test", quest.getId());
        assertEquals("Test Quest", quest.getTitle());
    }

//    @Test
//    @DisplayName("Test all returns all loaded quests")
//    void testAllReturnsAllLoadedQuests() throws Exception {
//
//        QuestRepository.loadTxt(
//                new ByteArrayInputStream("""
//                    ? start
//                    ? Q
//                    """.getBytes()),
//                "q1",
//                "Quest 1"
//        );
//
//        QuestRepository.loadTxt(
//                new ByteArrayInputStream("""
//                    ? start
//                    ? Q
//                    """.getBytes()),
//                "q2",
//                "Quest 2"
//        );
//
//        assertEquals(2, QuestRepository.all().size());
//    }
//
//    @Test
//    @DisplayName("Test remove deletes quest by id")
//    void testRemoveDeletesQuestById() throws Exception {
//
//        QuestRepository.loadTxt(
//                new ByteArrayInputStream("""
//                    ? start
//                    ? Q
//                    """.getBytes()),
//                "q1",
//                "Quest 1"
//        );
//
//        QuestRepository.remove("q1");
//
//        assertNull(QuestRepository.get("q1"));
//        assertTrue(QuestRepository.all().isEmpty());
//    }
//
//    @Test
//    @DisplayName("Test clear removes all quests")
//    void testClearRemovesAllQuests() throws Exception {
//
//        QuestRepository.loadTxt(
//                new ByteArrayInputStream("""
//                    ? start
//                    ? Q
//                    """.getBytes()),
//                "q1",
//                "Quest 1"
//        );
//
//        QuestRepository.clear();
//
//        assertTrue(QuestRepository.all().isEmpty());
//    }

    @Test
    @DisplayName("Test get returns null for unknown quest id")
    void testGetReturnsNullForUnknownId() {

        assertNull(QuestRepository.get("unknown"));
    }

    @Test
    @DisplayName("Test loadAll loads quests from servlet context")
    void testLoadAllLoadsQuestsFromServletContext() {

        ServletContext ctx = mock(ServletContext.class);

        when(ctx.getResourcePaths("/WEB-INF/classes/quests/"))
                .thenReturn(Set.of(
                        "/WEB-INF/classes/quests/test1.txt",
                        "/WEB-INF/classes/quests/test2.txt"
                ));

        when(ctx.getResourceAsStream(anyString()))
                .thenAnswer(invocation ->
                        new ByteArrayInputStream("""
                        ? start
                        ? Question
                        """.getBytes(StandardCharsets.UTF_8))
                );

        QuestRepository.loadAll(ctx);

        assertEquals(2, QuestRepository.all().size());
    }
}