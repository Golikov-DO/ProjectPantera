package com.javarush.golikov.quest.service;

import com.javarush.golikov.quest.model.User;
import com.javarush.golikov.quest.model.Quest;
import com.javarush.golikov.quest.repository.UserRepository;
import com.javarush.golikov.quest.repository.QuestRepository;

import java.io.InputStream;
import java.util.Collection;

public class AdminService {

    /* ===== USERS ===== */

    public Collection<User> getAllUsers() {
        return UserRepository.all();
    }

    public void saveUser(User user) {
        UserRepository.save(user);
    }

    public void deleteUser(String login) {
        UserRepository.delete(login);
    }

    /* ===== QUESTS ===== */

    public Collection<Quest> getAllQuests() {
        return QuestRepository.all();
    }

    public void loadQuestFromTxt(String id, String title, InputStream is) {
        try {
            QuestRepository.loadTxt(is, id, title);
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка загрузки квеста: " + id, e);
        }
    }

    public void deleteQuest(String id) {
        QuestRepository.remove(id);
    }

    public User findUser(String login) {
        return UserRepository.find(login);
    }
}