package com.simbirsoft.sqlTest.tests;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class JavaScriptExecutorTest extends BaseTest {

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта sql-ex.ru")
    @Feature("Фокус")
    @Story("Убрать фокус из поля ввода")
    @Test
    public void testDropFocus() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.dropFocus()).isTrue();
    }

    @Severity(SeverityLevel.MINOR)
    @Epic("Проверка сайта sql-ex.ru")
    @Feature("Скролл")
    @Story("Наличие скролла")
    @Test
    public void testIsScrollHeight() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(baseSQLPage.isScrollHeight()).isTrue();
    }

}