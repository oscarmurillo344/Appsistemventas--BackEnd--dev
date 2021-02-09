package com.tutorial.crud.controller;


import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.actualizarPollo;
import com.tutorial.crud.dto.inventarioDto;
import com.tutorial.crud.entity.diaPollos;
import com.tutorial.crud.entity.inventario;
import com.tutorial.crud.service.ProductoService;
import com.tutorial.crud.service.diaPolloService;
import com.tutorial.crud.service.inventarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/inventario")
@CrossOrigin(origins = {"https://asadero-front-end-dev.herokuapp.com","192.168.100.20:4200"})
public class inventarioController {

    @Autowired
    inventarioService inventarioservice;
    @Autowired
    ProductoService productoService;
    @Autowired
    diaPolloService diaservice;


    @GetMapping("/lista")
    public ResponseEntity<ArrayList<inventario>> list(){
        try{
        List<inventario> list = inventarioservice.listar();
        return new ResponseEntity(list, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addInventario")
    public ResponseEntity<?> create(@RequestBody inventarioDto invenDto){
        try{
        if(invenDto.getCantidad()<0)
            return new ResponseEntity(new Mensaje("cantidad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(invenDto.getProductoId().getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(invenDto.getProductoId().getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(invenDto.getProductoId().getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
            inventario inven=new inventario(
                    Calendar.getInstance(),invenDto.getProductoId(),invenDto.getExtras(),
                    invenDto.getCantidad(),invenDto.getCantidad());
            inventarioservice.save(inven);
        return new ResponseEntity(new Mensaje("Ingreso Exitoso"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateinventario/{idIven}")
    public ResponseEntity<?> Update(@PathVariable("idIven")int id,@RequestBody inventarioDto invenDto){
        try{
        if(invenDto.getCantidad()<0)
            return new ResponseEntity(new Mensaje("cantidad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        inventario inven=inventarioservice.getOne(id).get();
        inven.setFecha(Calendar.getInstance());
        inven.setCantidad(invenDto.getCantidad());
        inven.setCantidadExist(invenDto.getCantidad());
        inven.setExtras(invenDto.getExtras());
        inventarioservice.save(inven);
        return new ResponseEntity(new Mensaje("Actualizacion Exitosa"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        try{
        if(!inventarioservice.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        inventarioservice.delete(id);
        return new ResponseEntity(new Mensaje("producto e inventario eliminado"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatepollo/{id}")
    public ResponseEntity<?> updatepollo(@PathVariable("id")int id, @RequestBody actualizarPollo update)
    {
        try{
        int valor=0,presa=0;
        if(!inventarioservice.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(!inventarioservice.getOne(id).get().getProductoId().getTipo().equals("mercaderia"))
            return new ResponseEntity(new Mensaje("no es el tipo"), HttpStatus.BAD_REQUEST);
        inventario inventa=inventarioservice.getOne(id).get();

        valor=inventa.getCantidadExist()-update.getPollo();
        if(update.getPresa()>0){
            valor--;
            presa=8+inventa.getProductoId().getPresa();
            presa=presa-update.getPresa();
            inventa.getProductoId().setPresa(presa);
        }
        inventa.setCantidadExist(valor);
        inventarioservice.save(inventa);
        return new ResponseEntity(new Mensaje("Pollo actualizado"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pollotable")
    public ResponseEntity<?> PolloTable(@RequestBody actualizarPollo update)
    {
        try{
        if(update.getPollo()<0)
            return new ResponseEntity(new Mensaje("Debe ser mayor a 0"),HttpStatus.BAD_REQUEST);
        diaPollos dia;
        if(!diaservice.Existe(1)){
            diaservice.Guardar(new diaPollos(0,0));
            dia=diaservice.Listar(1);
        }else{
            dia=diaservice.Listar(1);
        }
        dia.setPollo(update.getPollo());
        dia.setPresa(update.getPresa());
        diaservice.Guardar(dia);
        return new ResponseEntity(new Mensaje("Actualizacion exitosa"),HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pollopresa")
    public ResponseEntity<actualizarPollo> lista()
    {
        try{
        diaPollos dia=diaservice.Listar(1);
        actualizarPollo ac=new actualizarPollo(dia.getPollo(), dia.getPresa());
        return new ResponseEntity(ac,HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
