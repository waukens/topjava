package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MockDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(value = "/mealsServlet")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MockDao mealsDao = new MealsDao();
    List<MealWithExceed> mealWithExceeds;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case("delete") :
                String id = request.getParameter("id");
                log.info("Delete {}" , id);
                mealsDao.delete(getId(request));
                response.sendRedirect("/meals.jsp");
                break;
            case ("create"):
            case ("update"):
                final Meal meal = action.equals("create") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealsDao.get(getId(request));
                request.setAttribute("meal", meal);
                log.info("redirect to {} meals", action);
                request.getRequestDispatcher("/mealsupdate.jsp").forward(request, response);
                break;
            case ("all"):
            default:
                log.info("show all");
                request.setAttribute("sortedMeals", MealsUtil.getAllMealWithExceeded(mealsDao.getAll(),
                        MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
