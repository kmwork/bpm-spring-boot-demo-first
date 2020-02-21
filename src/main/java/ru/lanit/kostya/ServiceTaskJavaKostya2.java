/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.lanit.kostya;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.kostya.dao.SessionBpmStatusData;

import static org.camunda.spin.Spin.JSON;

public class ServiceTaskJavaKostya2 implements JavaDelegate {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private SessionBpmStatusData sessionBpmStatusData = SessionBpmStatusData.getInstance();

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        SpinJsonNode jsonCustomer = JSON("{ \"name\": -1, \"kostyaAgeBPM\": -1}");

        sessionBpmStatusData.doEngineLock();
        jsonCustomer.prop("kostyaAgeBPM", Integer.valueOf(execution.getVariable("messageVar_KostyaAge").toString()));
        jsonCustomer.prop("name", execution.getVariable("messageVar_NameUser").toString());

        LOG.info("JSON customer: {}", jsonCustomer);
        execution.setVariable("customer", jsonCustomer);


    }

}




