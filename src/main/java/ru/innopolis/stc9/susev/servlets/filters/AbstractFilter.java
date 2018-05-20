package ru.innopolis.stc9.susev.servlets.filters;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

public abstract class AbstractFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Init filter " + this.getClass().getName());
    }

    @Override
    public void destroy() {
        logger.info("Destroy filter " + this.getClass().getName());
    }
}
