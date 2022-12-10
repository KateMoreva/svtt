package ru.spbstu.news.searcher.controller.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemToIndex {

    private final String url;
    private final List<String> imageUrls;
    private final String text;

}
