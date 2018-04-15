package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.ProjectBid;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public interface ProjectBidService {

    ProjectBid findByBidNumber(String bidNumber);

    void insert(ProjectBid projectBid);

    ProjectBid findByFilePath(String filePath);
}
