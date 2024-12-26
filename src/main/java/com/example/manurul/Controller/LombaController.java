
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

import com.example.manurul.Dto.LombaDto;
import com.example.manurul.Repository.LombaRepository;
import com.example.manurul.entity.Lomba;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class LombaController {
    

    @Autowired
    private LombaRepository repo;

    
    
    @GetMapping("/prestasi")
    public String showPrestasiPage(Model model) {
        List<Lomba> lombaList = repo.findAll();
        model.addAttribute("lombaList", lombaList);
        return "prestasi";
    }
    
    
    @GetMapping("/lomba")
    public String showlombapage(Model model){
        List<Lomba> lomba = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("lomba", lomba);
        return"lomba";
    }
    

    @GetMapping("/create_lomba")
    public String buatlomba(Model model) {
        LombaDto lombaDto = new LombaDto();
        model.addAttribute("lombaDto", lombaDto);
        return "create_lomba";
    }
    
    @PostMapping("/create_lomba")
    public String buatlomba(LombaDto lombaDto, BindingResult result) {
        if (lombaDto.getGambar().isEmpty()) {
            result.addError(new FieldError("lombaDto", "gambar", "Gambar tidak boleh kosong"));
        }
        if (result.hasErrors()) {
            return"create_lomba";
        }

        //save image 
        MultipartFile gambar = lombaDto.getGambar();
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
        
        Lomba lomba = new Lomba();
        lomba.setNama(lombaDto.getNama());
        lomba.setJuara(lombaDto.getJuara());
        lomba.setPrestasi(lombaDto.getPrestasi());
        lomba.setGambar(storageFileName);

        repo.save(lomba);
        return "redirect:/lomba";
    }

    @GetMapping("/edit_lomba")
    public String showedit(Model model, @RequestParam int id){

        try {
            Lomba lomba = repo.findById(id).get();
            model.addAttribute("lomba", lomba);

            LombaDto lombaDto = new LombaDto();
            lombaDto.setNama(lomba.getNama());
            lombaDto.setJuara(lomba.getJuara());
            lombaDto.setPrestasi(lomba.getPrestasi());

            model.addAttribute("lombaDto", lombaDto);


        } catch (Exception e) {
            System.out.println("Exception : "+ e.getMessage());
            return "redirect:/lomba";
        }

        return "edit_lomba";
    }

    @PostMapping("/edit_lomba")
    public String edit(Model model, @RequestParam int id, @Valid @ModelAttribute LombaDto lombaDto, BindingResult result){
        
        try {
            Lomba lomba = repo.findById(id).get();
            model.addAttribute("lomba", lomba);
            
            if (result.hasErrors()) {
                return "edit_lomba";
            }

            if (!lombaDto.getGambar().isEmpty()) {
                //Delete Gambar Lama
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + lomba.getGambar());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception e) {
                    System.out.println("Exception : " + e.getMessage());
                }

                //Save new img
                MultipartFile gambar = lombaDto.getGambar();
                String storageFileName = gambar.getOriginalFilename();

                try (InputStream inputStream = gambar.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), 
                    StandardCopyOption.REPLACE_EXISTING);
                }
                lomba.setGambar(storageFileName);
            }

            lomba.setNama(lombaDto.getNama());
            lomba.setJuara(lombaDto.getJuara());
            lomba.setPrestasi(lombaDto.getPrestasi());

            repo.save(lomba);
        }   
        catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
            return "redirect:/lomba";
    }

    @GetMapping("/delete_lomba")
    public String delete(@RequestParam int id){

        try {

            Lomba lomba = repo.findById(id).get();
            Path imagePath = Paths.get("public/images/" + lomba.getGambar());

            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println("Exception : " + e.getMessage());
            }

            //delete menu
            repo.delete(lomba);

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        return "redirect:/lomba";
    }


    




}

        
        








