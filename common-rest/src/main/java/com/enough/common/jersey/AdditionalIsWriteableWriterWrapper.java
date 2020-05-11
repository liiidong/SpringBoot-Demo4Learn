package com.supermap.gaf.jersey;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.WILDCARD)
public class AdditionalIsWriteableWriterWrapper<T> implements MessageBodyWriter<T> {

    private MessageBodyWriter<T> writer;
    private IsAbleEvaluator evaluator;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return evaluator.isAbleTo(type, genericType, annotations, mediaType) && writer.isWriteable(type, genericType, annotations, mediaType);
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return writer.getSize(t, type, genericType, annotations, mediaType);
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {
        writer.writeTo(t, type, genericType, annotations, mediaType, httpHeaders, entityStream);
    }
    
    public void setWriter(MessageBodyWriter<T> value) {
        writer = value;
    }
    
    public void setWriteableEvaluator(IsAbleEvaluator value) {
        evaluator = value;
    }

}
