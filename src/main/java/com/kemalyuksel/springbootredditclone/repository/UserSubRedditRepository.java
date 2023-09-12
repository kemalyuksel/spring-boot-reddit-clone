package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.UserSubReddit;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubRedditRepository extends JpaRepository<UserSubReddit, Long> {

    List<UserSubReddit> findAllByUserUsername(String username);

    //    List<UserSubReddit> findAllByUserIdAndRole(Long userId, SubRedditRole role);
    @Query("SELECT usr.subreddit FROM UserSubReddit usr GROUP BY usr.subreddit ORDER BY COUNT(usr.user) DESC")
    List<SubReddit> findMostPopularSubreddit();

    // User, subreddit ve role göre UserSubReddit entity'sini getiren JPA metodu
    UserSubReddit findByUserAndSubredditAndRole(User user, SubReddit subreddit, SubRedditRole role);

    boolean existsBySubreddit_SubredditNameAndUser_Username(String subredditName, String userName);

}
