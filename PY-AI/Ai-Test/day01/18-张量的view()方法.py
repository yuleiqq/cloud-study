import torch

#1. 若要使用view 函数，需要使用contiguous()变成连续以后再使用view函数
#2. 判断张量是否使用整块内存

data  =  torch.tensor([[10,20,30],[40,50,60]]);
print('data  -- >', data, data.shape)

# 1 判断是否使用整块内存
print(data.is_contiguous())

#2 view
mydata2 = data.view(3,2)
print(mydata2)