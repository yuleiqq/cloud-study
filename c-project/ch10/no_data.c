//
// Created by 余雷 on 2022/4/25.
//

#include "stdio.h"
#define  SIZE 4

int main(void ){

    int no_data[SIZE]; //未初始化数组
    int i;

    printf("%2s%14s\n", "i","no_data[i]");

    //使用数组，必须先给它赋值。 编译器使用的值是内存相应位置上的现有值。
    for(int i=0;i <SIZE; i++){
        printf("%2d %14d \n",i,no_data[i]);
    }


}


