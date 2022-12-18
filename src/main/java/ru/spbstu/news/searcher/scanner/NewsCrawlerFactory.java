package ru.spbstu.news.searcher.scanner;

import org.jetbrains.annotations.NotNull;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import ru.spbstu.news.searcher.service.SearchResultService;

@Component
public class NewsCrawlerFactory implements CrawlController.WebCrawlerFactory<NewsCrawler> {
    @NotNull
    public final CrawlerConfig crawlerConfig;
    @NotNull
    private final SearchResultService searchResultService;

    public NewsCrawlerFactory(@NotNull final SearchResultService searchResultService,
                              @NotNull final CrawlerConfig crawlerConfig) {
        Validate.notNull(searchResultService);
        Validate.notNull(crawlerConfig);
        this.searchResultService = searchResultService;
        this.crawlerConfig = crawlerConfig;
    }

    @Override
    public NewsCrawler newInstance() {
        return new NewsCrawler(searchResultService, crawlerConfig);
    }
}
