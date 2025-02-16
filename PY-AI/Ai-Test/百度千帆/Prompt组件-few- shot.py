
from  langchain_community.llms  import QianfanLLMEndpoint
from langchain_core.prompts import PromptTemplate,FewShotPromptTemplate

import os
#设置apikey 等信息
os.environ["QIANFAN_AK"] = "mglnQXEpi7vuNTP5zfDF5zpt"
os.environ["QIANFAN_SK"] = "EHiPX002QcoW3ltJrd3Y25iXSQwGS9ho"

# 1.给出部分示例
examples = [{"word": "开心", "antonym": "难过"},
            {"word": "高", "antonym": "矮"}]
# 2.设置example_prompt
example_template = """
单词: {word}
反义词: {antonym}\\n
"""
# 3.实例化example_prompt
example_prompt = PromptTemplate(input_variables=["word", "antonym"],
                                template=example_template)

# 4.实例化few-shot-prompt
few_shot_prompt = FewShotPromptTemplate(examples=examples,
                                        example_prompt=example_prompt,
                                        prefix="给出每个单词的反义词",
                                        suffix="单词:{input}\\n反义词",
                                        input_variables=["input"],
                                        example_separator="\\n")

# 5.实例化模型
llm = QianfanLLMEndpoint(model="Qianfan-Chinese-Llama-2-7B")

# 6.指定模型的输入
prompt_text = few_shot_prompt.format(input="粗")
print(f'prompt_text--&gt;{prompt_text}')

#7.将prompt_text输入模型
result = llm(prompt_text)
print(result)








