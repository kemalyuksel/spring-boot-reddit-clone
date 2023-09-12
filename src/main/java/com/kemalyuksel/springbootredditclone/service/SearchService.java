package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.dto.SearchResults;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SubRedditService subRedditService;
    private final UserService userService;
    private final PostService postService;
    public SearchResults search(String query) {

        SearchResults searchResults = new SearchResults();

        searchResults.setPosts(postService.getAllByQuery(query));
        searchResults.setUsers(userService.getAllByQuery(query));
        searchResults.setSubreddits(subRedditService.getAllByQuery(query));

        return searchResults;
    }

}
