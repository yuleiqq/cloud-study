
import os
from langchain_community.embeddings import QianfanEmbeddingsEndpoint
# from langchain_core.prompts import PromptTemplate
from langchain.chains import LLMChain

#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"


embed   = QianfanEmbeddingsEndpoint()
#一段文本的向量化
res1 = embed.embed_query("这是一个测试文档")
print(res1)
#一批文本向量化
res2 = embed.embed_documents(['这是第一个测试文档','这是第二个测试文档'])
print(res2)