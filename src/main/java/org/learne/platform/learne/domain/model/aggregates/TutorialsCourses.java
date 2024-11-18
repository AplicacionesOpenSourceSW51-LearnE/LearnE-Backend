package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.learne.domain.model.commands.CreateTutorialsCoursesCommand;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class TutorialsCourses extends AuditableAbstractAggregateRoot<TutorialsCourses> {
    @OneToOne
    @JoinColumn(name = "courses_id", nullable = false)
    private Course course;

    @OneToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String hour;

    @Column
    private Boolean isReservated = false;

    @Column(nullable = false)
    private String link;

    public TutorialsCourses() {}

    public TutorialsCourses updateReserved(Boolean isReserved) {
        this.isReservated = isReserved;
        return this;
    }

    public TutorialsCourses updateLink(String link) {
        this.link = link;
        return this;
    }

    public TutorialsCourses(CreateTutorialsCoursesCommand command) {
        this.course = new Course(command.courseId());
        this.user = new User(command.teacherId());
        this.date = command.date();
        this.hour = command.hour();
        this.link = command.link();
    }

    public TutorialsCourses(Long id) {this.setId(id);}
}
