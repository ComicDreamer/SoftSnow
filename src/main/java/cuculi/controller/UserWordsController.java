package cuculi.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuculi.common.BaseContext;
import cuculi.common.R;
import cuculi.common.SM2Algorithm;
import cuculi.pojo.UserWords;
import cuculi.pojo.Words;
import cuculi.service.UserWordsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/words")
public class UserWordsController {
    @Autowired
    public UserWordsService userWordsService;

    /**
     * 从我的单词库中删除单词
     * @param id
     * @return
     */
    @DeleteMapping("/remove")
    public R<String> remove(String id){
        Long wordId = Long.valueOf(id);
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<UserWords> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserWords::getWordId, wordId).eq(UserWords::getUserId, userId);

        userWordsService.remove(queryWrapper);
        return R.success("删除成功");
    }

    /**
     * 添加单词到我的记忆库
     * @param id
     * @return
     */
    @RequestMapping ("/add")
    public R<String> add(@RequestParam("id") String id){
        int wordId = Integer.valueOf(id);
        Long userId = BaseContext.getCurrentId();
        //设置记忆初始参数
        SM2Algorithm sm2 = new SM2Algorithm();
        sm2.updateReviewDate();
        UserWords word = new UserWords();
        word.setWordId(wordId);
        word.setUserId(userId);
        //填充数据
        word.setEasinessFactor(sm2.getEaseFactor());
        word.setConsecutiveCorrectAnswers(sm2.getConsecutiveCorrectAnswers());
        word.setNextReviewDate(sm2.getNextReviewDate());

        userWordsService.save(word);
        return R.success("添加成功");
    }

    /**
     * 更新我的单词复习时间
     */
    @PutMapping("/update")
    public void update(Long wordId, boolean isCorrect){
        //根据userId和wordId查询出单词复习记录
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<UserWords> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserWords::getWordId, wordId).eq(UserWords::getUserId, userId);
        UserWords word = userWordsService.getOne(queryWrapper);

        //更新复习时间
        SM2Algorithm sm2 = new SM2Algorithm();
        sm2.setEasinessFactor(word.getEasinessFactor());
        if (isCorrect == false){
            sm2.setConsecutiveCorrectAnswers(0);
            word.setConsecutiveCorrectAnswers(0);
        }else {
            sm2.setConsecutiveCorrectAnswers(word.getConsecutiveCorrectAnswers() + 1);
            word.setConsecutiveCorrectAnswers(sm2.getConsecutiveCorrectAnswers());
        }
        sm2.updateReviewDate();
        word.setNextReviewDate(sm2.getNextReviewDate());

        //更新记录
        userWordsService.updateById(word);
    }

    /**
     * 获取当日应该复习的单词
     * @return
     */
    @GetMapping("/review")
    public R<Words[]> review(){
        LocalDate today = LocalDate.now();
        LambdaQueryWrapper<UserWords> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserWords::getNextReviewDate, today);
        return null;
    }

    /**
     * 查询该单词是否在单词库
     * @return
     */
    @GetMapping("/query")
    public R<Integer> isInMyWord(Long id){
        Long wordId = Long.valueOf(id);
        LambdaQueryWrapper<UserWords> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserWords::getWordId, wordId);
        UserWords record = userWordsService.getOne(queryWrapper);
        if (record == null){
            return R.success(1);
        }else{
            return R.success(0);
        }
    }
}
