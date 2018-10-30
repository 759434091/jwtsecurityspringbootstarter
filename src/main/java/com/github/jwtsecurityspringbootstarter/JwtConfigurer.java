package com.github.jwtsecurityspringbootstarter;

import com.github.jwtsecurityspringbootstarter.config.JwtUserFactory;
import com.github.jwtsecurityspringbootstarter.filter.JwtAuthenticationFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtConfigurer<T extends HttpSecurityBuilder<T>> extends AbstractHttpConfigurer<JwtConfigurer<T>, T> {
    @Override
    public void configure(T builder) throws Exception {
        builder
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Configuration
    @ConditionalOnMissingBean(JwtUserFactory.class)
    static class JwtUserFactoryNotFound {
        public JwtUserFactoryNotFound() {
            throw new RuntimeException("JwtUserFactory Not Found");
        }
    }

    @Configuration
    @ConditionalOnMissingBean(JwtTokenUtils.class)
    static class JwtTokenUtilsNotFound {
        public JwtTokenUtilsNotFound() {
            throw new RuntimeException("JwtTokenUtils Not Found");
        }
    }
}
