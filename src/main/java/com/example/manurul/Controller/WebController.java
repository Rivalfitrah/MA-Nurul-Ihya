package com.example.manurul.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

   //Home page
    @GetMapping("/home")
    public String homepage(){
        return"home";
    }
    
    // @GetMapping("/galeri")
    // public String galeripage(){
    //     return"galeri";
    // }
    
    @GetMapping("/guru-guru")
    public String gurupage(){
        return"guru-guru";
    }
    
    @GetMapping("/ppdb")
    public String ppdbpage(){
        return"ppdb";
    }
    
    // @GetMapping("/prestasi")
    // public String prestasipage(){
    //     return"prestasi";
    // }
    

    @GetMapping("/visimisi")
    public String visiMisiPage() {
        return "visimisi"; // Ini adalah nama template Thymeleaf yang akan digunakan
    }


    @GetMapping("/program-sekolah")
    public String programsekolahpage() {
        return "program-sekolah"; // Ini adalah nama template Thymeleaf yang akan digunakan
    }
    
   @GetMapping("/sejarah")
   public String sejarahpage() {
       return "sejarah";
   }

}
