//
// Created by 余雷 on 2022/4/21.
//

#include "stdio.h"

/**
 * 多个函数的使用
*/


void  bulter(void);  // 函数原型 ,也称为函数声明

int main(){

    printf("I will summon the butler function. \n ");
    bulter();
    printf("Yes. Bring me some tea and writeable DVDs. \n");

    return 0;
}



void bulter(void){  /* 函数定义开始 */
    printf("You rang , sir? \n");
}

