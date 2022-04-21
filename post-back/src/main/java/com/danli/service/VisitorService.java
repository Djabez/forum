package com.danli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danli.entity.Visitor;

/**
 *  服务类
 *
 * @author fanfanli
 * @date 2021/7/28
 */
public interface VisitorService extends IService<Visitor> {
    /**
     * 通过uuid查询是否存在是该uuid的访客
     *
     * @param uuid
     * @return
     */
    boolean hasUUID(String uuid);
    /**
     *Query visitors through UUID
     *
     * @param uuid
     * @return
     */


    Visitor getVisitorByUuid(String uuid);
    /**
     * get Pv
     *
     * @return pv
     */
    int getPv();
}
