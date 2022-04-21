package com.danli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danli.entity.Type;
import com.danli.mapper.TypeMapper;
import com.danli.service.TypeService;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @author jmy
 * @date 2022-04-08
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
