package org.learne.platform.iam.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.learne.platform.iam.domain.model.commands.SignUpCommand;
import org.learne.platform.iam.domain.model.entities.Plan;
import org.learne.platform.iam.domain.model.entities.Role;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {


    @NotBlank
    @Column(nullable = false)
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new Role();
        this.plan = new Plan();
    }

    public User(String username, String password, Role role, Plan plan) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.plan = plan;

    }

    public User(SignUpCommand command, String password, Role role, Plan plan) {
        this.username = command.username();
        this.password = password;
        this.role = role;
        this.plan = plan;
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
    }

    public User(Long id) {this.setId(id);}

}
