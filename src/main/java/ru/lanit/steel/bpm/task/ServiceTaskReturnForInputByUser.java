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
import ru.lanit.steel.dao.BpmStatusForJSP;
import ru.lanit.steel.dao.SessionBpmStatusDataQuery;
import ru.lanit.steel.utils.TypeException;

@Slf4j
public class ServiceTaskReturnForInputByUser implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(BpmConst.PREFIX_TASK_LOG + " run ServiceTaskReturnForInputByUser: {}", execution);
        SessionBpmStatusDataQuery query = SessionBpmStatusDataQuery.getInstance();
        SpinJsonNode jsonData = (SpinJsonNode) execution.getVariable(BpmConst.JSON_ROOT_NAME);
        String techError = jsonData.prop(BpmConst.JSON_ERROR_DESC_PARAM).stringValue();
        BpmStatusForJSP userMessage = new BpmStatusForJSP(TypeException.INVALID_USER_INPUT_DATA, BpmConst.MESSAGE_TASK_STEEL_INVALID_DATA_ERROR, techError);
        query.addMessage("ServiceTaskReturnForInputByUser", userMessage);
    }

}




