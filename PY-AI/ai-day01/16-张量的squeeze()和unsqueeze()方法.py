
import  torch

#squeeze 函数删除形状为1的维度（降维），unsqueeze 函数添加形状为1的维度（升维）
mydata1 = torch.tensor([1,2,3,4,5])
print('mydata1 - >',mydata1.shape,mydata1)  # 一个普通的数组，一维数据

mydata2 = mydata1.unsqueeze(dim=0)
print('在0维度上扩展维度:',mydata2,mydata2.shape)  # 1* 5

mydata3 = mydata1.unsqueeze(dim=1)
print('在1维度上扩展维度:',mydata3,mydata3.shape) # 5*1

mydata4 = mydata1.unsqueeze(dim=-1)
print('在1维度上扩展维度:',mydata4,mydata4.shape) # 5*1

#降维
mydata5 = mydata4.squeeze()
print("压缩维度：",mydata5,mydata5.shape)
