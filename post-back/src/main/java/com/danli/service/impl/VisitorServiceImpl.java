package com.danli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danli.entity.Visitor;
import com.danli.mapper.VisitorMapper;
import com.danli.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @author jmy
 * @date 2022-04-08
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {
    @Autowired
    VisitorMapper visitorMapper;

    /**
     *
     *
     * @param uuid
     * @return
     */
    @Override
    public boolean hasUUID(String uuid) {
        return visitorMapper.hasUUID(uuid) == 0 ? false : true;
    }

    /**
     * use uuid find visitor
     *
     * @param uuid
     * @return
     */
    @Override
    public Visitor getVisitorByUuid(String uuid){
        return visitorMapper.selectByUuid(uuid);
    }

    /**
     * get Pv
     *
     * @return pv
     */
    @Override
    public int getPv(){
        return  visitorMapper.getPv();
    }


}
