package com.sck.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class TicketEntity {

    public enum TicketStatus {
        OPEN,
        CLOSED
    }

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    @Column(updatable = false, insertable = false)
    private Date createdAt;

    @ManyToOne
    private UserEntity userCreated;

    @ManyToOne
    private UserEntity userAssigned;

    @ManyToOne
    private CategoryEntity category;

    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.OPEN;

    private String title;

    private String content;

    private String resolution;

    public TicketEntity() {
    }

    public TicketEntity(final String title, final String content, final UserEntity userCreated) {

        this.title = title;
        this.content = content;
        this.userCreated = userCreated;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
    }

    public UserEntity getUserAssigned() {
        return userAssigned;
    }

    public void setUserAssigned(UserEntity userAssigned) {
        this.userAssigned = userAssigned;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
