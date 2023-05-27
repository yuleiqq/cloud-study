//
// Created by 余雷 on 2023/5/20.
//

#include <iostream>
#include "cmath"
using namespace std;

int main (){

    float   radius = 2.5f;  //半径
    float  height = 4.0f ;  //高
    //圆柱体积 = 底面积 * 高
    float  volume = 3.14 * pow(radius,2) * height;
    cout << "圆柱的面积为：" << volume <<endl;

    //数据类型的字节长度
    cout << sizeof (int) <<endl;

}

