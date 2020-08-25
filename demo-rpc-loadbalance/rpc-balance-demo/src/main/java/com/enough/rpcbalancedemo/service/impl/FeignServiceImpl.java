package com.enough.rpcbalancedemo.service.impl;

import com.enough.rpcbalancedemo.service.FeignService;
import org.springframework.stereotype.Service;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/14
 */
@Service
public class FeignServiceImpl implements FeignService {

    @Override
    public String getServerUrl() {
        return "openFenginHystrix服务器异常！";
    }
}