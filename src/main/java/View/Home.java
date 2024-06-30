package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import Controller.BaseController;
import Controller.CategoriesController;
import Controller.ContactController;
import Controller.CustomerController;
import Controller.ImportController;
import Controller.OrderController;
import Controller.ProductController;
import Controller.SuppliersController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Pane;
	private JTable table;
	private BaseController currentController;
	private JButton insertBtn;
	private JButton editBtn;
	private JButton deleteBtn;
	private JButton viewbtn;
	private JScrollPane scrollPane;
	private JPanel customerPanel;
	private ContactView contactView;
	private ContactController contactController;
	private StatisticalView statisticalView;
	private JLabel timelb;
	private PlaceholderTextField searchField;
	private JPanel searchpanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					new Home();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {

		// Khai báo và khởi tạo ImageIcon từ đường dẫn đến tệp hình ảnh
		ImageIcon icon = new ImageIcon("C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\logo.jpg");

		// Chỉnh sửa kích thước hình ảnh nếu cần thiết
		Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		// Tạo một ImageIcon mới từ hình ảnh đã được chỉnh sửa
		ImageIcon scaledIcon = new ImageIcon(image);

		// Đặt icon cho JFrame
		setIconImage(scaledIcon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 465);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 649);
		Pane = new JPanel();
		Pane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(Pane);
		Pane.setLayout(null);

		JPanel menuPanel = new JPanel();
		Color color = Color.decode("#a4c3a2");
		menuPanel.setBackground(color);
		menuPanel.setForeground(Color.WHITE);
		menuPanel.setBounds(-12, 100, 292, 522);
		Pane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton donhangBtn = new JButton("Đơn bán");
		donhangBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		donhangBtn.setBounds(0, 0, 292, 50);
		menuPanel.add(donhangBtn);

		JButton nhaphangBtn = new JButton("Đơn nhập");
		nhaphangBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		nhaphangBtn.setBounds(0, 49, 292, 50);
		menuPanel.add(nhaphangBtn);

		JButton sanphamBtn = new JButton("Sản phẩm - Kho hàng");
		sanphamBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		sanphamBtn.setBounds(0, 98, 292, 50);
		menuPanel.add(sanphamBtn);

		JButton khachhangBtn = new JButton("Khách hàng");
		khachhangBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		khachhangBtn.setBounds(0, 192, 292, 50);
		menuPanel.add(khachhangBtn);

		JButton thongkeBtn = new JButton("Thống kê");
		thongkeBtn.setBounds(0, 286, 292, 50);
		thongkeBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuPanel.add(thongkeBtn);

		JButton taikhoanBtn = new JButton("Tài khoản - Liên hệ");
		taikhoanBtn.setBounds(0, 335, 292, 50);
		taikhoanBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuPanel.add(taikhoanBtn);

		JButton supbtn = new JButton("Nhà cung cấp");
		supbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		supbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		supbtn.setBounds(0, 241, 292, 50);
		menuPanel.add(supbtn);

		JButton phanloaibtn = new JButton("Phân loại");
		phanloaibtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		phanloaibtn.setBounds(0, 145, 292, 50);
		menuPanel.add(phanloaibtn);

		JPanel navPanel = new JPanel();
		Color ncolor = Color.decode("#5D7B6F");
		navPanel.setBackground(ncolor);
		navPanel.setBounds(280, 0, 906, 100);
		navPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		Pane.add(navPanel);
		navPanel.setLayout(null);

		insertBtn = createButton("Create", "C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\createicon.png", 48,
				10, 85, 80);
		navPanel.add(insertBtn);

		editBtn = createButton("Edit", "C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\editicon.png", 170, 10,
				85, 80);
		navPanel.add(editBtn);

		deleteBtn = createButton("Delete", "C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\deleteicon.jpg", 293,
				10, 85, 80);
		navPanel.add(deleteBtn);

		viewbtn = createButton("View", "C:\\IT\\JavaEclipse\\Store\\src\\main\\resources\\img\\viewcon.png", 416, 10,
				85, 80);
		navPanel.add(viewbtn);

		searchpanel = new JPanel();
		searchpanel.setBounds(551, 10, 311, 80);
		searchpanel.setBackground(color);
		navPanel.add(searchpanel);
		searchpanel.setLayout(null);

		searchField = new PlaceholderTextField("Tìm kiếm...");
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchField.setBounds(28, 26, 259, 30);
		searchpanel.add(searchField);
		searchField.setColumns(10);

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search();
			}

			private void search() {
				if (currentController instanceof OrderController) {
					((OrderController) currentController).search(searchField.getText());
				}

				if (currentController instanceof ImportController) {
					((ImportController) currentController).search(searchField.getText());
				}

				if (currentController instanceof CustomerController) {
					((CustomerController) currentController).search(searchField.getText());
				}

				if (currentController instanceof ProductController) {
					((ProductController) currentController).search(searchField.getText());
				}
			}
		});
		JPanel footerPanel = new JPanel();
		footerPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Color fclolor = Color.decode("#A4C3A2");
		footerPanel.setBackground(fclolor);
		footerPanel.setBounds(280, 569, 906, 43);
		Pane.add(footerPanel);
		footerPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"Ứng dụng được tạo bởi sinh viên 23IT283 - Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(65, 10, 772, 23);
		footerPanel.add(lblNewLabel);

		JPanel ttPanel = new JPanel();
		Color tcolor = Color.decode("#A4C3A2");
		ttPanel.setBackground(tcolor);
		ttPanel.setBounds(0, 0, 280, 100);
		Pane.add(ttPanel);
		ttPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ttPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 100, 906, 472);
		Pane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// Thay đổi các nút trong phương thức khởi tạo của lớp Home
		donhangBtn.setBackground(getColorBtn());
		nhaphangBtn.setBackground(getColorBtn());
		sanphamBtn.setBackground(getColorBtn());
		khachhangBtn.setBackground(getColorBtn());
		thongkeBtn.setBackground(getColorBtn());
		taikhoanBtn.setBackground(getColorBtn());
		supbtn.setBackground(getColorBtn());
		phanloaibtn.setBackground(getColorBtn());

		timelb = new JLabel("");
		timelb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		timelb.setHorizontalAlignment(SwingConstants.CENTER);
		timelb.setBounds(10, 384, 282, 128);
		menuPanel.add(timelb);

		// Add avatar panel
		AvatarPanel avatarPanel = new AvatarPanel(
				"C:\\\\IT\\\\JavaEclipse\\\\Store\\\\src\\\\main\\\\resources\\\\img\\\\logo.jpg",
				Color.decode("#a4c3a2"));
		avatarPanel.setBounds(90, 5, 90, 90);
		ttPanel.add(avatarPanel);

		// Start the timer to update the time label
		startTimer();

		OrderController orderController = new OrderController(Pane, table);
		donhangBtn.addActionListener(e -> {
			currentController = orderController;
			orderController.loadData();
			showViewpane();
		});

		CustomerController customerController = new CustomerController(Pane, table);
		khachhangBtn.addActionListener(e -> {
			currentController = customerController;
			customerController.loadData();
			showTable();
		});

		ImportController importController = new ImportController(Pane, table);
		nhaphangBtn.addActionListener(e -> {
			currentController = importController;
			importController.loadData();
			showViewpane();
		});

		ProductController productController = new ProductController(Pane, table);
		sanphamBtn.addActionListener(e -> {
			currentController = productController;
			productController.loadData();
			showTable();
		});

		taikhoanBtn.addActionListener(e -> {
			if (contactView == null) {
				contactView = new ContactView();
				contactController = new ContactController(contactView);
			}
			showContactPanel();
		});

		thongkeBtn.addActionListener(e -> {
			if (statisticalView == null) {
				statisticalView = new StatisticalView();
				statisticalView.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
			showStatisticalPanel();
		});

		CategoriesController categoriesController = new CategoriesController(Pane, table);
		phanloaibtn.addActionListener(e -> {
			currentController = categoriesController;
			categoriesController.loadData();
		});

		SuppliersController suppliersController = new SuppliersController(Pane, table);
		supbtn.addActionListener(e -> {
			currentController = suppliersController;
			suppliersController.loadData();
		});

		insertBtn.addActionListener(e -> {
			if (currentController != null) {
				currentController.create();
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một menu trước.");
			}
		});

		editBtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				if (currentController != null) {
					currentController.edit(selectedRow);
				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn một menu trước.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để chỉnh sửa.");
			}
		});

		deleteBtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				if (currentController != null) {
					currentController.delete(selectedRow);
				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn một menu trước.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để xóa.");
			}
		});

		viewbtn.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				int importId = (int) table.getValueAt(selectedRow, 0); // Get the Import ID from the selected row
				currentController.view(importId); // Pass the importId to the view method
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một mục để xem.");
			}
		});

		currentController = orderController;
		orderController.loadData();
	}

	private JButton createButton(String text, String iconPath, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBackground(getColorBtn());
		button.setBounds(x, y, width, height);
		try {
			BufferedImage img = ImageIO.read(new File(iconPath));
			Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(scaledImg));
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return button;
	}

	private void showViewpane() {
		scrollPane.setViewportView(table);
		insertBtn.setVisible(true);
		editBtn.setVisible(true);
		deleteBtn.setVisible(true);
		viewbtn.setVisible(true);
		searchpanel.setVisible(true);
	}

	// Method to show the contact panel
	private void showContactPanel() {
		scrollPane.setViewportView(contactView);
		insertBtn.setVisible(false);
		editBtn.setVisible(false);
		deleteBtn.setVisible(false);
		viewbtn.setVisible(false);
		searchpanel.setVisible(false);

	}

	// Method to show the statistical panel
	private void showStatisticalPanel() {
		scrollPane.setViewportView(statisticalView);
		insertBtn.setVisible(false);
		editBtn.setVisible(false);
		deleteBtn.setVisible(false);
		viewbtn.setVisible(false);
		searchpanel.setVisible(false);
	}

	// Method to show the table
	private void showTable() {
		scrollPane.setViewportView(table);
		insertBtn.setVisible(true);
		editBtn.setVisible(true);
		deleteBtn.setVisible(true);
		viewbtn.setVisible(false);
		searchpanel.setVisible(true);
	}

	public static Color getColorBtn() {
		Color color = Color.decode("#EAE7D6");
		return color;
	}

	// Method to update the time in the timelb label
	private void updateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime time = LocalTime.now();
		timelb.setText(time.format(formatter));
	}

	// Method to start the timer to update the time label
	private void startTimer() {
		Timer timer = new Timer(1000, e -> updateTime());
		timer.start();
	}

	public static void logout() {
		// Dispose the Home frame
		EventQueue.invokeLater(() -> {
			try {
				// Open the LoginView
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}

// Custom JPanel subclass for circular avatar
class AvatarPanel extends JPanel {
	private BufferedImage image;

	public AvatarPanel(String imagePath, Color backgroundColor) {
		try {
			image = ImageIO.read(new File(imagePath));
			setOpaque(false); // Set panel to transparent
			setBackground(backgroundColor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		// Calculate the size of the circle to fit inside the square
		int diameter = Math.min(getWidth(), getHeight());

		// Set the clip shape to a circle
		g2d.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));

		// Draw the image centered within the circle
		int x = (getWidth() - diameter) / 2;
		int y = (getHeight() - diameter) / 2;
		g2d.drawImage(image, x, y, diameter, diameter, null);

		g2d.dispose();
	}

}
