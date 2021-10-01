package vtb.zf.base.test.converters;

import vtb.zf.base.test.dto.Country;
import vtb.zf.base.test.entities.CountryEntity;

public class CountryConverter extends AbstractConverter<Country, CountryEntity> {
    @Override
    public Country dtoFromEntity(CountryEntity entity, Country dto) {
        if (entity == null)
            return null;

        dto.setName(entity.getName());
        dto.setCodeLat3(entity.getCodeLat3());
        return dto;
    }

    @Override
    public CountryEntity entityFromDto(Country dto, CountryEntity entity) {
        if (dto == null)
            return null;

        entity.setName(dto.getName());
        entity.setCodeLat3(dto.getCodeLat3());
        return entity;
    }

    public CountryEntity entityFromDto(Country country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(country.getName());
        countryEntity.setCodeLat3(country.getCodeLat3());
        return countryEntity;
    }

    @Override
    public CountryEntity createEntity() {
        return new CountryEntity();
    }

    @Override
    public Country createDto() {
        return new Country();
    }
}
