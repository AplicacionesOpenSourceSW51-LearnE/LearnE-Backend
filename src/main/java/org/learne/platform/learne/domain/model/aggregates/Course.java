package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.learne.platform.learne.domain.model.commands.CreatedCourseCommand;
import org.learne.platform.learne.domain.model.valueobjects.TeacherId;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Course extends AuditableAbstractAggregateRoot<Course> {
    @Column
    @Size(min = 3, max = 50)
    private String title;

    @Column
    @Size(min = 3, max = 50)
    private String description;

    @Column
    @Size(min = 3, max = 50)
    private TeacherId teacherId;

    @Column
    @Size(min = 3, max = 50)
    private String level;

    @Column
    @Size(min = 3, max = 50)
    private String duration;

    @Column
    @Size(min = 3, max = 50)
    private String prior_knowledge;

    @Column
    @Size(min = 3, max = 50)
    private String principal_image;


    protected Course() {}

    public Course(CreatedCourseCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.teacherId = command.teacherId();
        this.level = command.level();
        this.duration = command.duration();
        this.prior_knowledge = command.prior_knowledge();
        this.principal_image = command.principal_image();
    }
}
