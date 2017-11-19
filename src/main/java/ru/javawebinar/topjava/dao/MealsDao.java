package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDao implements MockDao {
    private Map<Integer, Meal> userMeals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.USER_MEALS.forEach(this::add);
    }

    @Override
    public void delete(int id) {
        userMeals.remove(id);
    }

    @Override
    public Meal add(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
        }
        userMeals.put(meal.getId(), meal);
        return meal;

    }

    @Override
    public Meal get(int id) {
        return userMeals.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return userMeals.values();
    }
}

