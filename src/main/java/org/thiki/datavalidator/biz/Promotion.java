package org.thiki.datavalidator.biz;

import java.util.Date;

public class Promotion {

    private long id;
    private long productId;
    private String description;
    private int status;
    private Date beginTime;
    private Date endTime;
    private int descount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDescount() {
        return descount;
    }

    public void setDescount(int descount) {
        this.descount = descount;
    }
}
