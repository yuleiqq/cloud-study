
/**
 * 影视剧测试
 */
#include "stdio.h"
#include "string.h"

#define TSIZE  45  /* 存储片名的数组大小 */
#define FMAX  5  /*影片的最大数量*/

struct film {
    char title[TSIZE];
    int rating;
};

char * s_gets(char str[], int lim);

int main(void){
    struct film movies[FMAX];
    int i =0 ;
    int j;

    puts("Enter first movie title: ");
    while (i < FMAX && s_gets(movies[i].title ,TSIZE) != NULL && movies[i].title[0] !='\0')
    {
        puts("Enter your rating <0 - 10>: ");
        scanf("%d", &movies[i++].rating);
        while (getchar() !='\n' )
            continue;
        puts("Enter next movie title (empty line to stop): ");
    }
    if(i == 0){
        printf("No data entered.");
    }else{
        printf(" Here is the movie list: \n");
    }

    for(j=0; j< i; j++){
        printf("Moive: %s Rating: %d\n", movies[j].title,movies[j].rating);
    }


    return  0;

}














