from PyPDF2 import PdfReader, PdfWriter
from PyPDF2.generic import Destination, Fit, RectangleObject


def add_link_to_page(input_pdf, output_pdf, source_page_num, target_page_num, rect_x1, rect_y1, rect_x2, rect_y2):
    # 打开PDF文件
    reader = PdfReader(input_pdf)
    writer = PdfWriter()

    # 读取每一页
    for page_num in range(len(reader.pages)):
        page = reader.pages[page_num]

        # 如果是源页面，添加链接
        if page_num == source_page_num:
            # 创建目标页面的目的地，使用Fit.fit()创建Fit实例
            dest = Destination(target_page_num)
            # 创建链接注释
            annotation = page.add_link_annotation(RectangleObject(rect_x1, rect_y1, rect_x2, rect_y2), dest)

        # 将页面添加到写入器
        writer.add_page(page)

    # 将修改后的PDF写入文件
    with open(output_pdf, 'wb') as out:
        writer.write(out)


# 参数解释：
# input_pdf: 输入的PDF文件路径
# output_pdf: 输出的PDF文件路径
# source_page_num: 要添加链接的源页面编号（从0开始）
# target_page_num: 链接指向的目标页面编号（从0开始）
# rect_x1, rect_y1, rect_x2, rect_y2: 链接区域的坐标（左上角和右下角）
add_link_to_page('merged.pdf', 'linked.pdf', 0, 1, 100, 100, 200, 200)