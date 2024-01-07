import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.*;

public class Transaksi extends AhviMart 
{
    static Connection conn;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception
        {
            String pilihan;
            boolean next = true;

            String url = "jdbc:mysql://localhost:3306/ahvimart";

            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, "root", "");

                    String username = "admin";
                    String password = "pass12";
 
                    System.out.println("Selamat datang! Silahkan login");
   
                    System.out.print("Masukkan username: ");
                    String inputUsername = input.nextLine();
                
                    System.out.print("Masukkan password: ");
                    String inputPassword = input.nextLine();

                    if (inputUsername.equals(username) && inputPassword.equals(password)){
                        System.out.println("Login berhasil! Selamat datang, " + username + "!");
                    } else {
                        System.out.println("Login gagal. Periksa kembali username, password");
                    }

                while(next)
                {
                    Penyajian p = new Penyajian();

                        p.set();
                        p.date();
                        System.out.println("\n\n           ===============  ");
                        System.out.println("               AhviMart      ");
                        System.out.println("           ===============  ");
                        System.out.println("1. Lihat Data ");
            		    System.out.println("2. Masukkan Data ");
            		    System.out.println("3. Update Data ");
            		    System.out.println("4. Hapus Data ");
            		    System.out.println("5. Cari Data ");

                        System.out.print("Inputkan Pilihan : ");
                        pilihan = input.next();

                    switch(pilihan)
                    {
                        case "1":    
                            lihatdata();
                        break;

                        case "2":
                            masukkandata();
                        break;

                        case "3":
                            updatedata();
                        break;

                        case "4":
                            hapusdata();
                        break;

                        case "5":
                            caridata();
                        break;

                        default: System.out.println("Input tidak sesuai");
                    }

                    System.out.println("Lanjutkan?");
                    pilihan = input.next();
                    next = pilihan.equalsIgnoreCase("ya");
                }
                System.out.println("Pendataan selesai");
            }
            catch(SQLException ex)
            {
                System.err.println("Tidak berhasil terkoneksi");
            }
            catch(InputMismatchException n)
            {
                System.out.println("Inputan Salah!!!");
            } input.close();
        }

        public static void lihatdata() throws SQLException
        {
            String sql ="SELECT * FROM db_ahvisunday";
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);

            while(result.next())
            {
                System.out.println();
                System.out.print("No.ID             : ");
                System.out.println(result.getInt("no_id")); 
                System.out.print("Nama              : ");
                System.out.println(result.getString("nama"));
                System.out.print("Merek HP     : ");
                System.out.println(result.getString("merek_hp"));
                System.out.print("Motif     : ");
                System.out.println(result.getString("motif"));
                System.out.print("Harga             : ");
                System.out.println(result.getDouble("harga"));
                System.out.print("Jumlah             : ");
                System.out.println(result.getInt("jumlah"));
                System.out.print("Total Harga       : ");
                System.out.println(result.getDouble("total_harga"));
            }
        }

        public static void masukkandata() throws SQLException
        {
            System.out.println("  Masukkan Data  ");

            try
            {
                System.out.print("No.ID         : ");
                noid = input.nextInt();
                System.out.print("Nama          : ");
                nama = input.next();
                System.out.print("Merek Hp      : ");
                merekhp = input.next();
                System.out.print("Motif         : ");
                motif = input.next();
                System.out.print("Harga         : ");
                harga = input.nextInt();
                System.out.print("Jumlah         : ");
                jumlah = input.nextInt();
                
                totalharga = jumlah * harga;
                System.out.print("Total Harga   : "+totalharga);
                System.out.println();

                String sql = "INSERT INTO db_ahvisunday (no_id, nama, merek_hp, motif, harga, jumlah, total_harga) VALUES ('"+noid+"','"+nama+"','"+merekhp+"','"+motif+"','"+harga+"','"+jumlah+"','"+totalharga+"')";
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Data Berhasil Ditambahkan");
            }
            catch(SQLException e)
            {
                System.out.println("Data Tidak Berhasil Ditambahkan!!!");
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Error!!!");
            }
            catch(NullPointerException n)
            {
                System.out.println("Data Tidak Berhasil Ditambahkan!!!");
            }
        }

        public static void updatedata() throws SQLException
        {
            try
            {
                lihatdata();
                System.out.println("\n\nPilih No.ID  : ");
                Integer noid = input.nextInt();

                String sql = "SELECT * FROM db_ahvisunday WHERE no_id = " +noid;

                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);

                if (result.next()) 
                {

                    System.out.print("\nNama ["+result.getString("nama")+"]\t : ");
                    String nama = input.next();
                    System.out.print("\nMerek Hp ["+result.getString("merek_hp")+"]\t : ");
                    String merekhp = input.next();
                    System.out.print("\nMotif ["+result.getString("motif")+"]\t : ");
                    String motif = input.next();
                    System.out.print("\nHarga ["+result.getDouble("harga")+"]\t : ");
                    double harga = input.nextDouble();
                    System.out.print("\nJumlah ["+result.getDouble("jumlah")+"]\t : ");
                    Integer jumlah = input.nextInt();
                    System.out.print("\nTotal Harga ["+result.getDouble("total_harga")+"]\t : ");
                    double totalharga = input.nextDouble();

                    sql = "UPDATE db_ahvisunday SET nama='"+nama+"', merek_hp='"+merekhp+"', motif='"+motif+"', harga='"+harga+"',jumlah='"+jumlah+"',total_harga='"+totalharga+"' WHERE no_id='"+noid+"'";
                    if(statement.executeUpdate(sql) > 0)
                    {
                        System.out.println("\n\nData Diperbarui (No.ID "+noid+")");
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        }

        public static void hapusdata() throws SQLException
        {
            try
            {
                lihatdata();
                System.out.println("\n\nNo.ID    :");
                Integer noid = input.nextInt();

                String sql = "DELETE FROM db_ahvisunday WHERE no_id = "+ noid;

                Statement statement = conn.createStatement();
                if(statement.executeUpdate(sql) > 0)
                    {
                        System.out.println("\nData Terhapus");
                    }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Terjadi Kesalahan!!!");
            }
            catch(SQLException e)
            {
                System.out.println("Terjadi kesalahan dalam menghapus data barang");
            }
        }

        public static void caridata() throws SQLException
        {
            try
            {
                System.out.print("\n\nMasukkan Nama    :");
        
                String keyword = input.nextLine();
                Statement statement = conn.createStatement();
                String sql = "SELECT * FROM db_ahvisunday WHERE nama LIKE '%"+keyword+"%'";
                ResultSet result = statement.executeQuery(sql); 

                while(result.next())
                {
                    System.out.println();
                    System.out.print("No.ID             : ");
                    System.out.println(result.getInt("no_id")); 
                    System.out.print("Nama              : ");
                    System.out.println(result.getString("nama"));
                    System.out.print("Merek HP     : ");
                    System.out.println(result.getString("merek_hp"));
                    System.out.print("Motif     : ");
                    System.out.println(result.getString("motif"));
                    System.out.print("Harga             : ");
                    System.out.println(result.getDouble("harga"));
                    System.out.print("Jumlah             : ");
                    System.out.println(result.getInt("jumlah"));
                    System.out.print("Total Harga       : ");
                    System.out.println(result.getDouble("total_harga"));
                }
            }
            catch(InputMismatchException i)
            {
                System.out.println("Inputan Salah");

            }
        }
}       

