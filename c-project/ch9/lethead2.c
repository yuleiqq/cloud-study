//
// Created by 余雷 on 2022/4/25.
//

#include "stdio.h"
#include "string.h"

#define  NAME "秦始皇"
#define  ADDRESS "北京狮子123123123123鲁"
#define  PLACE  "103"
#define  WIDTH  40
#define  SPACE ' '

void show_char_n (char ch, int num);

int main(void){

    int spaces;
    //打印 * 号
    show_char_n('*' , WIDTH);
    putchar('\n');
    //打印空格
    spaces = (WIDTH - strlen(NAME)) /2 ;
    show_char_n(SPACE,spaces);

    printf("%s\n",NAME);
    spaces = (WIDTH - strlen(ADDRESS)) /2 ;
    show_char_n(SPACE,spaces);
    printf("%s\n",ADDRESS);

    show_char_n(SPACE, (WIDTH- strlen(PLACE))/2 );

    printf("%s\n", PLACE);
    show_char_n('*',WIDTH);

}



void show_char_n(char ch , int num){

    int count;
    for(count =1; count <= num; count++){
        putchar(ch);
    }

}









