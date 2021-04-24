package com.cjhxfund.autocode.application.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2021/3/16.
 */
@RestController
@RequestMapping(value = "/admin")
public class GenerationAdminController {

    @GetMapping("/version")
    public String getVersion() {
        return "1.0.0";
    }

}
