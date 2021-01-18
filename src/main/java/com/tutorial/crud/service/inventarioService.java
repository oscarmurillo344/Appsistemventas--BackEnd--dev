package com.tutorial.crud.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.inventario;
import com.tutorial.crud.repository.inventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class inventarioService {

    @Autowired
    inventarioRepository inventariorepository;

    public List<inventario> listar(){ return inventariorepository.findAll(); }

    public Optional<inventario> getOne(int id){ return inventariorepository.findById(id); }

    public void  save(inventario inven){ inventariorepository.save(inven); }

    public void delete(int id){
        inventariorepository.deleteById(id);
    }

    public boolean existsById(int id){
        return inventariorepository.existsById(id);
    }

    public inventario ActulizarProduct(Producto pid){ return inventariorepository.findByProductoId(pid);}





}
