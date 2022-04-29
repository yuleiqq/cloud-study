//
// Created by 余雷 on 2022/4/28.
//

#include "stdio.h"
#include "string.h"

char * s_gets(char * st ,int n);

#define MAXTITL  41  //书名的最大长度
#define MAXAUTL  31  // 作者姓名的最大长度

struct book {
    char title[MAXTITL] ;
    char author[MAXAUTL];
    float  value;
};


int main(void){

    struct  book library; // 把library 声明为一个book 类型的变量

    printf("Please enter the book title. \n");
    s_gets(library.title , MAXTITL);  // 访问title 部分

}

char * s_gets( char * st ,int n){

    char * ret_val;
    char * find;

    ret_val = fgets(st, n ,stdin);

    if(ret_val){

        find  = strchr(st,'\n'); //查找换行符

        if(find)  //如果地址不是NULL
            * find = '\0';  // 在此处放置一个空字符
        else
            while (getchar() != '\n')
                continue;  //处理输入行中剩余的字符
    }

    return   ret_val;

}

