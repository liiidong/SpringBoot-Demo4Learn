package com.enough.serviceconfigmgt.controller;

import com.enough.common.model.ReturnResult;
import com.enough.configmanager.dto.ConfigPropertyDTO;
import com.enough.configmanager.service.ConfigPropertyService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/30
 */
@RestController
@RequestMapping("/configmgt")
public class ConfigPropertiesController {

    @Autowired
    private ConfigPropertyService configPropertyService;

    @PostMapping("/query")
    public ReturnResult <List> query(@RequestBody ConfigPropertyDTO searchParam) {
        List <ConfigPropertyDTO> configPropertyDTOS = configPropertyService.queryConfigProperties(searchParam);
        if (CollectionUtils.isNotEmpty(configPropertyDTOS)) {
            return ReturnResult.success(List.class).data(configPropertyDTOS).msg("查询成功！").build();
        }

        return ReturnResult.failed(List.class).msg("查询失败！").build();
    }

    @GetMapping
    public ReturnResult <List> getAllConfigProperties() {
        List <ConfigPropertyDTO> configPropertyDTOS = configPropertyService.getAllConfigProperties();
        if (CollectionUtils.isNotEmpty(configPropertyDTOS)) {
            return ReturnResult.success(List.class).data(configPropertyDTOS).msg("查询成功！").build();
        }

        return ReturnResult.failed(List.class).msg("查询失败！").build();
    }

    @GetMapping("/{id}")
    public ReturnResult <ConfigPropertyDTO> query(@PathVariable String id) {
        ConfigPropertyDTO configPropertyDTO = configPropertyService.getConfigProperty(id);
        if (configPropertyDTO != null) {
            return ReturnResult.success(ConfigPropertyDTO.class).data(configPropertyDTO).msg("查询成功！").build();
        }

        return ReturnResult.failed(ConfigPropertyDTO.class).msg("查询失败！").build();
    }

    @PostMapping("/add")
    public ReturnResult <Boolean> add(@RequestBody ConfigPropertyDTO configPropertyDTO) {
        if (configPropertyService.addConfigProperty(configPropertyDTO)) {
            return ReturnResult.success(Boolean.class).data(true).msg("添加成功！").build();
        }
        return ReturnResult.failed(Boolean.class).msg("添加失败！").build();
    }

    @DeleteMapping("/{id}")
    public ReturnResult <Boolean> delete(@PathVariable String id) {
        if (configPropertyService.deleteConfigProperty(id)) {
            return ReturnResult.success(Boolean.class).data(true).msg("添加成功！").build();
        }
        return ReturnResult.failed(Boolean.class).msg("添加失败！").build();
    }
}
