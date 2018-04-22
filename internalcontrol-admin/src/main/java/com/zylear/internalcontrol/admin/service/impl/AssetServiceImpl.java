package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.AssetMapper;
import com.zylear.internalcontrol.admin.domain.Asset;
import com.zylear.internalcontrol.admin.manager.AssetManager;
import com.zylear.internalcontrol.admin.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class AssetServiceImpl implements AssetService {

    private AssetMapper assetMapper;

    @Override
    public void insert(Asset asset) {
        assetMapper.insert(asset);
    }

    @Override
    public Asset findByAssetNumber(String assetNumber) {
        return assetMapper.findByAssetNumber(assetNumber);
    }

    @Autowired
    public void setAssetMapper(AssetMapper assetMapper) {
        this.assetMapper = assetMapper;
    }
}
