//
// Created by 余雷 on 2022/4/21.
//

/**
* 把鞋码转换成英寸
*/

#include "stdio.h"
#define  ADJUST  7.31
int main(){

    const double  SCALE = 0.333;
    double shoe,foot;

    shoe = 9.0;
    foot = SCALE * shoe + ADJUST;

    printf("shoes size (men's)  foot length \n");
    printf("%10.1f  %15.2f inches \n",shoe,foot);

    return  0;

}

