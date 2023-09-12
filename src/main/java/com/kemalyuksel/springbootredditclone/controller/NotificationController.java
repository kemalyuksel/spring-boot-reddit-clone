package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.model.Notification;
import com.kemalyuksel.springbootredditclone.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotificationsByUsername(@AuthenticationPrincipal UserDetails userDetails) {

        List<Notification> unreadNotifications = notificationService.getUnreadNotificationsByUsername(userDetails.getUsername());
        return ResponseEntity.ok(unreadNotifications);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotificationsByUsername(@AuthenticationPrincipal UserDetails userDetails) {
        List<Notification> allNotifications = notificationService.getAllNotificationsByUsername(userDetails.getUsername());
        return ResponseEntity.ok(allNotifications);
    }

}
