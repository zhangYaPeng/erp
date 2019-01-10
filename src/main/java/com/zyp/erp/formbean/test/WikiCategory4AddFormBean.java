package com.zyp.erp.formbean.test;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class WikiCategory4AddFormBean implements Serializable {

    private static final long serialVersionUID = -4332413239874744689L;


    @NotNull
    private Integer merchantId;

    @NotBlank
    private String title;


    private Integer state = 1;

    private Integer deleted = 0;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
