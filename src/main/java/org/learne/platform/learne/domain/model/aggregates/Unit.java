package org.learne.platform.learne.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.learne.platform.learne.domain.model.commands.CreateUnitCommand;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Unit extends AuditableAbstractAggregateRoot<Unit> {
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    public Unit() {}

    public Unit(CreateUnitCommand command) {
        this.course = new Course(command.courseId());
        this.title = command.title();
    }

    //no borrar, se esta usando en Exam (borra este comentario)
    public Unit(Long id) {
        this.setId(id);
    }
}
