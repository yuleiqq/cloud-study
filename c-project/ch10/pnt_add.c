//
// Created by 余雷 on 2022/4/26.
//

/**
 * 指定地址
 */

#include "stdio.h"
#define SIZE 4

int main(void){

    short dates[SIZE];
    short * pti;
    short index;
    double bills[SIZE];
    double * ptf;

    pti = dates;  // 将数组地址赋给指针
    ptf = bills;

    printf("%23s  %15s \n", "short","double");

    for(index = 0 ; index < SIZE; index++){

        printf("指针 + %d:  %10p  %10p\n",index, pti + index, ptf + index);

    }





}
