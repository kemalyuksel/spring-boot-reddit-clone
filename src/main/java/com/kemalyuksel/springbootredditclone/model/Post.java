package com.kemalyuksel.springbootredditclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Size(min = 1,max = 2000,message = "Please enter at least 1 letter or max 2000 letter. ")
    private String description;
    @Enumerated(EnumType.STRING)
    private DescriptionType descriptionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int votes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="subreddit_id")
    private SubReddit subReddit;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Post post = (Post) o;
        return getId() != null && Objects.equals(getId(), post.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
