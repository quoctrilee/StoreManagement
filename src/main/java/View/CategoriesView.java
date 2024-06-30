package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class CategoriesView extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField idField;
    private JTextField nameField;
    private JButton okButton;
    private JButton cancelButton;

    public static void main(String[] args) {
        try {
            CategoriesView dialog = new CategoriesView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CategoriesView() {
    	
  	  // Khai báo và khởi tạo ImageIcon từ đường dẫn đến tệp hình ảnh
        ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

        // Chỉnh sửa kích thước hình ảnh nếu cần thiết
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt icon cho JFrame
        setIconImage(scaledIcon.getImage());
   
        setBounds(100, 100, 720, 465);
        
    	setLocationRelativeTo(null);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("Category Information");
        Color tcolor = Color.decode("#5D7B6F");
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(tcolor);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel.setBounds(0, 0, 436, 50);
        contentPanel.add(lblNewLabel);

        JPanel panel = new JPanel();
        Color pcolor = Color.decode("#A4C3A2");
        panel.setBackground(pcolor);
        panel.setBounds(0, 50, 436, 150);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblId = new JLabel("Category ID");
        lblId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblId.setBounds(60, 10, 100, 30);
        panel.add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblName.setBounds(60, 60, 100, 30);
        panel.add(lblName);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        idField.setBounds(180, 10, 180, 30);
        panel.add(idField);
        idField.setColumns(10);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nameField.setBounds(180, 60, 180, 30);
        panel.add(nameField);
        nameField.setColumns(10);

        JPanel buttonPane = new JPanel();
        Color bcolor = Color.decode("#EAE7D6");
        buttonPane.setBackground(bcolor);
        buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        buttonPane.setBounds(0, 200, 436, 53);
        contentPanel.add(buttonPane);
        buttonPane.setLayout(null);

        okButton = new JButton("OK");
        okButton.setBounds(246, 10, 70, 33);
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(340, 10, 70, 33);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
