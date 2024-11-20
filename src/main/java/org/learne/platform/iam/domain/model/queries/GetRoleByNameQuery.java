package org.learne.platform.iam.domain.model.queries;

import org.learne.platform.iam.domain.model.valueobjects.UserType;

public record GetRoleByNameQuery(UserType name) {
}
