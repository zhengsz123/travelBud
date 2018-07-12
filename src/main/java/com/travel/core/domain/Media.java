package com.travel.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "image")
public class Media implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authority_id_seq")
    @SequenceGenerator(name = "authority_id_seq",sequenceName = "authority_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "url")
    private String imageUrl;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
