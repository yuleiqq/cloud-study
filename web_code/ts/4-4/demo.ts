// 语法：
//     enum 枚举名称{成员1，成员2....};

// 1）：数字枚举
//     默认情况下，第一个枚举值是0，后续至依次增1
enum Color 
{ 
    red,
    blue,
    yellow
    }
let col = Color.blue;
alert(col);


//字符串枚举
enum gender {
    male = '1',
    female = '0',
}
​
alert(gender.male);     // "1"

