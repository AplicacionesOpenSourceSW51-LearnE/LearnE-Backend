package org.learne.platform.learne.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.learne.platform.learne.domain.model.commands.CreatePaymentCommand;
import org.learne.platform.learne.domain.model.valueobjects.EmailAddress;
import org.learne.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String nameCard;

    @Column(nullable = false)
    private Long numberCard;

    @Column(nullable = false)
    private String expireDate;

    @Column(nullable = false)
    private Integer securityCode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))
    })
    private EmailAddress emailAddress;

    public Payment() {}

    public Payment updateInformation(Long studentId, String nameCard, Long numberCard, String expireDate, Integer securityCode, String emailAddress) {
        this.studentId = studentId;
        this.nameCard = nameCard;
        this.numberCard = numberCard;
        this.expireDate = expireDate;
        this.securityCode = securityCode;
        this.emailAddress = new EmailAddress(emailAddress);
        return this;
    }

    public Payment(CreatePaymentCommand command) {
        this.studentId = command.studentId();
        this.nameCard = command.nameCard();
        this.numberCard = command.numberCard();
        this.expireDate = command.expireDate();
        this.securityCode = command.securityCode();
        this.emailAddress = new EmailAddress(command.emailAddress());
    }
}
