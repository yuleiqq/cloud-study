//
// Created by 余雷 on 2022/4/27.
//

/**
 * 使用外部变量
 */

#include "stdio.h"

int units = 0;
void  critic(void );  //外部变量

int main(void){

//    extern int units;  //可选的重复声明

    printf("How manay  pounds to a firkin of butter? \n");
    scanf("%d",&units);

    while (units != 56){
        critic();
    }

    printf("You must have looked it up ! \n");

    return  0;

}

void  critic(void ) {

    printf("No luck , my friend . Try again . \n");
    scanf("%d" , &units);

}


















