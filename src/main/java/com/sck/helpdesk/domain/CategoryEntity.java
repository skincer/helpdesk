package com.sck.helpdesk.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private Set<TicketEntity> tickets = new HashSet<>();

    public CategoryEntity() {
    }

    public CategoryEntity(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
