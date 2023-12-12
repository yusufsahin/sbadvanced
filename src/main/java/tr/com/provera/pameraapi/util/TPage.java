package tr.com.provera.pameraapi.util;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Custom pagination wrapper class to encapsulate pagination and sorting information along with content.
 * @param <T> The type of content in the page.
 */
@Data
public class TPage<T> {
    private int number;
    private int size;
    private Sort sort;
    private int totalPages;
    private Long totalElements;
    private List<T> content;

    /**
     * Sets the pagination and content data from a Spring Page object.
     * @param page The source Page object.
     * @param list The content list.
     */
    public void setPageData(Page<?> page, List<T> list) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.sort = page.getSort();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.content = list;
    }
}
