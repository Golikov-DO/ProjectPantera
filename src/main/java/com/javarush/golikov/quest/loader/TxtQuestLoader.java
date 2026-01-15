package com.javarush.golikov.quest.loader;

import com.javarush.golikov.quest.model.Choice;
import com.javarush.golikov.quest.model.Quest;
import com.javarush.golikov.quest.model.QuestNode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TxtQuestLoader {

    public static Quest load(InputStream in, String questId, String title) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(in)
        );

        Map<String, QuestNode> nodes = new LinkedHashMap<>();

        String line;
        String currentId = null;
        String currentText = null;
        List<Choice> choices = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // ===== ОБЪЯВЛЕНИЕ УЗЛА =====
            if (line.startsWith("? ") && !line.substring(2).contains(" ")) {

                // сохраняем предыдущий узел
                if (currentId != null) {
                    nodes.put(currentId,
                            new QuestNode(currentId, currentText, choices));
                }

                currentId = line.substring(2).trim();
                currentText = null;
                choices = new ArrayList<>();
            }

            // ===== ТЕКСТ УЗЛА =====
            else if (line.startsWith("?")) {

                if (currentId == null) {
                    throw new RuntimeException(
                            "Node text without node id: " + line);
                }

                currentText = line.substring(1).trim();
            }

            // ===== ВАРИАНТЫ =====
            else if (line.startsWith("+") || line.startsWith("-")) {

                if (currentId == null) {
                    throw new RuntimeException(
                            "Choice without node id: " + line);
                }

                String body = line.substring(1).trim();
                String[] parts = body.split("->");

                if (parts.length != 2) {
                    throw new RuntimeException(
                            "Invalid choice format: " + line);
                }

                String text = parts[0].trim();
                String next = parts[1].trim();
                boolean positive = line.startsWith("+");

                choices.add(new Choice(text, next, positive));
            }
        }

        // сохраняем последний узел
        if (currentId != null) {
            nodes.put(currentId,
                    new QuestNode(currentId, currentText, choices));
        }

        // проверка обязательного старта
        if (!nodes.containsKey("start")) {
            throw new RuntimeException("Quest has no 'start' node");
        }

        return new Quest(questId, title, "start", nodes);
    }
}