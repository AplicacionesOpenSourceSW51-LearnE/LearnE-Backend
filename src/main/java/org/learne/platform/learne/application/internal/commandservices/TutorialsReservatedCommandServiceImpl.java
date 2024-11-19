package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learne.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learne.domain.services.TutorialsReservated.TutorialsReservatedCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.TutorialsReservatedRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorialsReservatedCommandServiceImpl implements TutorialsReservatedCommandService {
    private final TutorialsReservatedRepository tutorialsReservatedRepository;
    public TutorialsReservatedCommandServiceImpl(TutorialsReservatedRepository tutorialsReservatedRepository) {
        this.tutorialsReservatedRepository = tutorialsReservatedRepository;
    }

    @Override
    public Long handle(CreateTutorialsReservatedCommand command) {
        var newTutorialsReservated = new TutorialsReservated(command);
        try {
            tutorialsReservatedRepository.save(newTutorialsReservated);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("An error occurred while saving the tutorials reservated " + e.getMessage());
        }
        return newTutorialsReservated.getId();
    }
}
