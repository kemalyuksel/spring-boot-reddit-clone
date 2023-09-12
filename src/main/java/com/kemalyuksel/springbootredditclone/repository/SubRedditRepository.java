package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.Post;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {

    SubReddit findBySubredditName(String subredditName);
    @Query("SELECT s.subredditName FROM SubReddit s")
    List<String> getAllSubredditName();

    @Query("SELECT s FROM SubReddit s WHERE s.subredditName LIKE %:query% ")
    List<SubReddit> findAllByTitleContaining(String query);

}
