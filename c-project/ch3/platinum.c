//
// Created by 余雷 on 2022/4/21.
//

/**
 * 数字的运算
 */

#include "stdio.h"
int main(void ){

    float weight;  /* 你的体重 */
    float value;   /* 相等重量的白金价格*/

    printf("Are you worth your weight in platinum ? \n");
    printf(" Let's  check it out. \n ");
    printf("Please enter your weight in pounds: ");

    /**
     * 获取用户的输入
     */
    scanf("%f" , &weight);

    /**
     * 假设白金的价格是每盎司 $1700
     *
     *  14.5833 用于把英镑常衡盎司转换为金衡盎司
     */

    value = 1700.00 * weight * 14.5833;

    printf("$%.2f \n" ,value );

    return  0;

}