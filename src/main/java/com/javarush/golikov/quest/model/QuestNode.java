package com.javarush.golikov.quest.model;

import java.util.List;

public record QuestNode(String id, String text, List<Choice> choices) {
}
