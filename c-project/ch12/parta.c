//
// Created by 余雷 on 2022/4/27.
//

#include "stdio.h"

void report_count();
void accumulate(int k);

int count = 0;  //文件作用域，外部链接
int main(void ){

    int value;  //自动变量
    register int i;  //寄存器变量

    printf("输入一个正数 （ 0 退出） ：");
    while (scanf("%d", &value) ==1 && value >0){

        ++ count;  //使用文件作用域变量
        for( i  = value; i >=0; i --){
            accumulate(i);
        }
        printf("输入一个正数 （ 0 退出） ：");
    }
    report_count();
    return  0;
}


void report_count(){
    printf("Loop executed %d times \n", count);
}






