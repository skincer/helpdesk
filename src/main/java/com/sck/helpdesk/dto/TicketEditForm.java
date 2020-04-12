package com.sck.helpdesk.dto;

import com.sck.helpdesk.domain.TicketEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class TicketEditForm {

    @NotNull
    @Length(max = 200)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long userAssignedId;

    public TicketEditForm() {
    }

    public void fromTicketEntity(TicketEntity ticketEntity) {
        this.title = ticketEntity.getTitle();
        this.content = ticketEntity.getContent();
        this.categoryId = ticketEntity.getCategory().getId();
        this.userAssignedId = ticketEntity.getUserAssigned().getId();
    }

    public Long getUserAssignedId() {
        return userAssignedId;
    }

    public void setUserAssignedId(Long userAssignedId) {
        this.userAssignedId = userAssignedId;
    }

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
