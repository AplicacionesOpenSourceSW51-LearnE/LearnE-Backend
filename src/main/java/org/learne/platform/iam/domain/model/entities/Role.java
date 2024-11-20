package org.learne.platform.iam.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import org.learne.platform.iam.domain.model.valueobjects.UserType;
import org.learne.platform.shared.domain.model.entities.AuditableModel;

@Entity
@Getter
public class Role extends AuditableModel {


    @Enumerated(EnumType.STRING)
    private UserType name;



    public Role() {}

    public Role(UserType name) {
        this.name = name;
        if(name.name().equals("TEACHER")) this.setId(1);
        if(name.name().equals("STUDENT")) this.setId(2);

    }

    public String getStringName() {
        return name.name();
    }

}
