package org.learne.platform.learne.domain.services.Unit;

import org.learne.platform.learne.domain.model.aggregates.Unit;
import org.learne.platform.learne.domain.model.commands.CreateUnitCommand;

import java.util.Optional;

public interface UnitCommandService {
    Optional<Unit> handle(CreateUnitCommand command);
}
