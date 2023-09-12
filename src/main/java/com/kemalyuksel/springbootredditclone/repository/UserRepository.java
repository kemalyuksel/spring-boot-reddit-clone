package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username LIKE %:query%")
    List<User> findAllByUsernameContaining(String query);

}
