import numpy as np
import torch
import torch.nn as nn

#定义一个简单的神经网络模型
class SimpleNN(nn.Module):
    def __init__(self):
        super(SimpleNN, self).__init__()
        #定义一个输入层到隐藏层的全连接层
        self.fc1 = nn.Linear(2, 2)
        # 定义一个隐藏层到输出层的全连接层
        self.fc2 = nn.Linear(2, 1)
    def forward(self, x):
        # 前向传播过程
        x = torch.relu(self.fc1(x))  # 使用 ReLU 激活函数
        x = self.fc2(x)  # 输出层
        return x

#创建模型实例
model = SimpleNN()
#打印模型
print(model)