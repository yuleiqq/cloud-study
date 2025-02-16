import os

from langchain.chains.question_answering.map_reduce_prompt import messages
from langchain_community.chat_models import QianfanChatEndpoint
from langchain_core.messages import HumanMessage

#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"

#模型
chat=QianfanChatEndpoint(streaming=True,
    model="ERNIE-Bot",)

messages=[
    HumanMessage(content="给我写一首唐诗")
]
# res = chat(messages)
# print(res.content)

res= chat.invoke(messages)
print(res)