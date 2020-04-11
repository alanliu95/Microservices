package com.alan.microservices.commons.account.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserList {
    int pageNum;
    int pageSize;
    int totalSize;
    List<SysUser> content;
    public UserList(PageReq pageReq){
        this.pageNum=pageReq.pageNum;
        this.pageSize=pageReq.pageSize;
    }
}
