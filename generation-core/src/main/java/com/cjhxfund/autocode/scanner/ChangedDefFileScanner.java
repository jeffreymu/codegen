package com.cjhxfund.autocode.scanner;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class ChangedDefFileScanner {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    private Multimap<String, File> commonOfToday = ArrayListMultimap.create();
    private Multimap<String, File> tablesOfToday = ArrayListMultimap.create();
    private Multimap<String, File> interfaceOfToday = ArrayListMultimap.create();

    @PostConstruct
    private void init() {
        log.info("++++++++++++Scan all changed files Today++++++++++++");
        scanFiles(westLakeHome + File.separator +
                ScannableDirFiles.DEF_COMMON_FILE_PATH, DirCategory.COMMON);
        scanFiles(westLakeHome + File.separator +
                ScannableDirFiles.DEF_DATA_TABLE_FILE_PATH, DirCategory.TABLE);
        scanFiles(westLakeHome + File.separator +
                ScannableDirFiles.DEF_DATA_INTERFACE_FILE_PATH, DirCategory.INTERFACE);
        log.info("++++++++++++++++++++++++++++++++++++");
    }

    public void scanFiles(String filePath, DirCategory category) {
        log.info("Scanning {}", filePath);
        List<File> fileList = (List<File>) FileUtils.listFiles(
                new File(filePath), new String[]{"xml"}, true);
        if (!fileList.isEmpty()) {
            String today = LocalDate.now().toString();
            for (File f : fileList) {
                if (getModifiedTime(f).equals(today)) {
                    log.info("Lastly modified file [{}] at {}", f.getName(), getModifiedTime(f));
                    if (category == DirCategory.TABLE)
                        tablesOfToday.put(today, f);
                    else if (category == DirCategory.INTERFACE)
                        interfaceOfToday.put(today, f);
                    else if (category == DirCategory.COMMON)
                        commonOfToday.put(today, f);
                }
            }
        }
    }

    public String getModifiedTime(File file) {
        Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }
}
