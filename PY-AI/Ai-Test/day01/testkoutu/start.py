from tkinter import *
from rembg import remove
from PIL import Image

root = Tk()
root.geometry("800x400")
root.maxsize(800, 400)
root.minsize(800, 400)
root.title("移除图片背景")
caption = Label(root, text="")
# def removeImageBg():
#     filename = filedialog.askopenfilename(initialdir="/", title="选择一个png格式图片", filetypes=(("png图片", "*.png*"),))
#     try:
#         if(filename == ""):
#             caption.configure(text="必须选择图片", fg="red")
#         else:
#             caption.configure(text="处理中请稍后...", fg="#333333")
#             root.update_idletasks()
#             openimg = Image.open(filename)
#             removeBg = remove(openimg)
#             removeBg.save(f"{filename}-已删除背景.png")
#             caption.configure(text="处理完成", fg="#11934A")
#             root.update_idletasks()
#     except Exception as e:
#         caption.configure(text=f"出错了{str(e)}", fg="red")
# selectImgBtn = Button(text="选择png图片立即移除", padx=30, pady=10, bg="#76ABAE", fg="#31363F", cursor="hand2",   command=removeImageBg)
# selectImgBtn.pack(side=TOP, anchor=CENTER, pady=20)
# caption.pack(side=TOP, anchor=CENTER)
# root.mainloop()
filename = "/Users/yulei/Desktop/yulei.png"
openimg = Image.open(filename)
# for i in 0,60:
removeBg = remove(openimg)
removeBg.save(f"{filename}-已删除背景.png"+str(1))