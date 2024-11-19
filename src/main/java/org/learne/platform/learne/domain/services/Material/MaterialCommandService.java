package org.learne.platform.learne.domain.services.Material;

import org.learne.platform.learne.domain.model.commands.Material.CreateMaterialCommand;

public interface MaterialCommandService {
    Long handle(CreateMaterialCommand command);
}
