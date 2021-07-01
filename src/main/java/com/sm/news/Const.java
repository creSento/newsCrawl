package com.sm.news;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Const {
    static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    static final String WEB_DRIVER_PATH ="‪C:\\chromedriver91.exe";
    static final Path WEB_DRIVER_PATH = Paths.get(System.getProperty("user.dir"), "src/main/webapp/WEB-INF/resources/chromedriver91.exe");
    static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
}
