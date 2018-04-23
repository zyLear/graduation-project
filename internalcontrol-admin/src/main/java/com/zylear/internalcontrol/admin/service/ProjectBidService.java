package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.ProjectBid;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public interface ProjectBidService {

    ProjectBid findByBidNumber(String bidNumber);

    void insert(ProjectBid projectBid);

    ProjectBid findByFilePath(String filePath);

    List<ProjectBid> findByStatus(Integer bidStatus);

    List<ProjectBid> findByPageParam(PageParam pageParam);

    List<ProjectBid> findByNumberAndPageParam(String biddingNumber, PageParam pageParam);

    Integer getTotal();

    Integer getTotalByBiddingNumber(String biddingNumber);

    void updateStatusByBidNumber(String bidNumber, Integer status);

    void updateStatusByBiddingNumber(String biddingNumber, Integer status);
}
