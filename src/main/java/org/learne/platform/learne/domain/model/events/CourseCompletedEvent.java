package org.learne.platform.learne.domain.model.events;

import org.learne.platform.learne.domain.model.valueobjects.TeacherId;
import org.springframework.context.ApplicationEvent;

public class CourseCompletedEvent  extends ApplicationEvent {
    private final String title;
    private final TeacherId teacherId;

    public CourseCompletedEvent(Object source, String title, TeacherId teacherId) {
        super(source);
        this.title = title;
        this.teacherId = teacherId;
    }
}
