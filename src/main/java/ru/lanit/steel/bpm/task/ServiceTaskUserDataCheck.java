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
import ru.lanit.steel.utils.AppException;
import ru.lanit.steel.utils.TypeException;
import ru.lanit.steel.utils.ValueParser;

import static org.camunda.spin.Spin.JSON;

@Slf4j
public class ServiceTaskUserDataCheck implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(BpmConst.PREFIX_TASK_LOG + "run ServiceTaskUserDataCheck: {}", execution);
        SpinJsonNode jsonData = JSON(String.format("{ \"%s\" : -1, \"%s\" : -1, \"%s\" : -1, \"%s\" : -1}", BpmConst.JSON_STEEL_VALUE_PARAM, BpmConst.JSON_STEEL_MODEL_PARAM, BpmConst.JSON_ERROR_CODE_PARAM, BpmConst.JSON_ERROR_DESC_PARAM));

        String strSteelModel = null;
        String strPercent = null;
        try {

            //проверка на марку стали
            strSteelModel = (String) execution.getVariable(BpmConst.MESSAGE_PARAM_SteelModelName);
            ValueParser.checkValidValue(strSteelModel, BpmConst.LABEL_SteelModelName_FOR_USER, BpmConst.VALID_STEEL_SET);
            jsonData.prop(BpmConst.JSON_STEEL_MODEL_PARAM, strSteelModel);

            //проверка ввода процестов стали
            strPercent = (String) execution.getVariable(BpmConst.MESSAGE_PARAM_SteelPercentValue);
            int intPercent = ValueParser.parseInt(strPercent, BpmConst.LABEL_SteelPercentValue_FOR_USER);
            jsonData.prop(BpmConst.JSON_STEEL_VALUE_PARAM, intPercent);

            jsonData.prop(BpmConst.JSON_ERROR_CODE_PARAM, BpmConst.JSON_ERROR_CODE_SUCCESS_VALUE);
            jsonData.prop(BpmConst.JSON_ERROR_DESC_PARAM, TypeException.OK.getDescError());

        } catch (Exception ex) {
            String args = "strSteelModel= " + strSteelModel + ", strPercent=" + strPercent;

            AppException appEx = ex instanceof AppException ? (AppException) ex : new AppException(TypeException.SYSTEM_ERROR, "Ошибка в ServiceTaskUserDataCheck", args, ex);
            jsonData.prop(BpmConst.JSON_ERROR_DESC_PARAM, appEx.getMsg());
            jsonData.prop(BpmConst.JSON_ERROR_CODE_PARAM, appEx.getType().toString());

            jsonData.prop(BpmConst.JSON_STEEL_VALUE_PARAM, BpmConst.JSON_BPM_ERROR_VALUE);
            jsonData.prop(BpmConst.JSON_STEEL_MODEL_PARAM, BpmConst.JSON_BPM_ERROR_VALUE);

        }

        //формирование json в BPM модель
        log.info("JSON for BPM: {}", jsonData);
        execution.setVariable(BpmConst.JSON_ROOT_NAME, jsonData);

    }

}




