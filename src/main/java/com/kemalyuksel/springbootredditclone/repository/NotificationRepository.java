package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    List<Notification> findByUserUsernameAndStatus(String username, boolean status);

    List<Notification> findByUserUsername(String username);

}
