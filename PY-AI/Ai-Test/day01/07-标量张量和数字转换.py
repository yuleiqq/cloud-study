
import torch

#对于只有一个元素的张量，使用item()函数将值从张量中提取出来
data = torch.tensor([30,])
print(data.item())

data =torch.tensor(30)
print(data.item())