
/**
 * 把字符串看做指针
 */

#include "stdio.h"

int main(){

    // *"space fares" ,表示该字符串所指向地址上存储的值, 字符串的首字符
    printf("%s, %p, %c\n","We" ,"are",*"space fares");

    return  0;
}





