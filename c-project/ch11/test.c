//
// Created by 余雷 on 2022/4/27.
//s

#include "stdio.h"
int main(){

    //char * name ;  未初始化的指针， name 可能指向任何地方。

    char name[81];  //声明时显示指定数组的大小
    scanf("%s",name);

    printf("%s",name);


    return  0;
}
