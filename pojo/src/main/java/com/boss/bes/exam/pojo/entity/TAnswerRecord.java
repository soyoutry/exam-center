package com.boss.bes.exam.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "t_answer_record")
public class TAnswerRecord implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 答卷明细ID
     */
    @Id
    @Column(name = "answer_detail_id")
    private Long answerDetailId;

    /**
     * 我的答案
     */
    @Column(name = "my_answer")
    private String myAnswer;

    /**
     * 标准答案
     */
    @Column(name = "standard_answer")
    private String standardAnswer;

    /**
     * 得分
     */
    private BigDecimal score;

    /**
     * 评价
     */
    private String evaluate;

    /**
     * 题目ID
     */
    @Column(name = "paper_subject_id")
    private Long paperSubjectId;

    /**
     * 考试记录ID
     */
    @Column(name = "exam_record_id")
    private Long examRecordId;

    /**
     * 获取答卷明细ID
     *
     * @return answer_detail_id - 答卷明细ID
     */
    public Long getAnswerDetailId() {
        return answerDetailId;
    }

    /**
     * 设置答卷明细ID
     *
     * @param answerDetailId 答卷明细ID
     */
    public void setAnswerDetailId(Long answerDetailId) {
        this.answerDetailId = answerDetailId;
    }

    /**
     * 获取我的答案
     *
     * @return my_answer - 我的答案
     */
    public String getMyAnswer() {
        return myAnswer;
    }

    /**
     * 设置我的答案
     *
     * @param myAnswer 我的答案
     */
    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    /**
     * 获取标准答案
     *
     * @return standard_answer - 标准答案
     */
    public String getStandardAnswer() {
        return standardAnswer;
    }

    /**
     * 设置标准答案
     *
     * @param standardAnswer 标准答案
     */
    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    /**
     * 获取得分
     *
     * @return score - 得分
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置得分
     *
     * @param score 得分
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 获取评价
     *
     * @return evaluate - 评价
     */
    public String getEvaluate() {
        return evaluate;
    }

    /**
     * 设置评价
     *
     * @param evaluate 评价
     */
    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    /**
     * 获取题目ID
     *
     * @return paper_subject_id - 题目ID
     */
    public Long getPaperSubjectId() {
        return paperSubjectId;
    }

    /**
     * 设置题目ID
     *
     * @param paperSubjectId 题目ID
     */
    public void setPaperSubjectId(Long paperSubjectId) {
        this.paperSubjectId = paperSubjectId;
    }

    /**
     * 获取考试记录ID
     *
     * @return exam_record_id - 考试记录ID
     */
    public Long getExamRecordId() {
        return examRecordId;
    }

    /**
     * 设置考试记录ID
     *
     * @param examRecordId 考试记录ID
     */
    public void setExamRecordId(Long examRecordId) {
        this.examRecordId = examRecordId;
    }
}