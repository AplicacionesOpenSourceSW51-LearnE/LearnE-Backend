package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.learne.platform.learne.domain.model.commands.CreatedCourseCommand;
import org.learne.platform.learne.domain.model.valueobjects.LearnePath;
import org.learne.platform.learne.domain.model.valueobjects.TeacherId;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private Long id;
    private String title;
    private String description;
    private TeacherId teacherId;
    private String level;
    private String duration;
    private String prior_knowledge;
    private String principal_image;


    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.level = Strings.EMPTY;
        this.duration = Strings.EMPTY;
        this.prior_knowledge = Strings.EMPTY;
        this.principal_image = Strings.EMPTY;
    }

    public Course(CreatedCourseCommand command) {
        this.id = command.Id();
        this.title = command.title();
        this.description = command.description();
        this.teacherId = command.teacherId();
        this.level = command.level();
        this.duration = command.duration();
        this.prior_knowledge = command.prior_knowledge();
        this.principal_image = command.principal_image();
    }


}
