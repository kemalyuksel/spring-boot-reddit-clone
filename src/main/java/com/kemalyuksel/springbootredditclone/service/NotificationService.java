package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.mapper.UserMapper;
import com.kemalyuksel.springbootredditclone.model.Notification;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.enums.NotificationType;
import com.kemalyuksel.springbootredditclone.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    //Send notification when someone reply our post or comment
    public Notification createNotification(String username, NotificationType notificationType) {

        Notification notification = new Notification();
        notification.setStatus(false);
        notification.setNotificationType(notificationType);
        notification.setUser(UserMapper.MAPPER.UserInfoDtoToUser(userService.getUserByUsername(username)));

        return notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotificationsByUsername(String username) {
        return notificationRepository.findByUserUsernameAndStatus(username, false);
    }

    public List<Notification> getAllNotificationsByUsername(String username) {
        return notificationRepository.findByUserUsername(username);
    }


}
