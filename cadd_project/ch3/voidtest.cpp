//
// Created by 余雷 on 2023/5/20.
//
#include "iostream"
using namespace  std;

int main (){


    //1.  void * 指针存放一个内存地址，地址指向的内容是什么类型不确定
    //2. void * 类型指针一般用来： 拿来和别的指针比较、作为函数的输入和输出；赋值给另一个void * 指针

    double num  = 3.14;
    double * ptr_num1 = &num ;
    void * ptr_num2 = &num;

    cout << boolalpha;



}