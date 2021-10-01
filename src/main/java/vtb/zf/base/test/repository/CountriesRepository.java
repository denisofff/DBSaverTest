package vtb.zf.base.test.repository;

import org.springframework.stereotype.Repository;
import vtb.zf.base.test.entities.CountryEntity;

import java.util.Optional;

@Repository
public interface CountriesRepository extends AbstractRepository<CountryEntity> {
    Optional<CountryEntity> findByCodeLat3(String codeLat3);
    /*
    @Query(value = "select \"Id\", \"CodeLat3\", \"Name\" from \"Base\".\"Countries\" where \"CodeLat3\" = :codeLat3", nativeQuery = true)
    Optional<CountryEntity> findByCodeLat3(@Param("codeLat3") String codeLat3);
     */
}
