package com.serverlessquiz.persistentquestionservice.service;

import com.serverlessquiz.persistentquestionservice.dto.QuestionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PersistentQuestionService {

    private static Map<String, String> getColorMap() {
        HashMap<String, String> colors = new HashMap<>();
        colors.put("#000080", "navy");
        colors.put("#00008b", "darkblue");
        colors.put("#0000cd", "mediumblue");
        colors.put("#0000FF", "blue");
        colors.put("#006400", "darkgreen");
        colors.put("#008000", "green");
        colors.put("#008080", "teal");
        colors.put("#008b8b", "darkcyan");
        colors.put("#00bfff", "deepskyblue");
        colors.put("#00ced1", "darkturquoise");
        colors.put("#00fa9a", "mediumspringgreen");
        colors.put("#00FF00", "lime");
        colors.put("#00ff7f", "springgreen");
        colors.put("#00ffff", "cyan");
        colors.put("#191970", "midnightblue");
        colors.put("#1e90ff", "dodgerblue");
        colors.put("#20b2aa", "lightseagreen");
        colors.put("#2e8b57", "seagreen");
        colors.put("#2f4f4f", "darkslategray");
        colors.put("#32cd32", "limegreen");
        colors.put("#3cb371", "mediumseagreen");
        colors.put("#40e0d0", "turquoise");
        colors.put("#4169e1", "royalblue");
        colors.put("#4682b4", "steelblue");
        colors.put("#483d8b", "darkslateblue");
        colors.put("#48d1cc", "mediumturquoise");
        colors.put("#4b0082", "indigo");
        colors.put("#556b2f", "darkolivegreen");
        colors.put("#5f9ea0", "cadetblue");
        colors.put("#6495ed", "cornflowerblue");
        colors.put("#66cdaa", "mediumaquamarine");
        colors.put("#696969", "dimgray");
        colors.put("#6a5acd", "slateblue");
        colors.put("#6b8e23", "olivedrab");
        colors.put("#708090", "slategray");
        colors.put("#778899", "lightslategray");
        colors.put("#7b68ee", "mediumslateblue");
        colors.put("#7cfc00", "lawngreen");
        colors.put("#7fff00", "chartreuse");
        colors.put("#7fffd4", "aquamarine");
        colors.put("#800000", "maroon");
        colors.put("#800080", "purple");
        colors.put("#808000", "olive");
        colors.put("#808080", "gray");
        colors.put("#87ceeb", "skyblue");
        colors.put("#87cefa", "lightskyblue");
        colors.put("#8a2be2", "blueviolet");
        colors.put("#8b0000", "darkred");
        colors.put("#8b008b", "darkmagenta");
        colors.put("#8b4513", "saddlebrown");
        colors.put("#8fbc8f", "darkseagreen");
        colors.put("#90ee90", "lightgreen");
        colors.put("#9370db", "mediumpurple");
        colors.put("#9400d3", "darkviolet");
        colors.put("#98fb98", "palegreen");
        colors.put("#9932cc", "darkorchid");
        colors.put("#9acd32", "yellowgreen");
        colors.put("#a0522d", "sienna");
        colors.put("#a52a2a", "brown");
        colors.put("#a9a9a9", "darkgray");
        colors.put("#add8e6", "lightblue");
        colors.put("#adff2f", "greenyellow");
        colors.put("#afeeee", "paleturquoise");
        colors.put("#b0c4de", "lightsteelblue");
        colors.put("#b0e0e6", "powderblue");
        colors.put("#228B22", "forestgreen");
        return colors;
    }

    public QuestionDto getQuestion() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setColorNames(new ArrayList<>());
        Map<String, String> colorMap = getColorMap();
        List<String> possibleColorCodes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            List<String> keys = new ArrayList<>(colorMap.keySet());
            String randomKey = keys.get(random.nextInt(keys.size()));
            while (possibleColorCodes.contains(randomKey)) {
                randomKey = keys.get(random.nextInt(keys.size()));
            }
            String value = colorMap.get(randomKey);
            possibleColorCodes.add(randomKey);
            questionDto.getColorNames().add(value);
        }
        questionDto.setColorCode(possibleColorCodes.get(random.nextInt(possibleColorCodes.size())));
        return questionDto;
    }

    public boolean getAnswerCorrectness(String colorCode, String inputColorName) {
        String name = getColorMap().get(colorCode);
        return ((null != name) && (name.equals(inputColorName)));
    }
}
