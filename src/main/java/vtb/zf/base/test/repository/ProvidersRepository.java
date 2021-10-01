package vtb.zf.base.test.repository;

import org.springframework.stereotype.Repository;
import vtb.zf.base.test.entities.ProviderEntity;

import java.util.Optional;

@Repository
public interface ProvidersRepository extends AbstractRepository<ProviderEntity>{
    Optional<ProviderEntity> findByCode(String code);
    /*
    @Query(value = "select \"Id\", \"Code\", \"Name\" from \"Base\".\"Providers\" where \"Code\" = :code", nativeQuery = true)
    Optional<ProviderEntity> findByCode(@Param("code") String codeLat3);
    */
}
