from flask import Flask, request, render_template,jsonify,make_response
from PIL import Image,ImageSequence
import base64
import cv2
import numpy as np
import io
import uuid

import imghdr
import os
def generate_random_filename():
    filename = str(uuid.uuid4())
    return filename
app = Flask(__name__)
def svchange(img,goal1,goal2,goal3):
    hsv_image=cv2.cvtColor(img,cv2.COLOR_BGR2HSV)
    h,s,v=cv2.split(hsv_image)
    print(np.mean(h))
    print(np.mean(s))
    print(np.mean(v))
    # factor1=goal1/np.mean(h)
    # h=np.clip(h*factor1,0,255).astype(np.uint8)
    factor2=goal2/np.mean(s)
    s=np.clip(s*factor2,0,255).astype(np.uint8)
    factor3=goal3/np.mean(v)
    v=np.clip(v*factor3,0,255).astype(np.uint8)
    new_hsv_image=cv2.merge((h,s,v))
    new_image=cv2.cvtColor(new_hsv_image,cv2.COLOR_HSV2BGR)
    new_image=cv2.cvtColor(new_image,cv2.COLOR_HSV2BGR)
    return new_image
def transparent(item,choice,h_new=137.9850015625,s_new=227.8255578125,v_new=204.6429734375):
    if choice=="seal":
        image = item
        hue_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
        image=svchange(hue_image, h_new,s_new,v_new)
        impng = cv2.cvtColor(image.copy(), cv2.COLOR_RGB2RGBA)
        hue_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
        # 2.png
        # low_range = np.array([170, 26, 46])
        # high_range = np.array([180, 195, 212])
        low_range = np.array([170, 26, 46])
        high_range = np.array([180, 255, 215])
        th = cv2.inRange(hue_image, low_range, high_range)
        element = cv2.getStructuringElement(cv2.MORPH_RECT, (1, 1))
        th = cv2.dilate(th, element)
        index1 = th == 255
        print1 = np.zeros(impng.shape, np.uint8)
        print1[:, :, :] = (255, 255, 255, 0)
        print1[index1] = impng[index1]  # (0,0,255)
        # 1.png
        # low_range = np.array([0, 26, 46])
        # high_range = np.array([13, 195, 212])
        # 2.png
        # low_range = np.array([0, 46, 46])
        # high_range = np.array([13, 195, 162])
        low_range = np.array([0, 26, 46])
        high_range = np.array([15, 255, 215])
        th = cv2.inRange(hue_image, low_range, high_range)
        element = cv2.getStructuringElement(cv2.MORPH_RECT, (1, 1))
        th = cv2.dilate(th, element)
        index2 = th == 255
        print2 = np.zeros(impng.shape, np.uint8)
        print2[:, :, :] = (255, 255, 255, 0)
        print2[index2] = impng[index2]
        imgreal = cv2.add(print2, print1)
        (row, col, _) = imgreal.shape
        minrow = row - 1
        maxrow = 0
        mincol = col - 1
        maxcol = 0
        for r in range(row):
            for c in range(col):
                px = imgreal[r][c]
                if px[3]>250:
                    #imgreal[r][c] = impng[r][c]
                    imgreal[r][c] = [0, 0,255, 255]
                    if r <= minrow:
                        minrow = r
                    if r >= maxrow:
                        maxrow = r
                    if c <= mincol:
                        mincol = c
                    if c >= maxcol:
                        maxcol = c
        result = imgreal[minrow:maxrow - 1, mincol:maxcol - 1]
        resized_image = cv2.resize(result, (result.shape[0], result.shape[1]), interpolation=cv2.INTER_AREA)
        return cv2.imencode('.png', resized_image)[1].tobytes()

def png_to_gif(png_file, gif_file, duration=0.1):
    """
    将单个PNG动画转换为GIF文件。

    :param png_file: PNG文件的路径。
    :param gif_file: 输出GIF文件的路径。
    :param duration: 每帧的持续时间，单位为秒。
    """
    with Image.open(png_file) as png:
        # 获取PNG的帧数
        frames = [frame.copy() for frame in ImageSequence.Iterator(png)]

    # 保存为GIF
    frames[0].save(gif_file, save_all=True, append_images=frames, duration=int(duration * 100), loop=0)

def string_to_hex(string):
    import binascii
    encoded_string = string.encode('utf-8')
    hex_string = binascii.hexlify(encoded_string)
    return hex_string.decode('utf-8')

@app.route('/seal',methods=['GET','POST'])
def seal():
    data = request.get_json()
    img = data['image']
    #kind = imghdr.what('', h=base64.b64decode(img))
    kind="png"
    image_data = base64.b64decode(img)
    image = Image.open(io.BytesIO(image_data))
    # 保存图片
    filename=generate_random_filename()
    fullname=""
    if kind == "gif":
        fullname=filename + ".png"
        image.save(fullname)
        image = cv2.imread(fullname)
    else:
        fullname=filename + "." + kind
        image.save(fullname)
        image = cv2.imread(fullname)
    print(filename)
    result = transparent(image, "seal")
    image = Image.open(io.BytesIO(result))
    resized_image = image.resize((317,317))
    resized_image.save(filename+".png")
    png_to_gif(filename+".png",filename+".gif")
    with open(filename+".gif", 'rb') as file:
        # 读取文件内容到字节流变量
        image_bytes = file.read()
    os.remove(filename+".gif")
    os.remove(filename+".png")
    img_base64 = base64.b64encode(image_bytes).decode('utf-8')
    return str(img_base64),200

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True, port=5001)