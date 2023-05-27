package cuculi.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    private String avatar;
}
