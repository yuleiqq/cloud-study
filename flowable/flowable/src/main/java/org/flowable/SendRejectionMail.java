package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author: yulei
 * @create: 2022-08-12
 * @Version 1.0
 **/
public class SendRejectionMail  implements JavaDelegate {

    /**
     *  类似Flowable 的一个触发器
     * @param execution
     */

    @Override
    public void execute(DelegateExecution execution) {

        //触发执行的逻辑， 按照我们在流程中的定义，应该给被拒绝的员工发送通知的邮件
        System.out.println("不好意思，你的请假申请被拒绝了!");


        //这里可以调用外部系统的API

    }


}
