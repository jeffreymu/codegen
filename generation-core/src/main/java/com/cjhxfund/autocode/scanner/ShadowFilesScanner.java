package com.cjhxfund.autocode.scanner;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.model.ShadowType;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class ShadowFilesScanner {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @Autowired
    private DataCacheManager cacheManager;

    private String shadowsFilePath = null;

    @PostConstruct
    private void init() {
        log.info("++++++++++++Scan all shadow files currently++++++++++++");
        shadowsFilePath = Class.class.getClass().getResource("/").getPath();
        try {
            scanShadowFiles(shadowsFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("common_offset##" + cacheManager.get(ShadowType.COMMON_SHADOW.name()).toString());
        log.info("table_offset##" + cacheManager.get(ShadowType.TABLE_SHADOW.name()).toString());
        log.info("interface_offset##" + cacheManager.get(ShadowType.INTERFACE_SHADOW.name()).toString());
        log.info("++++++++++++++++++++++++++++++++++++");
    }

    public void scanShadowFiles(String filePath) throws IOException {
        List<File> fileList = (List<File>) FileUtils.listFiles(
                new File(filePath + File.separator + "offset"), null, false);
        if (!fileList.isEmpty()) {
            String today = LocalDate.now().toString();
            for (File f : fileList) {
                String latestOffset = FileUtils.readFileToString(f, Charset.defaultCharset());
                if (StringUtils.isEmpty(latestOffset)) {
                    updateOffsetOfShadow(ShadowType.valueOf(f.getName()), today);
                } else {
                    cacheManager.put(f.getName(), FileUtils.readFileToString(f, Charset.defaultCharset()));
                }
            }
        }
    }

    /**
     * update the offset of shadow file
     * @param type
     * @param currentDate
     * @throws IOException
     */
    public void updateOffsetOfShadow(ShadowType type, String currentDate) throws IOException {
        StringBuilder shadowBuilder = new StringBuilder();
        shadowBuilder.append(shadowsFilePath).append(File.separator)
                .append("offset").append(File.separator)
                .append(type.name());
        Files.write(currentDate, new File(shadowBuilder.toString()), Charsets.UTF_8);
        cacheManager.put(type.name(), currentDate);
    }


}
