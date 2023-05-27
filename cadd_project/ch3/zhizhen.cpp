//
// Created by 余雷 on 2023/5/20.
//

#include "iostream"
using namespace  std;

int main (){

//    int year =2018;
//
//    int * pyear = &year;
//    cout <<  * pyear;

//   char ch = 'a';
//   char  * ptr_ch = &ch;
//   cout << (void *)ptr_ch << "\t" << * ptr_ch << endl;

        int num =108;
        int * rel_num = &num;
        *rel_num =118;

        cout << &num << '\t' << rel_num <<endl;

        cout << num ; //值变成 118了

}






