package cuculi.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Words implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    //单词
    private String word;

    //音标
    private String phonetic;

    //中文解释
    private String definition;
}
