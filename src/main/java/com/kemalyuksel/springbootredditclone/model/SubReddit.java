package com.kemalyuksel.springbootredditclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SubReddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String subredditName;
    private String about;
    @Column(length = 512)
    private String bannerImgUrl;
    @Column(length = 512)
    private String subImgUrl;
    @Column(length = 512)
    private String themeImgUrl;
    private LocalDate createdAt;


    @JsonIgnore
    @OneToMany(mappedBy = "subReddit")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SubReddit subReddit = (SubReddit) o;
        return getId() != null && Objects.equals(getId(), subReddit.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
