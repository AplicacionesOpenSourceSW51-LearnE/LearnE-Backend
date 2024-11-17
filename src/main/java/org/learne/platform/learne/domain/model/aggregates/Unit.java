package org.learne.platform.learne.domain.model.aggregates;


import jakarta.persistence.Entity;
import lombok.Getter;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Unit extends AuditableAbstractAggregateRoot<Unit> {


    public Unit() {}

    //no borrar, se esta usando en Exam (borra este comentario)
    public Unit(Long id) {
        this.setId(id);
    }
}
