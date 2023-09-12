package com.kemalyuksel.springbootredditclone.repository;

import com.kemalyuksel.springbootredditclone.model.*;
import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import com.kemalyuksel.springbootredditclone.model.enums.NotificationType;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import com.kemalyuksel.springbootredditclone.model.enums.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final NotificationRepository notificationRepository;
    private final PostRepository postRepository;
    private final SubRedditRepository subredditRepository;
    private final UserRepository userRepository;
    private final UserSubRedditRepository userSubRedditRepository;

    public DataLoader(NotificationRepository notificationRepository, PostRepository postRepository, SubRedditRepository subredditRepository, UserRepository userRepository,
                      UserSubRedditRepository userSubRedditRepository) {
        this.notificationRepository = notificationRepository;
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.userRepository = userRepository;
        this.userSubRedditRepository = userSubRedditRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        User user2 = new User();
        User user3 = new User();

        SubReddit subReddit = new SubReddit();
        SubReddit subReddit1 = new SubReddit();

        Post post = new Post();
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();

        Notification notification = new Notification();

        UserSubReddit userSubReddit = new UserSubReddit();
        UserSubReddit userSubReddit1 = new UserSubReddit();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setAbout("Your Reddit Clone: Engage, Discuss, Discover!");
        user.setCreatedAt(LocalDateTime.now());
        user.setDateOfBirth(LocalDate.now());
        user.setDisplayName("kemstroisi");
        user.setFirstName("yüksel");
        user.setLastName("yüksel");
        user.setUsername("kemsal");
        user.setEmail(user.getUsername() + "@gmail.com");
        user.setPassword(encoder.encode("kemsal"));
        user.setRole(UserRole.USER);
        user.setLastOnline(LocalDateTime.now());
        user.setProfile_img("https://placebeard.it/640x360");
        userRepository.save(user);

        user2.setAbout("Your Reddit Clone: Engage, Discuss, Discover!");
        user2.setCreatedAt(LocalDateTime.now());
        user2.setDateOfBirth(LocalDate.now());
        user2.setDisplayName("kemstroisi");
        user2.setFirstName("yüksel");
        user2.setLastName("yüksel");
        user2.setUsername("kems");
        user2.setEmail(user2.getUsername() + "@gmail.com");
        user2.setPassword(encoder.encode("kems"));
        user2.setRole(UserRole.USER);
        user2.setLastOnline(LocalDateTime.now());
        user2.setProfile_img("https://placebeard.it/640x360");
        userRepository.save(user2);

        user3.setAbout("Your Reddit Clone: Engage, Discuss, Discover!");
        user3.setCreatedAt(LocalDateTime.now());
        user3.setDateOfBirth(LocalDate.now());
        user3.setDisplayName("Delphinouser1");
        user3.setFirstName("David");
        user3.setLastName("Sanchez");
        user3.setUsername("dsanchez");
        user3.setEmail(user3.getUsername() + "@gmail.com");
        user3.setPassword(encoder.encode("dsanchez"));
        user3.setRole(UserRole.USER);
        user3.setLastOnline(LocalDateTime.now());
        user3.setProfile_img("https://placebeard.it/640x360");
        userRepository.save(user3);

        subReddit.setAbout("This subreddit is for news and discussion about Turkey. Posts both in English and Turkish are welcome. Bu subreddit Türkiye ile ilgili haber ve sohbet ortamıdır. Türkçe veya İngilizce yazabilirsiniz.");
        subReddit.setBannerImgUrl("https://codahosted.io/packs/10796/unversioned/assets/COVER/51263089de62c756dbcbf9ec93bfcc16b4accec201508dcd480d2daa2430cd2761cb7d97e5ffb41fa8f37bbae4f3682458430a47d6cf77f6d6517f7ee9881b29b1e2daae096597bf35a6e95ce71b2717d8d2b6eda08eb53064484d9f62fe487a05451a15");
        subReddit.setSubImgUrl("https://toppng.com/uploads/preview/reddit-logo-reddit-icon-115628658968pe8utyxjt.png");
        subReddit.setCreatedAt(LocalDate.now());
        subReddit.setSubredditName("subreddit1");
        subredditRepository.save(subReddit);

        subReddit1.setAbout("This subreddit is for news and discussion about Turkey. Posts both in English and Turkish are welcome. Bu subreddit Türkiye ile ilgili haber ve sohbet ortamıdır. Türkçe veya İngilizce yazabilirsiniz.");
        subReddit1.setBannerImgUrl("https://codahosted.io/packs/10796/unversioned/assets/COVER/51263089de62c756dbcbf9ec93bfcc16b4accec201508dcd480d2daa2430cd2761cb7d97e5ffb41fa8f37bbae4f3682458430a47d6cf77f6d6517f7ee9881b29b1e2daae096597bf35a6e95ce71b2717d8d2b6eda08eb53064484d9f62fe487a05451a15");
        subReddit1.setSubImgUrl("https://toppng.com/uploads/preview/reddit-logo-reddit-icon-115628658968pe8utyxjt.png");
        subReddit1.setCreatedAt(LocalDate.now());
        subReddit1.setSubredditName("subreddit2");
        subredditRepository.save(subReddit1);

        userSubReddit.setUser(user);
        userSubReddit.setRole(SubRedditRole.OWNER);
        userSubReddit.setSubreddit(subReddit);
        userSubRedditRepository.save(userSubReddit);

        userSubReddit1.setUser(user2);
        userSubReddit1.setRole(SubRedditRole.USER);
        userSubReddit1.setSubreddit(subReddit1);
        userSubRedditRepository.save(userSubReddit1);

        UserSubReddit userSubReddit2 = new UserSubReddit();
        userSubReddit2.setUser(user3);
        userSubReddit2.setRole(SubRedditRole.USER);
        userSubReddit2.setSubreddit(subReddit1);
        userSubRedditRepository.save(userSubReddit2);


        post.setCreatedAt(LocalDateTime.now());
        post.setOwner(user);
        post.setDescription("Hello to you all, i am a beginner at api and stuffs i know how to build an simple api like todo list, simple ecommerce app without payment integration and stuffs but could anyone give me a explicit guide on what should i be mindful of while integrating api gateways, i meant for what should i create what and stuffs , i am struggling to integrate an api like payment api like ingram and stuffs i have integrated weather map api which is mainly only get request and it's kinda easy to integrate with key only but when ever i am asked to integrate api i donot even understand api, i take it very vaguely , how to understand api docs and implement accordingly , any help would be appreciated, could anyone guide me how to know what classes and what configuration i need to have to integrate api in general or ingram api in specific, any help would be highly appreciable.");
        post.setTitle("Could anyone guide me how to integrate an api to my spring app ??");
        post.setVotes(4);
        post.setDescriptionType(DescriptionType.TEXT);
        post.setSubReddit(subReddit);
        postRepository.save(post);

        post1.setCreatedAt(LocalDateTime.now());
        post1.setOwner(user);
        post1.setDescription("https://iaaspr.tmgrup.com.tr/75a822/800/452/0/0/940/531?u=https://iaspr.tmgrup.com.tr/2020/03/22/galatasaraydan-koronaviruse-ozel-logo-1584865121110.jpg");
        post1.setTitle("Galatasaray close to Wilfried Zaha transfer agreement");
        post1.setVotes(2);
        post1.setDescriptionType(DescriptionType.IMAGE);
        post1.setSubReddit(subReddit);
        postRepository.save(post1);

        Post post4 = new Post();
        post4.setCreatedAt(LocalDateTime.now());
        post4.setOwner(user2);
        post4.setDescription("https://packaged-media.redd.it/aqslmzx5f9gb1/pb/m2-res_720p.mp4?m=DASHPlaylist.mpd&v=1&e=1691301600&s=63110ff697ae6b354c691703cbb8750d64d00f1d#t=0");
        post4.setTitle("Kanuni Fransa Kralı Fransuva'ya Mektup yazıyor");
        post4.setVotes(3);
        post4.setDescriptionType(DescriptionType.VIDEO);
        post4.setSubReddit(subReddit);
        postRepository.save(post4);



        post3.setCreatedAt(LocalDateTime.now());
        post3.setOwner(user2);
        post3.setDescription("test3");
        post3.setTitle("test3");
        post3.setVotes(3);
        post3.setDescriptionType(DescriptionType.LINK);
        post3.setSubReddit(subReddit);
        postRepository.save(post3);


        post2.setCreatedAt(LocalDateTime.now());
        post2.setOwner(user2);
        post2.setDescription("https://iaaspr.tmgrup.com.tr/75a822/800/452/0/0/940/531?u=https://iaspr.tmgrup.com.tr/2020/03/22/galatasaraydan-koronaviruse-ozel-logo-1584865121110.jpg");
        post2.setTitle("test2");
        post2.setVotes(4);
        post2.setDescriptionType(DescriptionType.IMAGE);
        post2.setSubReddit(subReddit1);
        postRepository.save(post2);


        notification.setNotificationType(NotificationType.POST_REPLY);
        notification.setUser(user);
        notification.setStatus(false);
        notificationRepository.save(notification);

        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification);

        user.setNotifications(notifications);

//        List<Post> posts = new ArrayList<>();
//        posts.add(post);
//        posts.add(post1);
//        posts.add(post2);
//
//        user.setPosts(posts);


    }
}
