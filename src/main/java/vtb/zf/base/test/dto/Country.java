package vtb.zf.base.test.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Country extends AbstractDto {
    private String name;
    private String codeLat3;
}
