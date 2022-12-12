package ru.spbstu.news.searcher.indexes.indexer;

import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import org.apache.lucene.index.Term;
import org.jetbrains.annotations.NotNull;

import ru.spbstu.news.searcher.indexes.SearchIndexDocument;
import ru.spbstu.news.searcher.indexes.exceptions.LuceneIndexingException;
import ru.spbstu.news.searcher.indexes.exceptions.LuceneOpenException;

public interface IndexerComponent {

    void open(@NotNull String indexDir) throws LuceneIndexingException, IOException;

    void index(@NotNull SearchIndexDocument searchIndexDocument) throws LuceneOpenException;

    void delete(@NotNull Term term);

    boolean commit() throws IOException;

    void close();

    String dir() throws OperationNotSupportedException;

}
