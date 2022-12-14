package ru.spbstu.news.searcher.service;

import java.io.File;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.spbstu.news.searcher.database.SearchResult;
import ru.spbstu.news.searcher.indexes.SearchIndexDocument;
import ru.spbstu.news.searcher.indexes.component.IndexSearcherComponent;
import ru.spbstu.news.searcher.util.SearcherTest;

@SpringBootTest(properties = { "indexer.indexDir=./indexText/SearchResultServiceTest" })
public class SearchResultServiceTest extends SearcherTest {

    @Autowired
    private IndexSearcherComponent indexSearcherComponent;

    @Test
    public void storeArticleData_Correctly() throws Exception {
        storeTestData();
        List<SearchResult> searchResultsList = searchResultRepository.findAll();
        Assert.assertNotNull(searchResultsList);
        Assert.assertEquals(1, searchResultsList.size());
        SearchResult searchResult = searchResultsList.get(0);
        Assert.assertArrayEquals(IMAGE_URLS.toArray(), searchResult.getImageUrls().toArray());
        Assert.assertEquals(URL, searchResult.getUrl());
        Pair<List<SearchIndexDocument>, Long> documentsToCountPair = indexSearcherComponent.searchIndexDocuments(QUERY, 1);
        Long expectedTotalCount = 1L;
        Assert.assertEquals(expectedTotalCount, documentsToCountPair.getValue());
        List<SearchIndexDocument> searchIndexDocuments = documentsToCountPair.getKey();
        Assert.assertNotNull(searchIndexDocuments);
        Assert.assertEquals(1, searchIndexDocuments.size());
        SearchIndexDocument document = searchIndexDocuments.get(0);
        Assert.assertEquals(TEXT, document.getFullText());
        Assert.assertEquals(searchResult.getId(), document.getDatabaseId());
    }

    @AfterClass
    public static void close() {
        deleteDirectory(new File("./indexText/SearchResultServiceTest"));
    }

}
