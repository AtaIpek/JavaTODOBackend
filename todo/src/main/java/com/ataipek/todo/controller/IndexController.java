package com.ataipek.todo.controller;

import com.ataipek.todo.config.SwaggerConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class IndexController {

    private final SwaggerConfig swaggerConfig;

    private final String servletPath;

    @Autowired
    public IndexController(SwaggerConfig swaggerConfig, @Value("${server.servlet.context-path}") String servletPath) {
        this.swaggerConfig = swaggerConfig;
        this.servletPath = servletPath;
    }

    @GetMapping
    public RedirectView redirectToSwagger(HttpServletRequest request) {
        return new RedirectView(String.format("http://%s%s%s/index.html", request.getServerName() + ":" + request.getServerPort(), servletPath, swaggerConfig.getSwaggerPath()));
    }

}
