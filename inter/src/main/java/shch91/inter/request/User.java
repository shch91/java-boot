package shch91.inter.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = -8925536505890540756L;

    private String name;

    private Integer age;

    private String address;

}
