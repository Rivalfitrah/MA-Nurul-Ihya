package com.example.manurul.Dto;

import org.springframework.web.multipart.MultipartFile;

public class LombaDto {

    private String nama;
    private String juara;
    private String prestasi;
    private MultipartFile gambar;
    
    
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getJuara() {
        return juara;
    }
    public void setJuara(String juara) {
        this.juara = juara;
    }

    public MultipartFile getGambar() {
        return gambar;
    }
    public void setGambar(MultipartFile gambar) {
        this.gambar = gambar;
    }
    public String getPrestasi() {
        return prestasi;
    }
    public void setPrestasi(String prestasi) {
        this.prestasi = prestasi;
    }




}