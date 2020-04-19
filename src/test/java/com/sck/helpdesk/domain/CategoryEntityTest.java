package com.sck.helpdesk.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryEntityTest {

    CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        categoryEntity = new CategoryEntity("TestCategory");
    }

    @Test
    void getId() {

        Long id = 2L;

        categoryEntity.setId(id);

        assertEquals(id, categoryEntity.getId());
    }

    @Test
    void getName() {

        String name = "SomeName";

        categoryEntity.setName(name);

        assertEquals(name, categoryEntity.getName());
    }

    @Test
    void getTickets() {

        TicketEntity ticket1 = new TicketEntity();
        ticket1.setId(1L);
        TicketEntity ticket2 = new TicketEntity();
        ticket2.setId(2L);
        TicketEntity ticket3 = new TicketEntity();
        ticket3.setId(3L);
        Set<TicketEntity> tickets = new HashSet<>(Arrays.asList(ticket1, ticket2, ticket3));

        categoryEntity.setTickets(tickets);

        assertThat(categoryEntity.getTickets().size()).isEqualTo(3);

    }

}