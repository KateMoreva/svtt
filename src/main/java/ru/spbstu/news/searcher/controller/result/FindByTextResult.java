package ru.spbstu.news.searcher.controller.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindByTextResult {

    private List<SearchItem> searchItems;
    private long totalCount;

}
