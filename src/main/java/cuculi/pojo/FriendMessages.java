package cuculi.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FriendMessages implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Long senderId;
    private Long recipientId;
    private String messageText;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
