
#f-string用大括号 {} 表示被替换字段，其中直接填入替换内容：
name = 'Etric'
print(f'Hello, {name}')

pre_history = [
    (f'现在你是一个文本分类器，你需要按照要求将我给你的句子分类到类别中。',
     f'好的。')
]

pre_history.append((123,22,33))

print(pre_history)