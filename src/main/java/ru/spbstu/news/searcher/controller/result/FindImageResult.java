package ru.spbstu.news.searcher.controller.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindImageResult {

    private final List<ImageItem> imageItems;
    private final long totalCount;

}
