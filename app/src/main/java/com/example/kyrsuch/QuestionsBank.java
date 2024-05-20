package com.example.kyrsuch;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> mathQuestions () {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Чему равно значение выражения 3 × (4 + 2) - 8?",
                "14", "16", "18", "20", "20", "" );
        final QuestionsList question2 = new QuestionsList("Какое число является результатом деления 42 на 6?",
                "5", "6", "7", "8", "7", "" );
        final QuestionsList question3 = new QuestionsList("Чему равно значение 5^2 - 3 × 4?",
                "13", "15", "17", "19", "17", "" );

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);

        return questionsList;
    }

    private static List<QuestionsList> historyQuestions () {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Чему равно значение выражения 3 × (4 + 2) - 8?",
                "14", "16", "18", "20", "20", "" );
        final QuestionsList question2 = new QuestionsList("Какое число является результатом деления 42 на 6?",
                "5", "6", "7", "8", "7", "" );
        final QuestionsList question3 = new QuestionsList("Чему равно значение 5^2 - 3 × 4?",
                "13", "15", "17", "19", "17", "" );

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);

        return questionsList;
    }

    private static List<QuestionsList> itQuestions () {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Чему равно значение выражения 3 × (4 + 2) - 8?",
                "14", "16", "18", "20", "20", "" );
        final QuestionsList question2 = new QuestionsList("Какое число является результатом деления 42 на 6?",
                "5", "6", "7", "8", "7", "" );
        final QuestionsList question3 = new QuestionsList("Чему равно значение 5^2 - 3 × 4?",
                "13", "15", "17", "19", "17", "" );

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);

        return questionsList;
    }

    private static List<QuestionsList> sportQuestions () {
        final List<QuestionsList> questionsList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Чему равно значение выражения 3 × (4 + 2) - 8?",
                "14", "16", "18", "20", "20", "" );
        final QuestionsList question2 = new QuestionsList("Какое число является результатом деления 42 на 6?",
                "5", "6", "7", "8", "7", "" );
        final QuestionsList question3 = new QuestionsList("Чему равно значение 5^2 - 3 × 4?",
                "13", "15", "17", "19", "17", "" );

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);

        return questionsList;
    }

    public static List<QuestionsList> getQuestions (String selectedTopicName) {
        switch (selectedTopicName) {
            case "math": return mathQuestions();
            case "history": return historyQuestions();
            case "it": return itQuestions();
            case "sport": return sportQuestions();
        }
        return null;
    }
}
