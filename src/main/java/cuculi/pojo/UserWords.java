package cuculi.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserWords implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Long userId;
    private int wordId;
    private LocalDate nextReviewDate;
    private Double easinessFactor;
    private Integer consecutiveCorrectAnswers;
}
