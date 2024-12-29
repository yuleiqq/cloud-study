//
// Created by 余雷 on 2022/4/21.
//

#include "stdio.h"

/**
 * 跳过输入中的前两个整数
 * @return
 */
int main (){

    int n;
    printf("请输入三个数字： \n");
    scanf("%*d %*d %d", &n);
    printf("最后一个数字是： %d\n", n);
    return  0;
}