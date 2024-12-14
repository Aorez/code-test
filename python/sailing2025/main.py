from PyPDF2 import PdfFileReader, PdfFileWriter


def merge_pdfs(paths, output):
    """
    paths: List of paths to the PDF files to be merged
    output: Path to the output PDF file
    """
    pdf_writer = PdfFileWriter()

    for path in paths:
        pdf_reader = PdfFileReader(path)
        for page_num in range(pdf_reader.getNumPages()):
            page = pdf_reader.getPage(page_num)
            pdf_writer.addPage(page)

    with open(output, 'wb') as out:
        pdf_writer.write(out)


# List of your PDF files
pdf_files = [
    '1513.13.0gew_005vck.pdf',
    '1513.13.0gew_006vck.pdf']


# merge_pdfs(pdf_files, 'merged.pdf')




def add_link_to_page(input_pdf, output_pdf, source_page_num, target_page_num, rect_x1, rect_y1, rect_x2, rect_y2):
    # 打开PDF文件
    reader = PdfFileReader(input_pdf)
    writer = PdfFileWriter()

    # 读取每一页
    for page_num in range(reader.getNumPages()):
        page = reader.getPage(page_num)

        # 如果是源页面，添加链接
        if page_num == source_page_num:
            # 创建一个动作，指向目标页面
            action = "/GoToR /FitR " + str(target_page_num) + " 0 R"
            annotation = "<</Type/Annot/Subtype/Link/Rect[" + \
                         str(rect_x1) + " " + str(rect_y1) + " " + \
                         str(rect_x2) + " " + str(rect_y1) + \
                         " " + str(rect_x2) + " " + str(rect_y2) + \
                         " " + str(rect_x1) + " " + str(rect_y2) + \
                         "]/BS<</W 0>>/Action<<" + action + ">>" + \
                         ">>"
            page.addAnnotation(annotation)

        # 将页面添加到写入器
        writer.addPage(page)

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