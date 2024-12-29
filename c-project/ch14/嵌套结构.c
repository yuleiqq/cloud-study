//
// Created by 余雷 on 2024/12/29.
//

#include "stdio.h"
#define LEN 20

const char * msgs[5] = {

        "Thank you  for the ",
        "You cers ",
        "siusadfads",
        "over si s si ",
        "and have a fore"
};

//第一个结构
struct  names{
    char first[LEN];
    char last[LEN];
};

//第二个结构
struct  guy{
    struct  names handle;
    char favfood[LEN];
    char job[LEN];
    float income;
};


int main (void){

    struct  guy fellow = {
            {"EVEN","Villard"},
            "grilled salom",
            "人民教师",
            6999.10
    };

    printf("Dear %s, \n\n",fellow.handle.first);


}




