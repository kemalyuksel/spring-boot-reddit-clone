package com.kemalyuksel.springbootredditclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user_subreddit")
public class UserSubReddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subreddit_id")
    @ToString.Exclude
    private SubReddit subreddit;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private SubRedditRole role;

}