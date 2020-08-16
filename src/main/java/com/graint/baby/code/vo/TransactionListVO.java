package com.graint.baby.code.vo;

/**
 * @author jacksparrow414
 * @date 2020-03-14
 * @description: TODO
 */
public class TransactionListVO {
    private Double price;
    private String status;
    private String order_no;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    @Override
    public String toString() {
        return "TransactionListVO{" +
                "price=" + price +
                ", status=" + status +
                ", order_no='" + order_no + '\'' +
                '}';
    }

    public TransactionListVO(Double price, String status, String order_no) {
        this.price = price;
        this.status = status;
        this.order_no = order_no;
    }
}
