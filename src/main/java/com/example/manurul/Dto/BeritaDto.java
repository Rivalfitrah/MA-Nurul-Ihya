package com.example.manurul.Dto;

import org.springframework.web.multipart.MultipartFile;


public class BeritaDto {

    private String title;
    private MultipartFile gambar;
    private String content;



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public MultipartFile getGambar() {
        return gambar;
    }
    public void setGambar(MultipartFile gambar) {
        this.gambar = gambar;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    

}