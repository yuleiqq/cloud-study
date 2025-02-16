import torch

#乘法运算
data1 = torch.tensor([[1,2],[3,4],[5,6]])
data2= torch.tensor([[5,6],[7,8]])

#方式一
data3 = data1 @ data2
print("data3->",data3)
 
#方式二
data4 = torch.matmul(data1,data2)
print("data4->",data4)

