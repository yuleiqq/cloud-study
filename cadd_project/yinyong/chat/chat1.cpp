//
// Created by 余雷 on 2023/5/27.
//

#include <iostream>
using namespace std;

/**
 *  模拟实现游戏中私聊的函数
 */

/**
 *  跟某人聊天 - 负责字符串的拼接 （聊天的格式）
 * @param toName  聊天对象的名称
 * @param content 聊天的内容
 * @return  按某规定的格式拼接聊天信息后的字符串
 */

string chatTo(const string & toName , const string & content);

string chatFrom(const string & fromName , const string & content);

int main(){

    cout << "请输入对方的名称： ";
    string toName , content;
    getline(cin,toName);

    cout << "请输入发送给对方的聊天信息： ";
    getline(cin,content);
    string chatMsg = chatTo(toName, content);
    cout << endl << chatMsg << endl;

}

/**
 *  跟某人聊天 - 负责字符串的拼接 （聊天的格式）
 * @param toName  聊天对象的名称
 * @param content 聊天的内容
 * @return  按某规定的格式拼接聊天信息后的字符串
 */

string chatTo(const string & toName , const string & content){

    string msg = " * 你悄悄的对 [ "+ toName +" ] 说：" +content;
    return  msg;
}

string chatFrom(const string & fromName , const string & content){

}

