package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;


    public static final List<Meal> USER_MEALS = Arrays.asList(
            new Meal(LocalDateTime.now(), "breakfast_USER", 2000),
            new Meal(LocalDateTime.now(), "supper_USER", 2000),
            new Meal(LocalDateTime.now(), "lunch_USER", 2000)
    );
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(LocalDateTime.now(), "lunch_ADMIN", 2000),
            new Meal(LocalDateTime.now(), "breakfast_ADMIN", 2000),
            new Meal(LocalDateTime.now(), "supper_ADMIN", 2000)
    );
    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id", "date_time", "user_id").isEqualTo(expected);
    }

}
