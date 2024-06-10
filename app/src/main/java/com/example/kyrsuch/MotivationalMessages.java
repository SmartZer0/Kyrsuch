package com.example.kyrsuch;

import java.util.Random;

public class MotivationalMessages {
    public static final String[] MESSAGES = {
            "Попробуйте новый тест сегодня!",
            "Ваши знания ждут проверки!",
            "Тесты ждут вас! Проходите их сейчас!",
            "Улучшите свои навыки с новыми тестами!",
            "Каждый день - новая возможность пройти тест!"
    };

    public static String getRandomMessage() {
        int index = new Random().nextInt(MESSAGES.length);
        return MESSAGES[index];
    }
}
