//
// Created by 余雷 on 2022/4/21.
//

#include "stdio.h"

/**
 * 一个简单的 C 程序
 *
 *  # include 这行代码是一条 C 预处理器指令
 *
 * @return
 */
 int main (){

    int num;   // 定义一个名为num 的变量
    num =1;    // 为一个num 赋值

    printf("I am a simple ");  //使用 printf 函数
    printf("computer . \n");

    printf("My favorite number is %d  because  it is  first. \n ", num);


    /**
     * 如果遗漏main()函数的return 语句，程序在运行至最外面的花括号时会返回0。因此可以省略
     *  main 函数的return 语句。但是不要在其它有返回值的函数中漏掉它。
     */
    return  0;
}