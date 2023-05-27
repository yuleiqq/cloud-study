//
// Created by 余雷 on 2023/5/27.
//
#include "iostream"
using namespace  std;

void show(int[] ,int);
int main (){

    int valueArray[] = {1,2,3,4,5};

    //数组名传入，实际传入的是函数的首地址,即指针
    show(valueArray,5);
    show(valueArray,5);

}
void show( int valueArray[],int len ){
    for(int i=0;i<len ;i++){
        cout << valueArray[i] ++;
    }
    cout << endl;
}