package com.zylear.internalcontrol.admin.config;

import com.zylear.internalcontrol.admin.filter.AdminFilter;
import com.zylear.internalcontrol.admin.filter.BidderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2018/4/28.
 */
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean adminFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("adminFilter");
        AdminFilter adminFilter = new AdminFilter();
        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/asset/*");
        registrationBean.addUrlPatterns("/bidding/*");
        registrationBean.addUrlPatterns("/budget/*");
        registrationBean.addUrlPatterns("/contract/*");
        registrationBean.addUrlPatterns("/project/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean bidderFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("adminFilter");
        BidderFilter bidderFilter = new BidderFilter();
        registrationBean.setFilter(bidderFilter);
        registrationBean.addUrlPatterns("/bid/*");
        registrationBean.setOrder(12);
        return registrationBean;
    }


}
