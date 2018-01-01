package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import javax.persistence.TableGenerator;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    Meal getOne(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Transactional
    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime DESC")
    List<Meal> findAll(@Param("user_id") int userId);

    @Transactional
    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id AND m.dateTime>=:data_start AND m.dateTime<=:data_end ORDER BY m.dateTime DESC")
    List<Meal> findAllBetweenDate(@Param("user_id") int userId, @Param("data_start") LocalDateTime startDate, @Param("data_end")LocalDateTime endDate);


}
