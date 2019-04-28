package com.wjaronski.tiwprproject1.model.parser;

import com.wjaronski.tiwprproject1.model.Meal;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Wojciech Jaronski
 */
@Slf4j
public class MealParser {

    private static Double parseDouble(String string) {
        if (string.isEmpty()) return null;
        return Double.parseDouble(string.replace(",", "."));
    }

    private static Float parseFloat(String string) {
        if (string.isEmpty()) return null;
        return Float.parseFloat(string.replace(",", ".")) / 100;
    }

    public static Meal parseMeal(String line) {
        line = line.replaceAll("%", "");
        String[] token = line.split(";");
        if (token.length != 29) {
//            log.warn(line);
            if (!token[0].isEmpty())
                return Meal.builder()
                        .category(token[0])
                        .build();
            log.warn(line);
            return null;
        }

        try {
            return Meal.builder()
//                .id                 (Integer.valueOf(token[0]))
                    .name(token[0])
                    .avgWeight(parseDouble(token[1]))
                    .energy100kJ(Integer.valueOf(token[2]))
                    .energykJ(Integer.valueOf(token[3]))
                    .energy100kcal(Integer.valueOf(token[4]))
                    .energykcal(Integer.valueOf(token[5]))
                    .energyRWS(parseFloat(token[6]))
                    .fat100(parseDouble(token[7]))
                    .fat(parseDouble(token[8]))
                    .fatRWS(parseFloat(token[9]))
                    .fatAcid100(parseDouble(token[10]))
                    .fatAcid(parseDouble(token[11]))
                    .fatAcidRWS(parseFloat(token[12]))
                    .fatTrans100(parseDouble(token[13]))
                    .fatTrans(parseDouble(token[14]))
                    .carbs100(parseDouble(token[15]))
                    .carbs(parseDouble(token[16]))
                    .carbsRWS(parseFloat(token[17]))
                    .carbsSugar100(parseDouble(token[18]))
                    .carbsSugar(parseDouble(token[19]))
                    .carbsSugarRWS(parseFloat(token[20]))
                    .fiber100(parseDouble(token[21]))
                    .fiber(parseDouble(token[22]))
                    .protein100(parseDouble(token[23]))
                    .protein(parseDouble(token[24]))
                    .proteinRWS(parseFloat(token[25]))
                    .salt100(parseDouble(token[26]))
                    .salt(parseDouble(token[27]))
                    .saltRWS(parseFloat(token[28]))
                    .build();
        } catch (Exception e) {
            log.warn("Line: {}\n{}", line, e.getLocalizedMessage());
            return null;
        }
    }
}
