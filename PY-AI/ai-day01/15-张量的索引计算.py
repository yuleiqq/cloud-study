import  torch

#随机生成数据
data = torch.randint(0,10,[4,5])
print(data)

#简单行、列索引
print(data[0])  # 第1行元素
print(data[:,0])# 所有行的第1列元素

#列表索引
#返回(0,1)、（1,2)两个位置的元素
print(data[[0,1],[1,2]])
#返回0、1行的1、2列共4个元素
print(data[[[0],[1]],[1,2]])

#范围索引
#前3行的前2列数据
print(data[:3,:2])
#第2行到最后的前列数据
print(data[2:,:2])



