
#tensor

import torch
import  numpy as np

# #创建张量标量
# data = torch.tensor(10)
# print(data)
#
# # 2. numpy数组
# data = np.random.randn(2,3)
# print(data)
# data = torch.tensor(data)
# print(data)
# # 列表
# print(torch.tensor([[2.,3,90],[12.,13,20]]))

#torch.Tensor(）根据形状创建张量
# 创建2行3列的张量，默认dtype 为float32
data = torch.Tensor(2,3)
print(data)

print(torch.Tensor(10))

print(torch.Tensor([[1,2],[3,4]]))









