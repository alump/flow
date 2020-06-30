/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.uitest.ui;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.flow.testutil.ChromeBrowserTest;

public class ClientSideExceptionHandlingIT extends ChromeBrowserTest {

    private static final By ERROR_LOCATOR = By.className("v-system-error");

    @Ignore
    @Test
    public void developmentModeExceptions() {
        open();
        causeException();

        String errorMessage = findElement(ERROR_LOCATOR).getText();

        Assert.assertTrue("Unexpected error message: " + errorMessage,
                Pattern.matches(".*TypeError.* property 'foo' of.*null.*",
                        errorMessage));
    }

    @Test
    @Ignore("Ignored because production mode is not activated by the servlet mapping , "
            + "see https://github.com/vaadin/flow/issues/7281")
    public void productionModeExceptions() {
        openProduction();
        causeException();

        Assert.assertFalse(isElementPresent(ERROR_LOCATOR));
    }

    private void causeException() {
        findElement(By.id(ClientSideExceptionHandlingView.CAUSE_EXCEPTION_ID))
                .click();
    }

}
