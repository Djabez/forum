package com.danli.common.lang.vo;

import lombok.Data;

import java.util.List;

/**
 * To the front end of the paging view object
 *
 * @author Yicong Wang
 * @date  2022
 */
@Data
public class PageResult {
    private List<BlogInfo> records;
    private int total;
    private int size;
    private int current;
    private List<String> orders;
    private boolean searchCount;
    private int pages;


}
