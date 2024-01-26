package springboottesting.pizza.helper;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class Pagination<T> {
    private final int page;
    private final int size;
    private final long total;
    private final List<T> data;

    private Pagination(List<T> data, int page, int size, long total) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public static <T> Pagination<T> of(Page<T> page) {
        return new Pagination<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}
