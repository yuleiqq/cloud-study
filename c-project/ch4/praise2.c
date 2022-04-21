//
// Created by 余雷 on 2022/4/21.
//

#include "string.h"
#include "stdio.h"
#define  PRAISE "You"

/**
 *  sizeof ： 以字节为单位，给出对象的大小
 *  strlen:  给出字符串的字符长度
 * @return
 */
int main(){

    char name [40];

    printf("What's your name ?");
    scanf("%s",name);

    printf("%zd , %zd \n", strlen(name), sizeof name);

    printf("%zd , %zd ", strlen(PRAISE), sizeof PRAISE);  // sizeof 打印的是4 ,因为多了一个空字符 '\0'

    return  0;
}

