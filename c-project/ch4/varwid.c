//
// Created by 余雷 on 2022/4/21.
//

#include "stdio.h"

/**
 *
 * prinf()函数 使用* :   其中的 * 表示一个通配符
 * @return
 */
int main(void){

    unsigned  width, precision;

    int number = 256;
    double weight = 242.5 ;

    printf("输入一个字段的宽度: \n");
    scanf("%d", &width);

    printf("数字是 : %*d:\n", width, number);

}
