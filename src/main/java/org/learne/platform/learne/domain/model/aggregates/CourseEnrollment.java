package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.learne.domain.model.commands.CreateCourseEnrollmentCommand;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
public class CourseEnrollment extends AuditableAbstractAggregateRoot<CourseEnrollment> {
    @Column
    @NotNull
    private User student_id;

    @Column
    @NotNull
    private Course course_id;

    protected CourseEnrollment() {}

    public CourseEnrollment(CreateCourseEnrollmentCommand command) {
        this.student_id = command.student_id();
        this.course_id = command.course_id();
    }
}
