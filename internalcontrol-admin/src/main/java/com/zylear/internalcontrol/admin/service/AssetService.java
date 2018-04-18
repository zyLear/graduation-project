package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.Asset;

/**
 * Created by xiezongyu on 2018/4/11.
 */
public interface AssetService {

    void insert(Asset asset);

    Asset findByAssetNumber(String assetNumber);
}
