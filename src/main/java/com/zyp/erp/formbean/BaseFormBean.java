package com.zyp.erp.formbean;



public class BaseFormBean {

    private Integer pageNum = 1; // 页码

    private Integer pageSize = 10;//

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
