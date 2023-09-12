package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.dto.SearchResults;
import com.kemalyuksel.springbootredditclone.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    @GetMapping
    public ResponseEntity<SearchResults> search(@RequestParam String query) {
        SearchResults results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}
