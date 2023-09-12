package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.Post;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // subreddite ait bütün postlar
    List<Post> findAllBySubRedditSubredditName(String subredditName);

    // subreddite ait bütün postlar
    Post findBySubReddit_SubredditNameAndId(String subredditName, Long id);

    // kullanıcıya ait bütün postlar
    List<Post> findAllByOwner(User user);

    // Kullanıcının katıldığı subredditlerin postlarına erişim metodu
    List<Post> findAllBySubRedditIn(List<SubReddit> subreddits);

    // Bir subreddit'in altındaki popüler gönderileri getiren metot
    @Query("SELECT p FROM Post p WHERE p.subReddit.subredditName = :subredditName ORDER BY p.votes DESC")
    List<Post> findPopularPostsBySubreddit(@Param("subredditName") String subredditName);

    // Bir subreddit'in altındaki en son gönderileri getiren metot
    @Query("SELECT p FROM Post p WHERE p.subReddit.subredditName = :subredditName ORDER BY p.createdAt DESC")
    List<Post> findRecentPostsBySubreddit(@Param("subredditName") String subredditName);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:query% OR p.description LIKE %:query%")
    List<Post> findAllByTitleOrDescriptionContaining(String query);
}
