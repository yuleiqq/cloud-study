//
// Created by 余雷 on 2022/4/27.
//

#include <stdlib.h>

static unsigned  long int next =1 ;

unsigned  int rand0(void ){



     next = next * 1103515245 + 12345;
     return  (unsigned  int) (next / 65536) % 32768;

}

int main (void){

    //分配内存
    double * ptd;
    ptd = (double *) malloc( 30 * sizeof (double ));



}


