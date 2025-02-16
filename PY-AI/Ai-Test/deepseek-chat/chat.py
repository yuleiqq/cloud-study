
from openai import OpenAI

def GetOpenai():
    # 初始化客户端 ， 本地部署，api-key 随便写就行。
    client = OpenAI(base_url="http://127.0.0.1:11434/v1", api_key="q")

    # # 调用API生成对话
    # completion = client.chat.completions.create(
    #     model="deepseek-r1:14b",  # 使用的模型，模型必须一致
    #     messages=[
    #         # {"role": "system", "content": "你是一个专业的助手，回答问题时必须准确，且不能胡言乱语。"},  # 系统提示
    #         {"role": "user", "content": "你好，你叫什么名字"}  # 用户输入
    #     ],
    #     temperature=0.7,  # 控制输出的随机性，值越低输出越确定
    #     top_p=0.9,  # 控制输出的多样性，值越低输出越集中
    #     max_tokens=1024,  # 控制生成的最大token数量
    #     frequency_penalty=0.5,  # 减少重复内容的生成
    #     presence_penalty=0.5  # 鼓励模型引入新内容
    # )

    response = client.chat.completions.create(
        model="deepseek-r1:14b",
        messages=[
            # {"role": "system", "content": "You are a helpful assistant"},
            {"role": "user", "content": "北京的介绍"},
        ],
        stream=False
    )

    # 打印生成的回复
    print(response.choices[0].message.content)


if __name__ == '__main__':
    GetOpenai()