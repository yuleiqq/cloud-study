
import torch

#1.将张量转换为numpy 数组
data_tensor = torch.tensor([2,3,4])
print(data_tensor)

data_numpy=data_tensor.numpy();
print(data_numpy)

print(type(data_tensor))
print(type(data_numpy))







