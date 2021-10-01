package vtb.zf.base.test.converters;

import vtb.zf.base.test.dto.Provider;
import vtb.zf.base.test.entities.ProviderEntity;

public class ProviderConverter extends AbstractConverter<Provider, ProviderEntity>{
    @Override
    public Provider dtoFromEntity(ProviderEntity entity, Provider dto) {
        if (entity == null)
            return null;

        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        return dto;
    }

    @Override
    public ProviderEntity entityFromDto(Provider dto, ProviderEntity entity) {
        if (dto == null)
            return null;

        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }

    public ProviderEntity entityFromDto(Provider provider) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setName(provider.getName());
        providerEntity.setCode(provider.getCode());
        return providerEntity;
    }

    @Override
    public ProviderEntity createEntity() {
        return new ProviderEntity();
    }

    @Override
    public Provider createDto() {
        return new Provider();
    }
}
