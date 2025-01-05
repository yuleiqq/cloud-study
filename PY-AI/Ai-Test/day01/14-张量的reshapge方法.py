
import  torch

# reshape函数可以在保证张量数据不变的前提下改变数据的维度，将其转换成指定的形状

data = torch.tensor([[10,20,30],[40,50,60]])

#1. 使用shape属性或者size 方法都可以获得张量的形状
print(data.shape)
print(data.size())

#2. 使用reshapge 函数修改张量形状
new_data = data.reshape(1,6)
print(new_data)
print(new_data.shape)






