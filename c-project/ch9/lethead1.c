//
// Created by 余雷 on 2022/4/25.
//


/**
 * 创建一个在一行打印40个星号的函数 ,并在一个打印表头的程序中使用该函数
 */


#include "stdio.h"

#define  NAME "秦始皇大帝转世"
#define ADDRESS "北京市十字路口"
#define  PLACE "石景山"
#define  WIDTH 40

void startbar(void);

int main(void){

    startbar();
    printf("%s \n",NAME);
    printf("%s \n",ADDRESS);
    printf("%s \n",PLACE);
    startbar();

    return  0;
}

/**
 * 定义函数
 */
void startbar(void){

    int count;

    for(count =1;  count<= WIDTH; count++){
        putchar('*');
    }

    putchar('\n');

}


