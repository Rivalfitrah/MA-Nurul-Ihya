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
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.manurul.Dto.BeritaDto;
import com.example.manurul.Repository.BeritaRepository;
import com.example.manurul.entity.Berita;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class BeritaController {
    

    @Autowired
    private BeritaRepository repo;

    @GetMapping("/berita")
    public String showberitapage(Model model){
        List<Berita> berita = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("berita", berita);
        return"berita";
    }
    

    @GetMapping("/create")
    public String buatberita(Model model) {
        BeritaDto beritaDto = new BeritaDto();
        model.addAttribute("beritaDto", beritaDto);
        return "create_berita";
    }
    
    @GetMapping("/index2")
    public String showberita(Model model) {
        List<Berita> beritas = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("beritas", beritas);
        return "admin-dashboard";
    }
    
    @PostMapping("/create")
    public String buatberita(BeritaDto beritaDto, BindingResult result) {
        if (beritaDto.getGambar().isEmpty()) {
            result.addError(new FieldError("beritaDto", "gambar", "Gambar tidak boleh kosong"));
        }
        if (result.hasErrors()) {
            return"create_berita";
        }

        //save image 
        MultipartFile gambar = beritaDto.getGambar();
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
        
        Berita berita = new Berita();
        berita.setTitle(beritaDto.getTitle());
        berita.setContent(beritaDto.getContent());
        berita.setGambar(storageFileName);

        repo.save(berita);
        return "redirect:/index2";
    }

    @GetMapping("/edit")
    public String showedit(Model model, @RequestParam int id){

        try {
            Berita berita = repo.findById(id).get();
            model.addAttribute("berita", berita);

            BeritaDto beritaDto = new BeritaDto();
            beritaDto.setTitle(berita.getTitle());
            beritaDto.setContent(berita.getContent());

            model.addAttribute("beritaDto", beritaDto);


        } catch (Exception e) {
            System.out.println("Exception : "+ e.getMessage());
            return "redirect:/index2";
        }

        return "edit_berita";
    }

    @PostMapping("/edit")
    public String edit(Model model, @RequestParam int id, @Valid @ModelAttribute BeritaDto beritaDto, BindingResult result){
        
        try {
            Berita berita = repo.findById(id).get();
            model.addAttribute("berita", berita);
            
            if (result.hasErrors()) {
                return "edit_berita";
            }

            if (!beritaDto.getGambar().isEmpty()) {
                //Delete Gambar Lama
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + berita.getGambar());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception e) {
                    System.out.println("Exception : " + e.getMessage());
                }

                //Save new img
                MultipartFile gambar = beritaDto.getGambar();
                String storageFileName = gambar.getOriginalFilename();

                try (InputStream inputStream = gambar.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), 
                    StandardCopyOption.REPLACE_EXISTING);
                }
                berita.setGambar(storageFileName);
            }

            berita.setTitle(beritaDto.getTitle());
            berita.setContent(beritaDto.getContent());

            repo.save(berita);
        }   
        catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
            return "redirect:/index2";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id){

        try {

            Berita berita = repo.findById(id).get();
            Path imagePath = Paths.get("public/images/" + berita.getGambar());

            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println("Exception : " + e.getMessage());
            }

            //delete menu
            repo.delete(berita);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        return "redirect:/index2";
    }


    
    




}

        
        






