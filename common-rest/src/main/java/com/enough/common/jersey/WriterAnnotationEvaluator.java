package com.supermap.gaf.jersey;

public class WriterAnnotationEvaluator extends AbstractAnnotationEvaluator<Writer> {

    protected WriterAnnotationEvaluator() {
        super(Writer.class);
    }

    @Override
    protected boolean isAbleToByAnnotation(Writer annotation, Class<?> clz) {
        return annotation.value().equals(clz);
    }

}
