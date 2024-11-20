package org.learne.platform.iam.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import org.learne.platform.shared.domain.model.entities.AuditableModel;

@Entity
@Getter
public class Plan extends AuditableModel {

    @Enumerated(EnumType.STRING)
    private org.learne.platform.iam.domain.model.valueobjects.Plan name;

    public Plan() {}

    public Plan(org.learne.platform.iam.domain.model.valueobjects.Plan name) {
        this.name = name;
        if(name.name().equals("FREE")) this.setId(1);
        if(name.name().equals("PREMIUM")) this.setId(2);
        if(name.name().equals("NOTHING")) this.setId(3);
    }


    public String getStringName() {
        return name.name();
    }
}
