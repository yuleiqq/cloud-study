cmake_minimum_required(VERSION 3.22)
project(c_project C)

set(CMAKE_C_STANDARD 99)

## 有引用的链接，需要同时编译，不然会报找不到符号的错误
add_executable(r_driver0  ch12/r_driver0.c  ch12/rand0.c)
## 有引用的链接，需要同时编译，不然会报找不到符号的错误
add_executable(parta ch12/parta.c  ch12/partb.c)