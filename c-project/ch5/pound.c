//
// Created by 余雷 on 2022/4/21.
//

/**
* 带参数的函数
 *
 * 实参 、形参
*/

#include "stdio.h"

void pound(int n);
extern  int a=1;

int main (){

    int times = 5;
    char ch = '!';
    float  f = 6.0f;


    pound(times);  // int 类型的参数
    pound(ch);    // 和  pound( (int) ch ) 相同
    pound(f);

}


void pound(int n){

    while (n -- > 0 )
        printf("#");
    printf("\n");

}







