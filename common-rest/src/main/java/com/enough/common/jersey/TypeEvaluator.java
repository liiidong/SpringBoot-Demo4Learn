package com.supermap.gaf.jersey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.google.common.collect.Sets;

public class TypeEvaluator implements IsAbleEvaluator {

    private Set<Class<?>> types = Collections.emptySet();
    
    public void setTypes(Set<Class<?>> value) {
        types = Sets.newHashSet(value);
    }

    @Override
    public boolean isAbleTo(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return types.contains(type);
    }

}
