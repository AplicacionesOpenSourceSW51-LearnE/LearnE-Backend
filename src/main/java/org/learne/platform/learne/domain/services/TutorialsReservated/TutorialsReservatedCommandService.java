package org.learne.platform.learne.domain.services.TutorialsReservated;

import org.learne.platform.learne.domain.model.commands.CreateTutorialsReservatedCommand;

public interface TutorialsReservatedCommandService {
    Long handle(CreateTutorialsReservatedCommand command);
}
