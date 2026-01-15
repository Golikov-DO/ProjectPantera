package com.javarush.golikov.quest.model;

import com.javarush.golikov.quest.auth.Role;

public record User(String login, String password, Role role) {
}