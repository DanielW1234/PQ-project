package com.njust.springboot.entity;

public class Answer {
    private Long id;
    private Long userId;
    private Long quizId;
    private String chosenOption;
    private Boolean isCorrect;
    private Boolean isAnswer;
    // getter 和 setter 可用 Lombok 简化

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(String chosenOption) {
        this.chosenOption = chosenOption;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }
}