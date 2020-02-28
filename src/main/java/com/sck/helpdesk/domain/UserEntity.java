package com.sck.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    @Column(updatable = false, insertable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    @Column(updatable = false, insertable = false)
    private Date updatedAt;

}
