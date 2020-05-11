package com.supermap.gaf.rest.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.supermap.services.rest.util.JsonConverter;

public class UrlUtil {

    public static <T> List<T> parseParamValueArray(String parameterName, Map<String, String> urlParameters, Class<T> type, List<T> defaultValue) {
        String str = (String) urlParameters.get(parameterName);
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return JsonConverter.parseJsonToList(str, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    
    public static <T> T parseParamValue(String parameterName, Map<String, String> urlParameters, Class<T> type, T defaultValue) {
        String str = (String) urlParameters.get(parameterName);
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return JsonConverter.parseJson(str, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
