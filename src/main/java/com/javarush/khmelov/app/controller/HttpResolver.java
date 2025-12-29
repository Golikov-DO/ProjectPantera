package com.javarush.khmelov.app.controller;

import com.javarush.khmelov.app.cmd.Command;
import com.javarush.khmelov.app.config.Winter;
import jakarta.servlet.http.HttpServletRequest;

public class HttpResolver {

    public Command resolve(HttpServletRequest req) {
        try {
            // /edit-user?id=18#ok
            String uri = req.getRequestURI();
            uri=uri.equals("/") ? "/start-page" : uri;
            String cmdKebabName = uri.split("[/?#]")[1];
            String simpleName = convertKebabStyleToCamelCase(cmdKebabName);
            String packageName = Command.class.getPackageName();
            String fqName=packageName+"."+simpleName;
            Class<?> cmdClass = Class.forName(fqName);
            return (Command) Winter.find(cmdClass);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static String convertKebabStyleToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }
        return result.toString();
    }
}
