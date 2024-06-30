package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.CategoriesDAO;
import Model.CategoriesModel;
import View.CategoriesView;

public class CategoriesController extends BaseController {
    private CategoriesDAO categoriesDAO;
    private Set<Integer> existingCategoryIDs;
    private Random random;

    public CategoriesController(JPanel jPanel, JTable jTable) {
        super(jPanel, jTable);
        this.categoriesDAO = new CategoriesDAO();
        this.existingCategoryIDs = new HashSet<>(categoriesDAO.getAllCategoryIDs());
        this.random = new Random();
        loadData();
    }

    public CategoriesController() {
        this.categoriesDAO = new CategoriesDAO();
        this.existingCategoryIDs = new HashSet<>(categoriesDAO.getAllCategoryIDs());
        this.random = new Random();
    }

    @Override
    public void loadData() {
        if (jTable != null) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.setColumnIdentifiers(new Object[]{"Mã mặt hàng", "Tên mặt hàng"});
            tableModel.setRowCount(0); // Clear existing data

            ArrayList<CategoriesModel> categories = categoriesDAO.selectAll();
            for (CategoriesModel category : categories) {
                tableModel.addRow(new Object[]{category.getCategoryid(), category.getName()});
            }
        }
    }

    @Override
    public void  create() {
        CategoriesView view = new CategoriesView();
        view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        view.setVisible(true);

        view.getOkButton().addActionListener(e -> {
            try {
                int categoryID = Integer.parseInt(view.getIdField().getText());
           

                String name = view.getNameField().getText();
                CategoriesModel category = new CategoriesModel(categoryID, name);

                int result = categoriesDAO.insert(category);
                if (result > 0) {
                    JOptionPane.showMessageDialog(view, "Category added successfully!");
                    existingCategoryIDs.add(categoryID);
                    loadData();
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to add category!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });

        view.getCancelButton().addActionListener(e -> view.dispose());
    }

    @Override
    public void edit(int selectedRow) {
        if (selectedRow >= 0) {
            int categoryID = (int) jTable.getValueAt(selectedRow, 0);
            CategoriesModel category = categoriesDAO.selectById(categoryID);

            if (category != null) {
                CategoriesView view = new CategoriesView();
                view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                view.setVisible(true);

                view.getIdField().setText(String.valueOf(categoryID));
                view.getNameField().setText(category.getName());
                view.getIdField().setEditable(false);

                view.getOkButton().addActionListener(e -> {
                    try {
                        String name = view.getNameField().getText();
                        category.setName(name);

                        int result = categoriesDAO.update(category);
                        if (result > 0) {
                            JOptionPane.showMessageDialog(view, "Category updated successfully!");
                            loadData();
                            view.dispose();
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to update category!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                    }
                });

                view.getCancelButton().addActionListener(e -> view.dispose());
            } else {
                JOptionPane.showMessageDialog(null, "Category not found!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a category to update!");
        }
    }

    @Override
    public void delete(int selectedRow) {
        if (selectedRow >= 0) {
            int categoryID = (int) jTable.getValueAt(selectedRow, 0);
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this category?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                int result = categoriesDAO.delete(categoryID);
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Category deleted successfully!");
                    existingCategoryIDs.remove(categoryID);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete category!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a category to delete!");
        }
    }

	@Override
	public void view(int selectedRow) {
		// TODO Auto-generated method stub
		
	}


}
