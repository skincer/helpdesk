package com.sck.helpdesk.domain;


import javax.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;


    @OneToMany(mappedBy = "userCreated")
    private Set<TicketEntity> ticketsCreated = new HashSet<>();

    @OneToMany(mappedBy = "userAssigned")
    private Set<TicketEntity> ticketsAssigned = new HashSet<>();

    @OneToMany
    private Set<MessageEntity> messages = new HashSet<>();


    public UserEntity() {
    }

    public UserEntity(final String username, final String password, final UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
