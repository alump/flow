/*
 * Copyright 2000-2017 Vaadin Ltd.
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
package com.vaadin.flow.uitest.ui.temp;

import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

/**
 * @author Vaadin Ltd
 *
 */
public class NPEHandler extends Div
        implements HasErrorParameter<NullPointerException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
            ErrorParameter<NullPointerException> parameter) {
        getElement().setText("NPE is thrown " + event.getLocation().getPath());

        // Explicitly assign as Exception to avoid #3902
        Exception exception = parameter.getException();
        LoggerFactory.getLogger(NPEHandler.class).error(exception.getMessage(),
                exception);

        setId("no-route");
        return 500;
    }

}
