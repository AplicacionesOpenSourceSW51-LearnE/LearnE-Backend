package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.Material;
import org.learne.platform.learne.domain.model.commands.Material.CreateMaterialCommand;
import org.learne.platform.learne.domain.services.Material.MaterialCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialCommandServiceImpl implements MaterialCommandService {

    private final MaterialRepository materialRepository;

    public MaterialCommandServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    @Override
    public Long handle(CreateMaterialCommand command) {
        if(materialRepository.existsByCourseIdAndTitle(command.courseId(), command.title())) {
            throw new IllegalArgumentException("Material in the course" +
                    command.courseId() + "with title " + command.title() + " already exists");
        }

        var newMaterial = new Material(command);

        try {
            materialRepository.save(newMaterial);
        }catch (RuntimeException e) {
            throw new IllegalArgumentException("An error occurred while saving material" + e.getMessage());
        }

        return newMaterial.getId();
    }
}
