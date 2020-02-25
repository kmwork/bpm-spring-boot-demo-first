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
package ru.lanit.steel.bpm.task;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import ru.lanit.steel.bpm.config.BpmConst;

import static org.camunda.spin.Spin.JSON;

@Slf4j
public class ServiceTaskUserDataCheck implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(BpmConst.PREFIX_TASK_LOG + "run ServiceTaskUserDataCheck: {}", execution);
        SpinJsonNode jsonData = JSON("{ \"steelModelName\": -1, \"steelPercentValueBPM\": -1}");

        jsonData.prop("steelPercentValueBPM", Integer.valueOf(execution.getVariable(BpmConst.MESSAGE_PARAM_SteelPercentValue).toString()));
        jsonData.prop("name", execution.getVariable(BpmConst.MESSAGE_PARAM_SteelModelName).toString());

        log.info("JSON customer: {}", jsonData);
        execution.setVariable("steel", jsonData);


    }

}



