import os
# os.environ["HF_ENDPOINT"]="https://hf-mirror.com"
# os.environ["HUGGINGFACE_HUB_URL"] = "https://mirrors.tuna.tsinghua.edu.cn/hugging-face-hub"
# os.environ["HF_DATASETS_URL"] = "https://mirrors.tuna.tsinghua.edu.cn/hugging-face-datasets"
# os.environ["HF_METRICS_URL"] = "https://mirrors.tuna.tsinghua.edu.cn/hugging-face-metrics"
# os.environ["HF_HOME"] = "data/hf_home_directory"
# os.environ["TRANSFORMERS_CACHE"] = "data/transformers_cache_directory"
# os.environ["HF_DATASETS_CACHE"] = "data/datasets_cache_directory"

#大模型，中文翻译者成英文

from transformers import AutoModelWithLMHead,AutoTokenizer,pipeline
mode_name = '/Users/yulei/ai/trans-opus-mt-zh-en'
model = AutoModelWithLMHead.from_pretrained(mode_name)
tokenizer = AutoTokenizer.from_pretrained(mode_name)
translation = pipeline("translation_zh_to_en", model=model, tokenizer=tokenizer)
out =translation('我喜欢学习数据科学和机器学习。', max_length=400)

print(out)