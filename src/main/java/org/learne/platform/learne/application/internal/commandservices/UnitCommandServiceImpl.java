package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.Unit;
import org.learne.platform.learne.domain.model.commands.CreateUnitCommand;
import org.learne.platform.learne.domain.services.Unit.UnitCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitCommandServiceImpl implements UnitCommandService {
    private final UnitRepository _unitRepository;

    public UnitCommandServiceImpl(UnitRepository unitRepository) {
        this._unitRepository = unitRepository;
    }

    @Override
    public Optional<Unit> handle(CreateUnitCommand command) {
        if (_unitRepository.existsByCourseId(command.courseId())) {
            throw new IllegalArgumentException("Unit for course already exists");
        }
        var unit = new Unit(command);
        _unitRepository.save(unit);
        return Optional.of(unit);
    }
}
