

import torch
import  numpy as np

data_numpy = np.array([2,3,4])
#转换为张量类型
data_tensor = torch.from_numpy(data_numpy)
print(data_tensor)
print(data_numpy)

#转换为张量类型
torch.tensor(data_numpy)





