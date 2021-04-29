package com.cjhxfund.autocode.application.api;

import com.cjhxfund.autocode.wesklake.loader.CommonSourceFileLoaderManager;
import com.cjhxfund.autocode.wesklake.watcher.WatchableSourceFilePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2021/3/16.
 */
@RestController
@RequestMapping(value = "/admin")
public class GenerationAdminController {

    @Autowired
    private CommonSourceFileLoaderManager commonSourceFileLoaderManager;

    @Autowired
    private WatchableSourceFilePublisher watchableSourceFilePublisher;

    @GetMapping("/version")
    public String getVersion() {
        return "1.0.0";
    }

    @PostMapping("/reloadCommonFile")
    public String loadCommonFiles() {
        commonSourceFileLoaderManager.reload();
        return "Success";
    }

    @PostMapping("/watchFile")
    public String watchFiles() {
        watchableSourceFilePublisher.publish();
        return "Success";
    }

}
