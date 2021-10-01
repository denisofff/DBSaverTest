package vtb.zf.base.test.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(schema="Base", name="Providers")
public class ProviderEntity extends AbstractEntity {
    @Column(name="Name")
    private String name;

    @Column(name="Code")
    private String code;

    //@ManyToOne
    @OneToOne
    @JoinColumn(name="Country_Id", nullable=true)
    private CountryEntity country;

    /*
    @ManyToOne
    //@JoinColumn(name="Country_Id")
    @JoinColumn(name="Country_Id", nullable=false)
    private CountryEntity countryId;
    */

    @Override
    public String toString() {
        return "Provider{" +
                //"Id=" + id +
                ", Name='" + name + '\'' +
                ", Code='" + code + '\'' +
                ", Country='" + country.toString() + '\'' +
                '}';
    }
}
