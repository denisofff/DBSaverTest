package vtb.zf.base.test.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(schema="Base", name="Countries")
@Inheritance(strategy = InheritanceType.JOINED)
public class CountryEntity extends AbstractEntity {
    /*
    @Id
    @AttributeOverride(name = "Id", column = @Column(name = "Id"))
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", insertable = false)
    private Integer id;

     */

    @Column(name="Name")
    private String name;

    @Column(name="CodeLat3")
    private String codeLat3;

    @Override
    public String toString() {
        return "Country{" +
                //"Id=" + id +
                ", Name='" + name + '\'' +
                ", CodeLat3='" + codeLat3 + '\'' +
                '}';
    }
}
