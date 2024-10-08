package com.simbirsoft.sqlTest.helpers;

import io.cucumber.java.en.And;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookiesHelper {

    private static final Logger logger = LoggerFactory.getLogger(CookiesHelper.class);

    @And("Записать Cookies в файл")
    public static void writerReaderCookies(WebDriver driver) {

        File file = new File("Cookies.data");

        try {
            if (!file.createNewFile()) {
                file.deleteOnExit();
            }
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);

            for (Cookie ck : driver.manage().getCookies()) {
                bufferedWriter.write((ck.getName() + ";" + ck.getValue() + ";"
                        + ck.getDomain() + ";" + ck.getPath() + ";"
                        + ck.getExpiry() + ";" + ck.isSecure()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWrite.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @And("Прочитать Cookies из файла")
    public static void readerReaderCookies(WebDriver driver) {

        try {
            File readFile = new File("Cookies.data");
            FileReader fileReader = new FileReader(readFile);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String strLine;
            while ((strLine = buffReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strLine, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    if (!token.nextToken().equals("null")) {
                        expiry = Date.from(Instant.now());
                    }
                    boolean isSecure = Boolean.parseBoolean(token.nextToken());
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    logger.debug(String.valueOf(ck));
                    driver.manage().addCookie(ck);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
