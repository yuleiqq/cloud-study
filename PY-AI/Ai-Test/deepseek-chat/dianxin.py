
from openai import OpenAI

def GetOpenai():
    # 初始化客户端
    client = OpenAI(base_url="https://wishub-x1.ctyun.cn/v1/", api_key="a4d6d26618b943d4ac7c92abeb1fdfb1")

    # 调用API生成对话
    completion = client.chat.completions.create(
        model="4bd107bff85941239e27b1509eccfe98",  # 使用的模型
        messages=[
            # {"role": "system", "content": "你是一个专业的助手，回答问题时必须准确，且不能胡言乱语。"},  # 系统提示
            {"role": "user", "content": "现在什么时候"}  # 用户输入
        ],
        temperature=0.7,  # 控制输出的随机性，值越低输出越确定
        top_p=0.9,  # 控制输出的多样性，值越低输出越集中
        max_tokens=512,  # 控制生成的最大token数量
        frequency_penalty=0.5,  # 减少重复内容的生成
        presence_penalty=0.5  # 鼓励模型引入新内容
    )
    # 打印生成的回复
    print(completion.choices[0].message.content)


if __name__ == '__main__':
    GetOpenai()