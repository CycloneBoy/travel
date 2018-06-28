package com.cycloneboy.travel.entity.dto;

import lombok.*;

/**
 * @program: creditcard
 * @description: 分页查询参数DTO
 * @author: cycloneboy
 * @create:2018-03-18 11:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryDTO {
    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 查询条件
     */
    private Object query;
}
