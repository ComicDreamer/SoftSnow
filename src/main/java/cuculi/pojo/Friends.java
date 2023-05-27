package cuculi.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Friends implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Long userId;
    private Long friendId;
    private String status;
}
