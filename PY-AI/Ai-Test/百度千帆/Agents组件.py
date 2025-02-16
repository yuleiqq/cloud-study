
from langchain_community.llms import QianfanLLMEndpoint
from langchain.agents import load_tools, initialize_agent, AgentType
from langchain_core.prompts import PromptTemplate

import os
#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"

#实例化模型
llm = QianfanLLMEndpoint()
#需要安装依赖库：llm-mathj计算、wikipedia
# 定义工具:这里指定两个工具来选择使用：llm-math计算，wikipedia
tools = load_tools(["wikipedia"], llm=llm)

#创建Agent实例
agent = initialize_agent(tools=tools,
                         llm=llm,
                         agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
                         verbose=True)

# print(f'agent-->>; {agent}')

prompt_template = "中国目前有多少人口"
prompt = PromptTemplate.from_template(prompt_template)

# 执行代理
result = agent.run(prompt)
print(result)