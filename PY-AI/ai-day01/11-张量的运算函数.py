import torch
data = torch.randint(0,10,[2,3],dtype=torch.float64)
print(data)
#计算均值
#注意: tensor 必须为Float 或者Double类型
print("均值->",data.mean())
#计算总和
print("总和->",data.sum())
#计算平方
print("平方->",torch.pow(data,2))
#计算平方根
print(data.sqrt())
#指数计算 ,e的n次方
print("指数计算->",data.exp())
#对数计算
#以e为底
print(data.log())
print(data.log2())
print(data.log10())
