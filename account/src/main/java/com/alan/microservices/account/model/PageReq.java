package com.alan.microservices.account.model;

import lombok.Data;

@Data
public class PageReq {
    int pageNum;
    int pageSize;
}
