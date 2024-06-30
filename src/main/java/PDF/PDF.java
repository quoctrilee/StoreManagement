package PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import Controller.DateController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PDF {

    public static void createSalesInvoice(int orderId, List<Object[]> rowData, String customerName,
                                          String customerPhone, String customerAddress) throws DocumentException, IOException {
        createInvoice(orderId, rowData, customerName, customerPhone, customerAddress, "Hóa đơn bán hàng");
    }

    public static void createPurchaseInvoice(int orderId, List<Object[]> rowData, String supplierName,
                                             String supplierPhone, String supplierAddress) throws DocumentException, IOException {
        createInvoice(orderId, rowData, supplierName, supplierPhone, supplierAddress, "Hóa đơn nhập hàng");
    }

    private static void createInvoice(int orderId, List<Object[]> rowData, String entityName, String entityPhone,
                                      String entityAddress, String invoiceTitle) throws DocumentException, IOException {
        // Khởi tạo một tài liệu PDF
        Document document = new Document();
        String directoryPath = "target/PDF";
        String filePath = directoryPath + "/" + orderId + ".pdf";

        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(Paths.get(directoryPath))) {
            Files.createDirectories(Paths.get(directoryPath));
        }

        // Tạo một PdfWriter để viết nội dung vào file PDF
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Mở tài liệu
        document.open();

        // Load the custom font from resources
        InputStream fontStream = PDF.class.getResourceAsStream("/fonts/arial-unicode-ms.ttf");
        if (fontStream == null) {
            throw new FileNotFoundException("Font file not found in resources/fonts/arial-unicode-ms.ttf");
        }
        BaseFont baseFont = BaseFont.createFont("arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, false,
                fontStream.readAllBytes(), null);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        Font boldFont = new Font(baseFont, 12, Font.BOLD);
        Font titleFont = new Font(baseFont, 16, Font.BOLD);

        // Thông tin cửa hàng
        String store = "Cửa hàng phân bón Yến Nhi";
        String sdt = "0375047xxx";
        String address = "Lam Phụng - Đại Đồng - Đại Lộc - Quảng Nam";

        // Thông tin tiêu đề
        DateController dateController = new DateController();
        String date = dateController.getFormattedCurrentDateTime("yyyy-MM-dd HH:mm:ss");

        // Tạo bảng thông tin cửa hàng và tiêu đề
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Tạo ô thông tin cửa hàng
        PdfPCell storeInfoCell = new PdfPCell();
        storeInfoCell.addElement(new Paragraph(store, boldFont));
        storeInfoCell.addElement(new Paragraph("SĐT: " + sdt, font));
        storeInfoCell.addElement(new Paragraph("Địa chỉ: " + address, font));
        storeInfoCell.setBorder(Rectangle.NO_BORDER);

        // Tạo ô thông tin tiêu đề
        PdfPCell titleInfoCell = new PdfPCell();
        titleInfoCell.addElement(new Paragraph(invoiceTitle, titleFont));
        titleInfoCell.addElement(new Paragraph("Ngày: " + date, font));
        titleInfoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        titleInfoCell.setBorder(Rectangle.NO_BORDER);

        // Thêm các ô vào bảng
        table.addCell(storeInfoCell);
        table.addCell(titleInfoCell);

        // Thêm bảng vào tài liệu
        document.add(table);

        // Tạo thông tin khách hàng/nhà cung cấp
        PdfPTable entityTable = new PdfPTable(1);
        entityTable.setWidthPercentage(100);
        entityTable.setSpacingBefore(10f);
        entityTable.setSpacingAfter(10f);

        PdfPCell entityInfoCell = new PdfPCell();
        entityInfoCell.addElement(new Paragraph("Name: " + entityName, font));
        entityInfoCell.addElement(new Paragraph("SĐT: " + entityPhone, font));
        entityInfoCell.addElement(new Paragraph("Địa chỉ: " + entityAddress, font));
        entityInfoCell.setBorder(Rectangle.NO_BORDER);

        // Thêm ô thông tin khách hàng/nhà cung cấp vào bảng
        entityTable.addCell(entityInfoCell);

        // Thêm bảng khách hàng/nhà cung cấp vào tài liệu
        document.add(entityTable);

        // Tạo bảng sản phẩm
        PdfPTable productTable = new PdfPTable(5); // 5 cột bao gồm STT
        productTable.setWidthPercentage(100);
        productTable.setSpacingBefore(10f);
        productTable.setSpacingAfter(10f);

        // Thêm tiêu đề cho bảng sản phẩm
        PdfPCell cell = new PdfPCell(new Phrase("STT", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("ID Sản phẩm", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Tên sản phẩm", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Số lượng", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Thành tiền", boldFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        productTable.addCell(cell);

        // Thêm dữ liệu sản phẩm từ JTableRight
        int stt = 1;
        float total = 0;
        for (Object[] row : rowData) {
            productTable.addCell(new PdfPCell(new Phrase(String.valueOf(stt++), font))); // STT
            productTable.addCell(new PdfPCell(new Phrase(row[0].toString(), font))); // ID Sản phẩm
            productTable.addCell(new PdfPCell(new Phrase(row[1].toString(), font))); // Tên sản phẩm
            productTable.addCell(new PdfPCell(new Phrase(row[2].toString(), font))); // Số lượng
            float quantity = Float.parseFloat(row[2].toString());
            float unitPrice = Float.parseFloat(row[3].toString());
            float totalPrice =  unitPrice;
            productTable.addCell(new PdfPCell(new Phrase(String.valueOf(totalPrice), font))); // Thành tiền
            total += totalPrice;
        }

        // Thêm hàng tổng cộng
        for (int i = 0; i < 4; i++) {
            productTable.addCell(new PdfPCell(new Phrase("", font)));
        }
        PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + total, boldFont));
        totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        productTable.addCell(totalCell);

        // Thêm bảng sản phẩm vào tài liệu
        document.add(productTable);

        // Đóng tài liệu
        document.close();

        System.out.println("PDF created successfully at " + filePath);
    }
}
