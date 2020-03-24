package com.excellent.accreditation.model.base;

import lombok.Data;

@Data
public class Options {

    /**
     * 选项
     */
    private String prefix;

    /**
     * 内容
     */
    private String content;

    /**
     * 分数
     */
    private String score;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
