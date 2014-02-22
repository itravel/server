package com.weiminw.web.services.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.glassfish.jersey.message.internal.HttpDateFormat;
import org.glassfish.jersey.server.internal.inject.ExtractorException;
@Provider
public final class DateParamConverterProvider implements ParamConverterProvider {
	private static final String[] datePattern = {"y-M-d","yyyy-MM-dd"};
	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
		return (rawType != Date.class) ? null : new ParamConverter<T>() {

            @Override
            public T fromString(String value) {
                try {
                    return rawType.cast(DateUtils.parseDate(value, datePattern));
                } catch (ParseException ex) {
                    throw new ExtractorException(ex);
                }
            }

            @Override
            public String toString(T value) throws IllegalArgumentException {
                return value.toString();
            }
        };
	}

}
