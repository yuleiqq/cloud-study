package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * @author: yulei
 * @create: 2022-09-23
 * @Version 1.0
 **/
public class CallExternalSystemDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {


        Map<String, Object> variables = execution.getVariables();

        System.out.println("调用外部系统服务");

    }
}
