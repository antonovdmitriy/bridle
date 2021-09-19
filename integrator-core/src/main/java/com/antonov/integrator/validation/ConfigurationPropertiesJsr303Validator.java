package com.antonov.integrator.validation;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ConfigurationPropertiesJsr303Validator implements Validator {

    private static final String[] VALIDATOR_CLASSES = {"javax.validation.Validator",
            "javax.validation.ValidatorFactory", "javax.validation.bootstrap.GenericBootstrap"};

    private final com.antonov.integrator.validation.ConfigurationPropertiesJsr303Validator.Delegate delegate;

    public ConfigurationPropertiesJsr303Validator(ApplicationContext applicationContext) {
        this.delegate = new com.antonov.integrator.validation.ConfigurationPropertiesJsr303Validator.Delegate(applicationContext);
    }

    @Override
    public boolean supports(Class<?> type) {
        return this.delegate.supports(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.delegate.validate(target, errors);
    }

    static boolean isJsr303Present(ApplicationContext applicationContext) {
        ClassLoader classLoader = applicationContext.getClassLoader();
        for (String validatorClass : VALIDATOR_CLASSES) {
            if (!ClassUtils.isPresent(validatorClass, classLoader)) {
                return false;
            }
        }
        return true;
    }

    private static class Delegate extends LocalValidatorFactoryBean {

        Delegate(ApplicationContext applicationContext) {
            setApplicationContext(applicationContext);
            setMessageInterpolator(new MessageInterpolatorFactory().getObject());
            afterPropertiesSet();
        }

    }

}
