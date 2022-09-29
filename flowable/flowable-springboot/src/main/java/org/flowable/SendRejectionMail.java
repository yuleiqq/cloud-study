package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author: yulei
 * @create: 2022-09-23
 * @Version 1.0
 **/
public class SendRejectionMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {

        System.out.println("拒绝你的请求...");

    }
}
