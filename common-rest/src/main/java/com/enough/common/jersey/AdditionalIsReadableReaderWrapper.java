package com.supermap.gaf.jersey;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.WILDCARD)
public class AdditionalIsReadableReaderWrapper<T> implements MessageBodyReader<T> {

    private MessageBodyReader<T> reader;
    private IsAbleEvaluator evaluator;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return evaluator.isAbleTo(type, genericType, annotations, mediaType) && reader.isReadable(type, genericType, annotations, mediaType);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {
        return reader.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
    }
    
    public void setReader(MessageBodyReader<T> value) {
        reader = value;
    }
    
    public void setReadalbeEvaluator(IsAbleEvaluator value) {
        evaluator = value;
    }

}
