package com.supermap.gaf.jersey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;

public class NotEvaluator implements IsAbleEvaluator {

    private IsAbleEvaluator wrapped;

    @Override
    public boolean isAbleTo(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return !wrapped.isAbleTo(type, genericType, annotations, mediaType);
    }
    
    public void setWrapped(IsAbleEvaluator value) {
        wrapped = value;
    }

}
