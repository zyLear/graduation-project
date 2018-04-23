package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.Asset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKey(Asset record);


    Asset findByAssetNumber(@Param("assetNumber") String assetNumber);

    Integer getTotal();

    List<Asset> findByPageParam(@Param("pageParam") PageParam pageParam);
}