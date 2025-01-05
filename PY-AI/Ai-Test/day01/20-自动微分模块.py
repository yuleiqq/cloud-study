
import torch
def test01():
     #1、当x为标量时梯度的计算
     x=torch.tensor(5)
     #目标值
     y=torch.tensor(0.)
     #设置要更新的权重和偏置的初始值
     w=torch.tensor(1.,requires_grad=True,dtype=torch.float32)
     b=torch.tensor(  3.,requires_grad=True,dtype=torch.float32)
     #设置网络的输出值
     z=x * w +b #矩阵乘法
     #设置损失函数，并进行损失的计算
     loss=torch.nn.MSELoss()
     loss=loss(z,y)
     #自动微分
     loss.backward()
     #打印w，b变量的梯度
     #backward函数计算的梯度值会存储在张量的grad变量中
     print("W的梯度：",w.grad)
     print("b的梯度",b.grad)


test01()