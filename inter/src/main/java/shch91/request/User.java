package shch91.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = -8925536505890540756L;

    private String name;

    private Integer age;

    private String address;

}
