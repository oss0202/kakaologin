package com.kakao.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nm;

    @Column(nullable = false)
    private String phone;

    @Builder
    public User(Long id, String nm, String phone) {
        this.id = id;
        this.nm = nm;
        this.phone = phone;
    }
}
