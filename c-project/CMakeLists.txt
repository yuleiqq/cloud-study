cmake_minimum_required(VERSION 3.22)
project(c_project C)

set(CMAKE_C_STANDARD 99)

add_executable(c_project main.c)

add_executable(first ch2/first.c)
add_executable(two_func ch2/two_func.c)
add_executable(platinum ch3/platinum.c)