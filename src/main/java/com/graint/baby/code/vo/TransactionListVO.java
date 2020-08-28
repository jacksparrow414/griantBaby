package com.graint.baby.code.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 事务ListVO.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionListVO {
    
    private Double price;
    
    private String status;
    
    private String orderNo;
    
}
