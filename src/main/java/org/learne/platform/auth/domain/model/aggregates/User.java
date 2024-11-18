package org.learne.platform.auth.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.learne.platform.auth.domain.model.commands.CreateUserCommand;
import org.learne.platform.auth.domain.model.valueobjects.Memberships;
import org.learne.platform.auth.domain.model.valueobjects.UserTypes;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AuditableAbstractAggregateRoot<User> {
    @Column
    @Size(min = 3, max = 50)
    private String firstName;

    @Column
    @Size(min = 3, max = 50)
    private String lastName;

    @Column(unique = true)
    @Size(min = 3, max = 120)
    private String username;

    @Column(unique = true)
    @Size(min = 3, max = 120)
    private String email;

    @Column
    @Size(min = 3, max = 120)
    private String password;

    @Column
    private UserTypes userType;

    @Column
    private Memberships membership;

    protected User() {}

    public User(CreateUserCommand command){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.username = command.username();
        this.email = command.email();
        this.password = command.password();
        this.userType = command.userType();
        this.membership = command.membership();
    }

    //Probando para TutorialsCourses
    public User(Long id) {this.setId(id);}

}
