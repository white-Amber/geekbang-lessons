package cn.yz.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yuze
 * @date 2024/11/4
 */
public class C01E01_HelloWorld {

    public static void main(String[] args) throws Exception {
        letter();
    }

    public static void helloWorld() throws Exception {
        // 创建文档对象
        Document document = new Document();
        // 获取PdfWriter实例
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("/Users/yuze/Downloads/hello.pdf")));
        // 打开文档
        document.open();
        // 添加段落内容
        document.add(new Paragraph("Hello World!"));
        // 关闭文档
        document.close();
    }

    public static void documentConstructDemo() throws Exception {
        // 自定义页面大小
        Rectangle pageSize = new Rectangle(300f, 520f);
        Document document = new Document(pageSize, 50f, 50f, 50f, 50f);
        PdfWriter.getInstance(document, new FileOutputStream("/Users/yuze/Downloads/document_construct.pdf"));
        document.open();
        document.add(new Paragraph("Hello World! Hello People! Hello Sky! Hello Sun! Hello Moon! Hello Stars! "));
        document.close();
    }

    public static void letter() throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/Users/yuze/Downloads/document_construct.pdf"));
        document.open();
        document.add(new Paragraph("Hello World! Hello People! Hello Sky! Hello Sun! Hello Moon! Hello Stars! "));
        document.close();
    }

}
