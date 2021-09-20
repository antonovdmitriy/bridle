package com.bridle.core.properties.validation;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Because ConfigurationPropertiesJsr303Validator
 * in org.springframework.boot.context.properties is package private
 */
public class ConfigurationPropertiesJsr303Validator implements Validator {

    private final ConfigurationPropertiesJsr303Validator.Delegate delegate;

    public ConfigurationPropertiesJsr303Validator(ApplicationContext applicationContext) {
        this.delegate = new ConfigurationPropertiesJsr303Validator.Delegate(applicationContext);
    }

    @Override
    public boolean supports(Class<?> type) {
        return this.delegate.supports(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.delegate.validate(target, errors);
    }

    private static class Delegate extends LocalValidatorFactoryBean {
        Delegate(ApplicationContext applicationContext) {
            setApplicationContext(applicationContext);
            setMessageInterpolator(new MessageInterpolatorFactory().getObject());
            afterPropertiesSet();
        }
    }

}
