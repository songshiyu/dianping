package com.song.dianping.request;

import javax.validation.constraints.NotBlank;

public class SellerRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
