package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ImportDetailsModel;
import Model.ProductsModel;
import DAO.EditImportDAO;
import DAO.ImportDetailsDAO;
import DAO.ProductsDAO;

public class ImportConController {
    private JPanel contentPanel;
    private JTable jTableLeft;
    private JTable jTableRight;
    private DefaultTableModel tableModelLeft;
    private DefaultTableModel tableModelRight;
    private int importId;

    public ImportConController(JPanel contentPanel, JTable jTableLeft, JTable jTableRight, int importId) {
        this.contentPanel = contentPanel;
        this.jTableLeft = jTableLeft;
        this.jTableRight = jTableRight;
        this.importId = importId;

        tableModelLeft = new DefaultTableModel(
                new Object[]{"Product ID", "Name", "Description", "Quantity", "Unit Price"}, 0);
        jTableLeft.setModel(tableModelLeft);

        tableModelRight = new DefaultTableModel(
                new Object[]{"Product ID", "Name", "Description", "Quantity", "Total Price"}, 0);
        jTableRight.setModel(tableModelRight);

        loadProducts();
        loadImportDetails();
    }

    private void loadProducts() {
        ProductsDAO productsDAO = new ProductsDAO();
        List<ProductsModel> products = productsDAO.selectAll();
        for (ProductsModel product : products) {
            tableModelLeft.addRow(new Object[]{product.getProductid(), product.getName(), product.getDescription(),
                    product.getQuantity(), product.getPrice()});
        }
    }

    private void loadImportDetails() {
        EditImportDAO editImportDAO = new EditImportDAO();
        List<ImportDetailsModel> importDetailsList = editImportDAO.selectByImportId(importId);

        tableModelRight.setRowCount(0); // Clear existing rows

        for (ImportDetailsModel detail : importDetailsList) {
            ProductsModel product = editImportDAO.selectById(detail.getProductd());
            if (product != null) {
                tableModelRight.addRow(new Object[]{detail.getProductd(), product.getName(),
                        product.getDescription(), detail.getQuantity(), detail.getUnitprice()});
            }
        }
    }

    public void addProductToImport(int productId, int quantity) {
        boolean productExists = false;

        for (int i = 0; i < tableModelRight.getRowCount(); i++) {
            if ((int) tableModelRight.getValueAt(i, 0) == productId) {
                int existingQuantity = (int) tableModelRight.getValueAt(i, 3);
                int newQuantity = existingQuantity + quantity;
                if (newQuantity <= 0) {
                    JOptionPane.showMessageDialog(contentPanel, "Số lượng phải lớn hơn 0!");
                    return;
                }
                float unitPrice = (float) tableModelRight.getValueAt(i, 4) / existingQuantity;
                tableModelRight.setValueAt(newQuantity, i, 3);
                tableModelRight.setValueAt(unitPrice * newQuantity, i, 4);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            for (int i = 0; i < tableModelLeft.getRowCount(); i++) {
                if ((int) tableModelLeft.getValueAt(i, 0) == productId) {
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(contentPanel, "Số lượng phải lớn hơn 0!");
                        return;
                    }
                    float unitPrice = (float) tableModelLeft.getValueAt(i, 4);
                    float totalPrice = unitPrice * quantity;
                    Object[] rowData = {tableModelLeft.getValueAt(i, 0), tableModelLeft.getValueAt(i, 1),
                            tableModelLeft.getValueAt(i, 2), quantity, totalPrice};
                    tableModelRight.addRow(rowData);
                    break;
                }
            }
        }
    }

    private int getQuantityFromLeftTable(int productId) {
        for (int i = 0; i < tableModelLeft.getRowCount(); i++) {
            if ((int) tableModelLeft.getValueAt(i, 0) == productId) {
                return (int) tableModelLeft.getValueAt(i, 3);
            }
        }
        return 0;
    }

    private int generateUniqueImportDetailId() {
        ImportDetailsDAO importDetailsDAO = new ImportDetailsDAO();
        return importDetailsDAO.generateUniqueImportDetailId();
    }
}
