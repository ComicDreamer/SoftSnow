package cuculi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuculi.common.R;
import cuculi.pojo.Words;
import cuculi.service.WordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/words")
public class WordsController {
    @Autowired
    private WordsService wordsService;

    /**
     * 查询单词
     * @param word
     * @return
     */
    @GetMapping
    public R<Words> search(String word, HttpSession session){
        LambdaQueryWrapper<Words> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Words::getWord, word);
        Words info = wordsService.getOne(queryWrapper);
        if (info == null){
            return R.error("查无此词");
        }
        log.info(String.valueOf(session.getAttribute("user")));
        return R.success(info);
    }

}
