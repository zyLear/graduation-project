package com.zylear.internalcontrol.admin.service.impl;

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
    private AssetManager assetManager;

    @Override
    public void insert(Asset asset) {

    }
    @Autowired
    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }


}
