
import torch
import numpy as np

# transpose 函数可以实现交换张量形状的指定维度，例如： 一个张量的形状为（2，3，4） 可以通过 transpose函数把3和4进行交换，
# 将张量的形状变为(2,3,4)。permute函数可以一次交换更多的维度。

data = torch.tensor(np.random.randint(0,10,[3,4,5]))
print('data shape',data.size(),data)

#交换1 和2 维度
mydata2 = torch.transpose(data,1,2)
print('mydata2 shape',mydata2.size(),mydata2)

#方法1
torch.permute(data,[1,2,0])
#方法2
data.permute([1,2,0])



