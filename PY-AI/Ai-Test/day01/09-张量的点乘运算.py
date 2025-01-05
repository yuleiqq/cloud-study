
import  torch

#同纬度数组相乘

data1 = torch.tensor([[1,2],[3,4]])
data2 = torch.tensor([[5,6],[7,8]])
#第一种方式
data  = torch.mul(data1,data2)
print(data)

#第二种方式
data = data1 * data2
print(data)






