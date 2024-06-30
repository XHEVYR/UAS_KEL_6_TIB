public class Book {
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

    // Getters and Setters
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