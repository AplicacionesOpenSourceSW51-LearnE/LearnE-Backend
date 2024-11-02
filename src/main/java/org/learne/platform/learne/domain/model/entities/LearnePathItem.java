package org.learne.platform.learne.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.valueobjects.TeacherId;
import org.learne.platform.shared.domain.model.entities.AudiTableModel;

/**
 * Learning path item entity
 * @summary The class represents a learned path item entity
 * A learned path item is a course with teacher and a reference to next item in the learned path
 * @see Course
 * @see TeacherId
 */
@Entity
@Getter
public class LearnePathItem extends AudiTableModel {
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @NotNull
    @Embedded
    @Column(name = "teacher_id")
    private TeacherId teacherId;

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearnePathItem nextItem;

    public LearnePathItem(Course course, TeacherId teacherId, LearnePathItem nextItem) {
        this.course = course;
        this.teacherId = teacherId;
        this.nextItem = nextItem;
    }

    public LearnePathItem() {
        this.teacherId = new TeacherId(0L);
        this.nextItem = null;
    }

    /**
     * Update the next item in the learned path
     * @param nextItem The next item in the learned path
     */
    public void updateNextItem(LearnePathItem nextItem) { this.nextItem = nextItem; }
}
