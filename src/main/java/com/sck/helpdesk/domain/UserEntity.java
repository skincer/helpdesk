package com.sck.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UserEntity {

    public enum UserType {
        CUSTOMER,
        AGENT
    }

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany
    private Set<TicketEntity> ticketsCreated = new HashSet<>();

    @OneToMany
    private Set<TicketEntity> ticketsAssigned = new HashSet<>();

    @OneToMany
    private Set<MessageEntity> messages = new HashSet<>();


    public UserEntity() {
    }

    public UserEntity(final String firstName, final String lastName, final UserType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Set<TicketEntity> getTicketsCreated() {
        return ticketsCreated;
    }

    public void setTicketsCreated(Set<TicketEntity> ticketsCreated) {
        this.ticketsCreated = ticketsCreated;
    }

    public Set<TicketEntity> getTicketsAssigned() {
        return ticketsAssigned;
    }

    public void setTicketsAssigned(Set<TicketEntity> ticketsAssigned) {
        this.ticketsAssigned = ticketsAssigned;
    }

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
