package com.sck.helpdesk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TicketEntity {

    public enum TicketStatus {
        OPEN,
        CLOSED
    }

    @Id
    @GeneratedValue
    private Long id;
}
