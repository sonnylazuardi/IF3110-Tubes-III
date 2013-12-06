package javaModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseLib.DatabaseAdapter;

public class Barang {
    public int id, id_kategori, harga, stok, counter;
    public String nama, gambar, keterangan;
    private static DatabaseAdapter DBA = new DatabaseAdapter();
    private static ResultSet result;

    public Barang() {
        id = -1;
    }

    // find 1 row data of barang based on query
    public static Barang find(String query) {
        Barang barang = null;
        try {
            DBA.executeQuery(query);
            result = DBA.getQueryResult();
            if (result.next()) {
                barang = new Barang();
                barang.id = Integer.parseInt(result.getString(1));
                barang.id_kategori = Integer.parseInt(result.getString(2));
                barang.nama = result.getString(3);
                barang.harga = Integer.parseInt(result.getString(4));
                barang.gambar = result.getString(5);
                barang.stok = Integer.parseInt(result.getString(6));
                barang.counter = Integer.parseInt(result.getString(7));
                barang.keterangan = result.getString(8);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barang;
    }

    // find 1 row data of barang based on primary key
    public static Barang findByPk(int id) {
        return find("SELECT * FROM barang WHERE id=" + id);
    }

    // find all data of barang based on query
    public static ArrayList<Barang> findAll(String query) {
        ArrayList<Barang> barangs = new ArrayList<Barang>();
        try {
            DBA.executeQuery(query);
            result = DBA.getQueryResult();
            while (result.next()) {
                Barang barang = new Barang();
                barang.id = Integer.parseInt(result.getString(1));
                barang.id_kategori = Integer.parseInt(result.getString(2));
                barang.nama = result.getString(3);
                barang.harga = Integer.parseInt(result.getString(4));
                barang.gambar = result.getString(5);
                barang.stok = Integer.parseInt(result.getString(6));
                barang.counter = Integer.parseInt(result.getString(7));
                barang.keterangan = result.getString(8);
                barangs.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barangs;
    }

    // save barang to db
    public void save() {
        String query;
        if (id == -1) // new
            query = "INSERT INTO barang (id_kategori, nama, harga, gambar, stok, counter, keterangan) VALUES ('" + id_kategori + "','" + nama + "','" + harga + "','" + gambar + "','" + stok + "','" + counter + "','" + keterangan + "')";
        else
            query = "UPDATE barang SET id_kategori = '"+ id_kategori +"', nama = '"+ nama +"', harga = '"+ harga +"', gambar = '"+ gambar +"', stok = '"+ stok +"', counter = '"+ counter +"', keterangan = '"+ keterangan +"' WHERE id = " + id;
        DBA.insertQuery(query);
    }

    // delete barang from db
    public void delete() {
        String query = "DELETE FROM barang WHERE id=" + id;
        DBA.deleteQuery(query);
    }
}