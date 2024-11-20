package org.learne.platform.iam.interfaces.rest.transform;

import org.learne.platform.iam.domain.model.entities.Role;
import org.learne.platform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}