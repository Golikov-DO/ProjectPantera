package com.javarush.golikov.quest.repository;

import com.javarush.golikov.quest.model.Quest;
import com.javarush.golikov.quest.loader.TxtQuestLoader;
import jakarta.servlet.ServletContext;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuestRepository {

    private static final Map<String, Quest> quests = new HashMap<>();

    public static Collection<Quest> all() {
        return quests.values();
    }

    public static Quest get(String id) {
        return quests.get(id);
    }

    public static void clear() {
        quests.clear();
    }

    public static void loadTxt(InputStream in, String id, String title) throws Exception {
        Quest quest = TxtQuestLoader.load(in, id, title);
        quests.put(id, quest);
    }

    public static void loadAll(ServletContext ctx) {
        try {
            clear();

            Set<String> files = ctx.getResourcePaths("/WEB-INF/classes/quests/");

            if (files == null) return;

            for (String path : files) {
                if (!path.endsWith(".txt")) continue;

                String fileName = path.substring(path.lastIndexOf("/") + 1);
                String questId = fileName.replace(".txt", "");
                String title = questId.substring(0,1).toUpperCase() + questId.substring(1);

                InputStream in = ctx.getResourceAsStream(path);
                loadTxt(in, questId, title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(String id) {
        quests.remove(id);
    }
}

