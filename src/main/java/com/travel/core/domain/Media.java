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
    private String url;

    @Column(name = "s3Key")
    private String s3Key;

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

//    @NotNull
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "users_id")
//    private User user;
//    public long getId() {
//        return id;
//    }
//    public User getUser() {
//        return user;
//    }
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
