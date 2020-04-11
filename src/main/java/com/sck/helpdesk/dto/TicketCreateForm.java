package com.sck.helpdesk.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class TicketCreateForm {

    @NotNull
    @Length(max = 200)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
