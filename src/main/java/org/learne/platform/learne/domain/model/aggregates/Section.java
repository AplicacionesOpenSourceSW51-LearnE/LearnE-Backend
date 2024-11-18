package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.learne.platform.learne.domain.model.commands.CreateSectionCommand;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Section extends AuditableAbstractAggregateRoot<Section> {
    @Column
    @Size(min = 3, max = 30)
    private String title;

    @Column
    @Size(min = 3, max = 100)
    private String description;

    @Column
    @Size(min = 3, max = 30)
    private String urlToVideo;

    protected Section() {}

    public Section(CreateSectionCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.urlToVideo = command.urlToVideo();
    }

    public Section(Long id) { this.setId(id);}
}
