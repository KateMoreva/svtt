package ru.spbstu.news.searcher.controller.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class FindImageResult {

    private List<ImageItem> imageItems;
    private long totalCount;

}
