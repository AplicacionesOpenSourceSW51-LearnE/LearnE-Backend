package org.learne.platform.iam.infrastructure.persistence.jpa.repositories;

import org.learne.platform.iam.domain.model.entities.Role;
import org.learne.platform.iam.domain.model.valueobjects.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserType name);

    boolean existsByName(UserType name);

}
