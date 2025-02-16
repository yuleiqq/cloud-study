from langchain.chains.sequential import SimpleSequentialChain
from  langchain_community.llms  import QianfanLLMEndpoint
from langchain_core.prompts import PromptTemplate
from langchain.chains import LLMChain

import os

from sympy.physics.units import temperature

#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"


#如果你想将第一个模型输出的结果，直接作为第二个模型的输入，还可以使用LangChain的SimpleSequentialChain

#创建第一条链
template = "我的邻居姓{lastname},他生了个儿子，给他儿子起个名字"
first_prompt = PromptTemplate(
    input_variables=["lastname"],
    template=template)
llm = QianfanLLMEndpoint(temperature=0.9)
# print(res)
first_chain = LLMChain(llm=llm,prompt=first_prompt)

#创建第二条链
second_prompt= PromptTemplate(input_variables=['child_name'],template="邻居的儿子名字叫{child_name}，给他起一个小名")
second_chain = LLMChain(llm=llm,prompt=second_prompt)

#链接两条链，verbose=True可以显示推理过程
overall_chain=SimpleSequentialChain(chains=[first_chain,second_chain],verbose=True)

#执行链，只需要传入第一个参数
print(overall_chain.run("王"))

