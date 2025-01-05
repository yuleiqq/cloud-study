
import torch
#三维数组
data = torch.randint(0,10,[3,4,5])
print(data)

#获取0轴上的第一个数据
print(data[0,:,:])

#获取1轴上的第一个数据
print(data[:,0,:])

#获取2轴上的第一个数据
print(data[:,:,0])


