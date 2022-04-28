//
// Created by 余雷 on 2022/4/27.
//

/**
 * 指针和字符串
 */

#include "stdio.h"

int main(){

    const char * mesg = "Don't be a fool";
    const char * copy;
    copy=mesg;

    printf("%s\n",copy);


    // & 打印指针的地址，  value： 显示指针的值，就是它存储的地址。

    printf("mesg = %s ; &mesg = %p; value = %p\n",mesg,&mesg, mesg);

    printf("copy = %s ; &copy = %p; value = %p\n",copy,&copy, copy);




}
