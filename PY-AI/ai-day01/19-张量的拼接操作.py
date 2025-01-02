
import numpy as np
import torch

# torch.cat()函数可以将两个张量根据指定的维度拼接起来，不改变维度数
data1  = torch.randint(0,10,[1,2,3])
print("data1 -> ",data1)
data2  = torch.randint(0,10,[1,2,3])
print("data2 -> ",data2)

# #1. 按0维度拼接
# new_data = torch.cat([data1,data2],dim=0)
# print(new_data,new_data.shape)

# 2. 按1维度拼接
# new_data = torch.cat([data1,data2],dim=1)
# print(new_data,new_data.shape)

# 3. 按2维度拼接
new_data = torch.cat([data1,data2],dim=2)
print(new_data,new_data.shape)
