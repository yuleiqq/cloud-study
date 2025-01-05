import torch

# 创建一个2*3 的全0张量
a = torch.zeros(2,3)
print(a)

#创建一个2*3的全1张量
b = torch.ones(2,3)
print(b)

#创建一个2*3的随机张量
c = torch.randn(2,3)
print(c)

#从numpy数组创建张量
import numpy as np
numpy_array = np.array([[1,2],[3,4]])
print(numpy_array)
tensor_from_numpy = torch.from_numpy(numpy_array)
print(tensor_from_numpy)

#在指定设备(CPU/GPU)上创建张量
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(device)
d = torch.randn(2, 3, device=device)
print(d)

#创建一个指定范围内等间隔的序列张量
x= torch.linspace(0,10,1)
print(x)






