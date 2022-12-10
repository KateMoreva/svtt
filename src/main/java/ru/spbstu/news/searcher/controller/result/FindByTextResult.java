package ru.spbstu.news.searcher.controller.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FindByTextResult {

    private final List<SearchItem> searchItems;
    private final long totalCount;

}
