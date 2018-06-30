package com.travel.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authority_id_seq")
    @SequenceGenerator(name = "authority_id_seq",sequenceName = "authority_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "authority_role")
    private String authorityRole;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public String getAuthorityRole() {
        return authorityRole;
    }

}
