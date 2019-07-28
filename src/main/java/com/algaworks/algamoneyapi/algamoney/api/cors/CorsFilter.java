package com.algaworks.algamoneyapi.algamoney.api.cors;

import com.algaworks.algamoneyapi.algamoney.api.config.property.AlgamoneyApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

    @Autowired
    private AlgamoneyApiProperty algamoneyApiProperty;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse respose = (HttpServletResponse) resp;

        respose.setHeader("Access-Control-Allow-Origin", algamoneyApiProperty.getOrigemPermitida());
        respose.setHeader("Access-Control-Allow-Credentials", "true");

        if("OPTIONS".equals(request.getMethod()) && algamoneyApiProperty.getOrigemPermitida().equals(request.getHeader("Origin"))){
            respose.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            respose.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            respose.setHeader("Access-Control-Max-Age", "3600");

            respose.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
