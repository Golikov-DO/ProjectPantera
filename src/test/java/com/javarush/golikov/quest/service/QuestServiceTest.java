package com.javarush.golikov.quest.service;

import com.javarush.golikov.quest.model.QuestSession;
import com.javarush.golikov.quest.model.QuestNode;
import com.javarush.golikov.quest.repository.QuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {

    private QuestService questService;

    @BeforeEach
    void setUp() throws Exception {
        QuestRepository.clear();
        questService = new QuestService();

        InputStream in =
                getClass().getResourceAsStream("/quests/farm.txt");

        QuestRepository.loadTxt(in, "farm");
    }

    @Test
    @DisplayName("Test startQuest creates session with start node")
    void testStartQuestCreatesSessionWithStartNode() {

        QuestSession session = questService.startQuest("farm");

        assertEquals("farm", session.getQuestId());
        assertEquals("start", session.getCurrentNode());
    }

    @Test
    @DisplayName("Test applyChoice changes current node and adds history step")
    void testApplyChoiceChangesCurrentNodeAndAddsHistoryStep() {

        QuestSession session = questService.startQuest("farm");

        questService.applyChoice(session, "barn", "Да");

        assertEquals("barn", session.getCurrentNode());
        assertEquals(1, session.getHistory().size());
    }

    @Test
    @DisplayName("Test isFinalNode returns false for non-final node")
    void testIsFinalNodeReturnsFalseForNonFinalNode() {

        QuestSession session = questService.startQuest("farm");
        QuestNode node = questService.getCurrentNode(session);

        assertFalse(questService.isFinalNode(node));
    }
}