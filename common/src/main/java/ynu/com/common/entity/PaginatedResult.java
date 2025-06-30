package ynu.com.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResult<T> {
    private List<T> list;
    private Integer totalCount;

    private String message;

    public PaginatedResult(){

    }
    public PaginatedResult(List<T> list, Integer totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }

    public PaginatedResult(List<T> list, Integer totalCount, String message) {
        this.list = list;
        this.totalCount = totalCount;
        this.message = message;
    }


}