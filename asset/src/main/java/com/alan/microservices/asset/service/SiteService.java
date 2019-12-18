package com.alan.microservices.asset.service;

import com.alan.microservices.asset.dao.SiteDao;
import com.alan.microservices.commons.asset.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {
    @Autowired
    SiteDao siteDao;

    public List<Site> getAll() {
        return siteDao.getAll();
    }

    public Site getById(Long id) {
        return siteDao.getById(id);

    }
}
