package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MockDao {
    void delete(int id);
    Meal add(Meal meal);
    Meal get(int id);
    Collection<Meal> getAll();
}
