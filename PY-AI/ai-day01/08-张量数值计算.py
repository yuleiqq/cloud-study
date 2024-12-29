
import torch

data = torch.randint(0,10,[2,3])
print(data)

#1.不修改原数据
new_data = data.add(10)  # new
print(new_data)
#2. 直接修改原数据  注意： 带下划线的函数为修改原数据本身
data.add_(10)
print(data)

#3 其他函数
data.sub(10)
data.mul(100)
data.div(19)
print(data.neg())  #负号
