package com.supermap.gaf.jersey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;

public interface IsAbleEvaluator {
    boolean isAbleTo(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType);
}
