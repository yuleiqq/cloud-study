
from  langchain_community.llms  import QianfanLLMEndpoint
from langchain_core.prompts import PromptTemplate
from langchain.chains import LLMChain

import os
#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"
#定义模版
template = "我的邻居姓{lastname},他生了个儿子，给他儿子起个名字"

prompt = PromptTemplate(
    input_variables=["lastname"],
    template=template)
prompt_text = prompt.format(lastname="王")
llm = QianfanLLMEndpoint()
res = llm(prompt_text)
# print(res)
chain = LLMChain(llm=llm,prompt=prompt)

#执行链
print(chain.run(lastname="王"))
