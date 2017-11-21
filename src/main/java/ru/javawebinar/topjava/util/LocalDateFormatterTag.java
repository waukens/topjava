package ru.javawebinar.topjava.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatterTag extends SimpleTagSupport {
    private String pattern;
    private LocalDateTime localDateTime;

    public LocalDateFormatterTag() {

    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
            getJspContext().getOut().write(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SkipPageException("Exception in date " + localDateTime +
                " with pattern " + pattern);
        }
    }
}
