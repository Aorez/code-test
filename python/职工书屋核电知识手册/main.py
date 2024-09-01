import requests
import time
import os
import json

# 职工书屋核电知识手册
# 157页*休眠5秒/60秒=13分钟左右
# 实际20分钟，成功157页

# JSON文件路径
json_file_path = 'gethash.json'

# 读取JSON文件并获取页面哈希值
with open(json_file_path, 'r') as file:
    content = json.load(file)
    page_hashes = [page['hash'] for page in content['data']]

# 图片的基础URL
base_url = "https://img1-qn.bookan.com.cn/jpage8/2101598/2101598-310262597/"

# 保存图片的目录
save_dir = '../hashbook'
os.makedirs(save_dir, exist_ok=True)

# 下载并保存图片的函数
def download_image(url, save_path):
    response = requests.get(url)
    if response.status_code == 200:
        with open(save_path, 'wb') as file:
            file.write(response.content)

# 遍历每个页面哈希值，构造URL并下载图片
for i, page_hash in enumerate(page_hashes, start=1):
    image_url = base_url + page_hash + "_big.jpg"
    save_path = os.path.join(save_dir, f'page_{i}.jpg')
    download_image(image_url, save_path)
    time.sleep(5)  # 休眠5秒以避免反爬虫机制
