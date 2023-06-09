/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.mgb.Service;

import com.portfolio.mgb.Entity.Hys;
import com.portfolio.mgb.Repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
@Transactional
public class SHys {
    @Autowired
    RHys rHys;
    
    public List<Hys> list(){
        return rHys.findAll();
    }
    public Optional<Hys> getOne(int id){
        return rHys.findById(id);
    }
    public Optional<Hys> getByNombre(String nombre){
        return rHys.findByNombre(nombre);
    }
    public void save(Hys skill){
        rHys.save(skill);
    }
    public void delete(int id){
        rHys.deleteById(id);
    }
    public boolean existsById(int id){
        return rHys.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return rHys.existsByNombre(nombre);
    }
}
