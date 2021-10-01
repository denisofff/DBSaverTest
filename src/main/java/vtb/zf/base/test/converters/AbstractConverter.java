package vtb.zf.base.test.converters;

import vtb.zf.base.test.dto.AbstractDto;
import vtb.zf.base.test.entities.AbstractEntity;

public abstract class AbstractConverter <DTO extends AbstractDto, ENTITY extends AbstractEntity>{
    public abstract  DTO dtoFromEntity(ENTITY entity, DTO dto);
    public DTO dtoFromEntity(ENTITY entity) {
        DTO dto = createDto();
        return dtoFromEntity(entity, dto);
    }
    public abstract ENTITY  entityFromDto(DTO dto, ENTITY entity);

    public ENTITY  entityFromDto(DTO dto) {
        ENTITY entity = createEntity();
        return entityFromDto(dto, entity);
    }

    public abstract ENTITY createEntity();
    public abstract DTO createDto();
}
