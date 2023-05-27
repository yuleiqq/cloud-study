//
// Created by 余雷 on 2023/5/20.

#include "iostream"

#include "vector"
using namespace  std;

/***
 *  #include "vector"
 */

int main (){

    //vector 是一个动态数组 ,可以在运行阶段设置长度
    //具有数组的快速索引方式
    //可以插入和删除元素
    vector<double> vecDouble = {98.5, 89.2,22.2};
    //向数组中插入数字
    vecDouble.push_back(100.8); //在数组的尾部插入一个数字
//    //遍历
//    for(int i =0; i< vecDouble.size();i++){
//        cout << vecDouble[i] <<endl;
//    }


    //排序
    sort(vecDouble.begin(),vecDouble.end());
    //逆序
    reverse(vecDouble.begin(),vecDouble.end());

    vector<double> :: iterator  it; //得到迭代器对象 ,实际上是一个指针对象
    for(it = vecDouble.begin(); it != vecDouble.end(); ++it){
        cout <<  *it  << endl;
    }

}