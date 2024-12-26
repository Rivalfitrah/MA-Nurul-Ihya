
package com.example.manurul.Controller;



import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.manurul.Dto.GaleriiDto;
import com.example.manurul.Repository.GaleriiRepository;
import com.example.manurul.entity.Galerii;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class Galeriicontroller {
    

    @Autowired
    private GaleriiRepository repo;

    
    
    @GetMapping("/galeri")
    public String showGaleriPage(Model model) {
        List<Galerii> galeriiList = repo.findAll();
        model.addAttribute("galeriiList", galeriiList);
        return "galeri";
    }
    
    
    @GetMapping("/galerii")
    public String showgaleripage(Model model){
        List<Galerii> galerii = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("galerii", galerii);
        return"galerii";
    }
    

    @GetMapping("/create_galeri")
    public String buatlomba(Model model) {
        GaleriiDto galeriiDto = new GaleriiDto();
        model.addAttribute("galeriiDto", galeriiDto);
        return "create_galeri";
    }
    
    @PostMapping("/create_galeri")
    public String buatlomba(GaleriiDto galeriiDto, BindingResult result) {
        if (galeriiDto.getGambar().isEmpty()) {
            result.addError(new FieldError("galeriiDto", "gambar", "Gambar tidak boleh kosong"));
        }
        if (result.hasErrors()) {
            return"create_galerii";
        }

        //save image 
        MultipartFile gambar = galeriiDto.getGambar();
        String storageFileName = gambar.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                
            }
            try (InputStream inputStream = gambar.getInputStream ()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("exception" + ex.getMessage());
        }
        
        Galerii galerii = new Galerii();
        galerii.setGambar(storageFileName);

        repo.save(galerii);
        return "redirect:/galerii";
    }


    @GetMapping("/delete_galeri")
    public String delete(@RequestParam int id){

        try {

            Galerii galerii = repo.findById(id).get();
            Path imagePath = Paths.get("public/images/" + galerii.getGambar());

            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println("Exception : " + e.getMessage());
            }

            //delete menu
            repo.delete(galerii);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        return "redirect:/galerii";
    }


    




}

        
        








