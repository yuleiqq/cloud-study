//
// Created by 余雷 on 2022/4/27.
//

/**
 * 使用 gets 和 puts
 */
#include "stdio.h"

#define STLEN 4

int main(void){

    char words[STLEN];
    puts("Enter a string , please.");

    gets(words); //典型用法, 获取用户的输入

    printf("Your string twice: \n");
    printf("%s\n", words);

    puts(words);
    puts("Done.");

    return  0;
}




