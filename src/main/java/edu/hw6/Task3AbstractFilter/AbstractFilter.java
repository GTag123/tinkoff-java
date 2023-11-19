package edu.hw6.Task3AbstractFilter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

/*
    Files.newDirectoryStream в Java используется для создания DirectoryStream,
    который представляет собой поток (или итератор) записей в указанном каталоге.
    Этот метод облегчает итерацию по файлам и подкаталогам в заданном каталоге.
 */

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    // для фильтрации записей в DirectoryStream при итерации по каталогу
    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter filter) {
        return path -> AbstractFilter.this.accept(path) && filter.accept(path);
    }
}
