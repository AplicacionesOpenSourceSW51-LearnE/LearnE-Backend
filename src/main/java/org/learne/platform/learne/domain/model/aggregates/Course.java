package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.learne.platform.learne.domain.model.valueobjects.LearnePath;
import org.learne.platform.learne.domain.model.valueobjects.TeacherId;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;
    private TeacherId teacherId;
    private String level;
    private String duration;
    private String prior_knowledge;
    private String principal_image;

    @Embedded
    private final LearnePath learnePath;

    /**
     * Create a new course
     */

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.level = Strings.EMPTY;
        this.duration = Strings.EMPTY;
        this.prior_knowledge = Strings.EMPTY;
        this.principal_image = Strings.EMPTY;
        this.learnePath = new LearnePath();
    }

    /**
     * Update the course information
     * @param title The title of the course
     * @param description The description of the course
     * @param teacherId The teacher id about the course
     * @param  level The level in the course
     * @param duration The duration of time in the course
     * @param prior_knowledge The course syllabus
     * @param principal_image The image of the course
     * @return
     */
    public Course updateInformation(String title, String description, TeacherId teacherId, String level, String duration, String prior_knowledge, String principal_image) {
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.level = level;
        this.duration = duration;
        this.prior_knowledge = prior_knowledge;
        this.principal_image = principal_image;
        return this;
    }
}
