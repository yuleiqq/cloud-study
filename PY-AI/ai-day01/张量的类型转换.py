import  torch

#1
data  = torch.full([2,3],10)
print(data.dtype)
data = data.type(torch.DoubleTensor)
print(data.dtype)

#2
data = data.double()
# data.int()




