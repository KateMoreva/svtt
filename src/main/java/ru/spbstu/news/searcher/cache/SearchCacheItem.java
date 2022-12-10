package ru.spbstu.news.searcher.cache;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SearchCacheItem {

    private Long id;
    private String title;
    private String url;
    private List<String> imageUrls;

}
