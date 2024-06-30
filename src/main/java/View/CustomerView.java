package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

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

public class CustomerView extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField addressField;
    private JButton okButton;
    private JButton cancelButton;

    public static void main(String[] args) {
        try {
            CustomerView dialog = new CustomerView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerView() {
  	  // Khai báo và khởi tạo ImageIcon từ đường dẫn đến tệp hình ảnh
        ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

        // Chỉnh sửa kích thước hình ảnh nếu cần thiết
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt icon cho JFrame
        setIconImage(scaledIcon.getImage());
        
        setBounds(100, 100, 720, 465);
        
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNewLabel = new JLabel("Customer Information");
        Color tcolor = Color.decode("#5d7b6f");
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(tcolor);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel.setBounds(0, 0, 486, 75);
        contentPanel.add(lblNewLabel);

        JPanel panel = new JPanel();
        Color pcolor = Color.decode("#A4C3A2");
        panel.setBackground(pcolor);

        panel.setBounds(0, 74, 486, 235);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblId = new JLabel("Customer ID");
        lblId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblId.setBounds(73, 20, 105, 34);
        panel.add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblName.setBounds(73, 70, 105, 34);
        panel.add(lblName);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPhone.setBounds(73, 124, 105, 34);
        panel.add(lblPhone);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblAddress.setBounds(73, 180, 105, 34);
        panel.add(lblAddress);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        idField.setBounds(207, 20, 188, 34);
        panel.add(idField);
        idField.setColumns(10);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nameField.setColumns(10);
        nameField.setBounds(207, 71, 188, 34);
        panel.add(nameField);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        phoneField.setColumns(10);
        phoneField.setBounds(207, 125, 188, 34);
        panel.add(phoneField);

        addressField = new JTextField();
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addressField.setColumns(10);
        addressField.setBounds(207, 180, 188, 34);
        panel.add(addressField);

        JPanel buttonPane = new JPanel();
        Color bcolor = Color.decode("#EAE7D6");

        buttonPane.setBackground(bcolor);
        buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        buttonPane.setBounds(0, 310, 486, 53);
        contentPanel.add(buttonPane);
        buttonPane.setLayout(null);

        okButton = new JButton("OK");
        okButton.setBounds(294, 10, 70, 33);
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(388, 10, 70, 33);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
