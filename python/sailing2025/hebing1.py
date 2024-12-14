from PyPDF2 import PdfReader, PdfWriter


def merge_pdfs(paths, output):
    # 创建一个 PdfWriter 对象，它将保存最终合并后的 PDF
    writer = PdfWriter()

    # 遍历所有提供的 PDF 文件路径
    for path in paths:
        # 为每个 PDF 文件创建一个 PdfReader 对象
        reader = PdfReader(path)
        # 将 reader 中的所有页面添加到 writer 中
        for page in reader.pages:
            writer.add_page(page)

    # 将合并后的 PDF 写入文件
    with open(output, 'wb') as out:
        writer.write(out)


# 要合并的 PDF 文件列表
pdf_files = [
    '1513.13.0gew_005vck.pdf',
    '1513.13.0gew_006vck.pdf']
# 合并后的 PDF 文件名
merged_pdf = 'merged.pdf'

# 调用函数合并 PDF
merge_pdfs(pdf_files, merged_pdf)
