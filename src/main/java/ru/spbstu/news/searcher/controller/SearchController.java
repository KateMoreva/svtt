package ru.spbstu.news.searcher.controller;


import java.util.List;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.spbstu.news.searcher.controller.request.FindRequest;
import ru.spbstu.news.searcher.controller.request.ItemToIndex;
import ru.spbstu.news.searcher.controller.result.FindByTextResult;
import ru.spbstu.news.searcher.controller.result.FindImageResult;
import ru.spbstu.news.searcher.indexes.exceptions.LuceneOpenException;
import ru.spbstu.news.searcher.scanner.NewsCrawlerController;
import ru.spbstu.news.searcher.service.SearchResultService;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchResultService searchResultService;
    private final NewsCrawlerController newsCrawlerController;

    @Autowired
    public SearchController(@NotNull SearchResultService searchResultService,
                            @NotNull NewsCrawlerController newsCrawlerController) {
        Validate.notNull(searchResultService);
        Validate.notNull(newsCrawlerController);
        this.searchResultService = searchResultService;
        this.newsCrawlerController = newsCrawlerController;
    }

    @PostMapping("/{page}")
    public ResponseEntity<FindByTextResult> findByText(@PathVariable(name = "page") Integer page,
                                                       @RequestBody FindRequest findRequest) {
        Validate.notNull(page);
        Validate.notNull(findRequest);
        try {
            logger.info("findByText, request: [{}]", findRequest);
            FindByTextResult textResult = searchResultService.findByText(findRequest.getQuery(), page);
            return ResponseEntity.ok(textResult);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/similar")
    public ResponseEntity<List<String>> findSimilar(@NotNull @RequestBody FindRequest findRequest) {
        Validate.notNull(findRequest);
        try {
            List<String> similarItems = searchResultService.findSimilar(findRequest.getQuery());
            return ResponseEntity.ok(similarItems);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/image")
    public ResponseEntity<FindImageResult> findImages(@RequestBody FindRequest imagesRequest) {
        Validate.notNull(imagesRequest);
        Validate.notNull(imagesRequest.getQuery());
        try {
            FindImageResult imageResults = searchResultService.findImages(imagesRequest.getPage(), imagesRequest.getQuery());
            return ResponseEntity.ok(imageResults);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/index")
    public ResponseEntity<?> index(@NotNull @RequestBody ItemToIndex itemToIndex) {
        Validate.notNull(itemToIndex);
        try {
            searchResultService.index(itemToIndex);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (LuceneOpenException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/crawl")
    public ResponseEntity<?> crawl() {
        try {
            newsCrawlerController.launchCrawling();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
