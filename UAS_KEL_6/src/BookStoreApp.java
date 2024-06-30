import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BookStoreApp extends JFrame {
    private Book[] books; 
    private int bookCount; 
    private JTable bookTable; 
    private BookTableModel bookTableModel; 

    public BookStoreApp() {
        books = new Book[3]; 
        bookCount = 0; 

        // Set properti frame utama
        setTitle("Aplikasi Toko Buku");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Buat dan atur komponen input
        JLabel titleLabel = new JLabel("Judul:");
        JTextField titleField = new JTextField(20);
        JLabel authorLabel = new JLabel("Nama Pengarang:");
        JTextField authorField = new JTextField(20);
        JLabel publisherLabel = new JLabel("Penerbit:");
        JTextField publisherField = new JTextField(20);
        JLabel yearLabel = new JLabel("Tahun Cetak:");
        JTextField yearField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Kategori:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Semua umur", "Remaja", "Dewasa", "Anak-anak"});

        JButton addButton = new JButton("Tambah Buku"); // Tombol untuk menambah buku

        // Inisialisasi model tabel dan tabel
        bookTableModel = new BookTableModel();
        bookTable = new JTable((TableModel) bookTableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Set ukuran preferensi untuk tabel dan scroll pane
        bookTable.setPreferredScrollableViewportSize(new Dimension(580, 200));
        scrollPane.setMinimumSize(new Dimension(580, 200));

        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tambahkan komponen input ke layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);
        gbc.gridx = 1;
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(authorLabel, gbc);
        gbc.gridx = 1;
        add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(publisherLabel, gbc);
        gbc.gridx = 1;
        add(publisherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(yearLabel, gbc);
        gbc.gridx = 1;
        add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Tambahkan ActionListener untuk tombol "Tambah Buku"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookCount < 3) { // Memeriksa apakah masih slot untuk buku baru
                    // Ambil data dari kolom input
                    String judul = titleField.getText();
                    String pengarang = authorField.getText();
                    String penerbit = publisherField.getText();
                    int tahun_terbit = Integer.parseInt(yearField.getText());
                    String kategori = (String) categoryComboBox.getSelectedItem();

                    // Tambahkan buku ke array
                    books[bookCount] = new Book(judul, pengarang, penerbit, tahun_terbit, kategori);
                    bookCount++;
                    bookTableModel.addBook(books[bookCount - 1]); // Tambahkan buku ke tabel
                    JOptionPane.showMessageDialog(null, "Buku berhasil ditambahkan!");

                    // Kosongkan kolom input
                    titleField.setText("");
                    authorField.setText("");
                    publisherField.setText("");
                    yearField.setText("");
                    categoryComboBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Data buku penuh!"); // Tampilkan pesan jika array penuh
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookStoreApp().setVisible(true); // Start Aplikasi
            }
        });
    }
}

// Kelas Book untuk menyimpan data buku
class Book {
    private String judul;
    private String pengarang;
    private String penerbit;
    private int tahun_terbit;
    private String kategori;

    public Book(String judul , String pengarang, String penerbit, int tahun_terbit, String kategori) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun_terbit = tahun_terbit;
        this.kategori = kategori;
    }

    // Getters dan Setters
    public String getTitle() { return judul; }
    public void setTitle(String judul) { this.judul = judul; }
    public String getAuthor() { return pengarang; }
    public void setAuthor(String pengarang) { this.pengarang = pengarang; }
    public String getPublisher() { return penerbit; }
    public void setPublisher(String penerbit) { this.penerbit = penerbit; }
    public int getYear() { return tahun_terbit; }
    public void setYear(int tahun_terbit) { this.tahun_terbit = tahun_terbit; }
    public String getCategory() { return kategori; }
    public void setCategory(String kategori) { this.kategori = kategori; }
}

// Kelas BookTableModel untuk mengelola data buku dalam tabel
class BookTableModel extends AbstractTableModel {
    private String[] columnNames = {"Judul", "Pengarang", "Penerbit", "Tahun Cetak", "Kategori"};
    private java.util.List<Book> books = new java.util.ArrayList<>();

    @Override
    public int getRowCount() {
        return books.size(); // Mengembalikan jumlah baris dalam tabel
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Mengembalikan jumlah kolom dalam tabel
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex]; // Mengembalikan nama kolom berdasarkan indeks
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex); // Ambil buku berdasarkan indeks baris
        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor(); 
            case 2:
                return book.getPublisher();
            case 3:
                return book.getYear();
            case 4:
                return book.getCategory();
            default:
                return null;
        }
    }

    public void addBook(Book book) {
        books.add(book);
        fireTableRowsInserted(books.size() - 1, books.size() - 1);
    }
}
