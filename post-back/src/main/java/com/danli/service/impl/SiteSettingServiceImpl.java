package com.danli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danli.entity.SiteSetting;
import com.danli.mapper.SiteSettingMapper;
import com.danli.service.SiteSettingService;
import org.springframework.stereotype.Service;


@Service
public class SiteSettingServiceImpl extends ServiceImpl<SiteSettingMapper, SiteSetting> implements SiteSettingService {

}
