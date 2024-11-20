package org.learne.platform.iam.domain.services;

import org.learne.platform.iam.domain.model.entities.Role;
import org.learne.platform.iam.domain.model.queries.GetAllRolesQuery;
import org.learne.platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);

    Optional<Role> handle(GetRoleByNameQuery query);

}
