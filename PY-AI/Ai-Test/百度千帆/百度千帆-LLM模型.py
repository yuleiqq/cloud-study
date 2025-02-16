
import os
from langchain_community.llms import QianfanLLMEndpoint
# from langchain_core.prompts import PromptTemplate
from langchain.chains import LLMChain

#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"

llm = QianfanLLMEndpoint(model="ChatGLM2-6B-32K")
res = llm("请帮我讲一个笑话")
print(res)






