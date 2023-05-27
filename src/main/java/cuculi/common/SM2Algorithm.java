package cuculi.common;

import lombok.Data;

import java.time.LocalDate;
@Data
public class SM2Algorithm {
    // 定义初始记忆强度因子为2.5，连续正确次数为0，下次复习日期为当天
    private double easinessFactor;
    private int consecutiveCorrectAnswers;
    private LocalDate nextReviewDate;

    // 构造函数，初始化成员变量
    public SM2Algorithm() {
        this.easinessFactor = 2.5;
        this.consecutiveCorrectAnswers = 0;
        this.nextReviewDate = LocalDate.now();
    }

    // 更新记忆强度因子
    public void updateEasinessFactor(boolean answerIsCorrect) {
        if (answerIsCorrect) {  // 如果答案正确
            this.consecutiveCorrectAnswers++;  // 连续正确次数加1
            // 根据公式更新记忆强度因子
            this.easinessFactor = Math.max(1.3, this.easinessFactor + 0.1 - (3 - this.consecutiveCorrectAnswers) * (0.08 + (3 - this.consecutiveCorrectAnswers) * 0.02));
        } else {  // 如果答案错误
            this.consecutiveCorrectAnswers = 0;  // 连续正确次数清零
            // 根据公式更新记忆强度因子
            this.easinessFactor = Math.max(1.3, this.easinessFactor - 0.8);
        }
    }

    // 更新下次复习日期
    public void updateReviewDate() {
        // 下次复习日期为当前日期加上复习间隔（单位：天）
        this.nextReviewDate = LocalDate.now().plusDays((int) Math.round(getInterval()));
    }

    // 获取复习间隔
    private double getInterval() {
        if (consecutiveCorrectAnswers == 0) {  // 如果连续正确次数为0
            return 1;  // 复习间隔为1天
        } else if (consecutiveCorrectAnswers == 1) {  // 如果连续正确次数为1
            return 6;  // 复习间隔为6天
        } else {  // 如果连续正确次数大于1
            return Math.round(getIntervalForN(consecutiveCorrectAnswers) * easinessFactor);  // 根据公式计算复习间隔
        }
    }

    // 获取连续正确次数为n的复习间隔
    private double getIntervalForN(int n) {
        if (n == 2) {  // 如果连续正确次数为2
            return 15;  // 复习间隔为15天
        } else {  // 如果连续正确次数大于2
            return getIntervalForN(n-1) * getEaseFactor();  // 根据公式计算复习间隔
        }
    }



    // 获取记忆强度因子
    public double getEaseFactor() {
        return this.easinessFactor;
    }

    // 获取下次复习日期
    public LocalDate getNextReviewDate() {
        return this.nextReviewDate;
    }
}