/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.mgb.Controler;

import com.portfolio.mgb.Dto.dtoProyecto;
import com.portfolio.mgb.Entity.Proyecto;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = {"http://localhost:4200","https://frontendlv8.web.app"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    @GetMapping("detail/{id}")
    
    public ResponseEntity<Proyecto> getById(@PathVariable("id")int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
     Proyecto proyecto  = sProyecto.getOne(id).get();
     return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list= sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
 @PutMapping("/update/{id}")
 public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoProyecto dtoproyecto){
     if(!sProyecto.existsById(id)){
         return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
     }
     if(sProyecto.existsByNombreP(dtoproyecto.getNombreP())&& sProyecto.getByNombreP(dtoproyecto.getNombreP()).get().getId()!=id){
         return new ResponseEntity(new Mensaje("Ese nombre de proyecto ya existe"), HttpStatus.BAD_REQUEST);
     }
     if(StringUtils.isBlank(dtoproyecto.getNombreP())){
         return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
     }
     Proyecto proyecto= sProyecto.getOne(id).get();
     proyecto.setNombreP(dtoproyecto.getNombreP());
     proyecto.setDescriptionP(dtoproyecto.getDescriptionP());
     proyecto.setImg(dtoproyecto.getImg());
     sProyecto.save(proyecto);
     return new ResponseEntity(new Mensaje("Proyecto editado"), HttpStatus.OK);
    }
     @DeleteMapping("delete/{id}")
 public ResponseEntity<?> delete(@PathVariable("id")int id){
     if(!sProyecto.existsById(id)){
         return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
     }
     sProyecto.delete(id);
     return new ResponseEntity(new Mensaje("Proyecto ELiminado"), HttpStatus.OK);
 }
 @PostMapping("create")
 public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto){
     if(StringUtils.isBlank(dtoproyecto.getNombreP())){
         return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
     }
     if(sProyecto.existsByNombreP(dtoproyecto.getNombreP())){
         return new ResponseEntity(new Mensaje("Ese nombre de proyecto ya existe"), HttpStatus.BAD_REQUEST);
     }
     Proyecto proyecto= new Proyecto(
     dtoproyecto.getNombreP(), dtoproyecto.getDescriptionP(), dtoproyecto.getImg()
     );
     sProyecto.save(proyecto);
     return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
 }   
}
