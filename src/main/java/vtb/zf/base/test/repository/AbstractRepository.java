package vtb.zf.base.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vtb.zf.base.test.entities.AbstractEntity;

@Repository
public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<AbstractEntity, Integer> {
}