package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    private static final Logger log = LoggerFactory.getLogger(MealServiceTest.class);

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test(expected = NotFoundException.class)
    public void get() {
        service.get(100000, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(100000, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> expectedMeals = Arrays.asList(USER_MEALS.get(2), USER_MEALS.get(1));
        List<Meal> actualMeals = service.getBetweenDates(LocalDate.parse("2010-01-01"),
                LocalDate.parse("2010-12-30"), USER_ID);
        assertMatch(actualMeals, expectedMeals);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> expectedMeals = Arrays.asList(USER_MEALS.get(2));
        List<Meal> actualMeals = service.getBetweenDateTimes(LocalDateTime.parse("2010-06-04T10:30:00"),
                LocalDateTime.parse("2010-06-04T11:30:00"), USER_ID);
        assertMatch(actualMeals, expectedMeals);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, USER_MEALS);
    }

    @Test(expected = NotFoundException.class)
    public void update() {
        Meal newMeal = service.get(100000, USER_ID);
        newMeal.setDescription("new_food");
        service.update(newMeal, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = service.create(new Meal(LocalDateTime.now(), "admin_vodka", 1000), ADMIN_ID);
        List<Meal> actualMeals = service.getAll(ADMIN_ID);
        List<Meal> expectedMeals = Arrays.asList(newMeal, ADMIN_MEALS.get(0), ADMIN_MEALS.get(1), ADMIN_MEALS.get(2), ADMIN_MEALS.get(3));
        assertMatch(actualMeals, expectedMeals);
    }
}