package com.alan.microservices.commons.account.domain;

import lombok.Data;

@Data
public class PageReq {
    int pageNum;
    int pageSize;
}
