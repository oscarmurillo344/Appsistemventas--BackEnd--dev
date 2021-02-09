package com.tutorial.crud.security.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.dto.JwtDto;
import com.tutorial.crud.security.dto.LoginUsuario;
import com.tutorial.crud.security.dto.NuevoUsuario;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.jwt.JwtProvider;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"https://asadero-front-end-dev.herokuapp.com","http://192.168.100.20:4200"})
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaUsu")
    public ResponseEntity<List<Usuario>> list() {
        try{
        List<Usuario> list = usuarioService.listar();
        return new ResponseEntity(list, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        try{
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        try{
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try{
        if (!usuarioService.existeUser(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        usuarioService.eliminarUser(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleterol/{codigo}")
    public ResponseEntity<?> deleteRol(@PathVariable("codigo") int codigo) {
        try{
        if (!rolService.existeRol(codigo))
            return new ResponseEntity(new Mensaje("no existe rol"), HttpStatus.NOT_FOUND);
        rolService.eliminarRol(codigo);
        return new ResponseEntity(new Mensaje("Rol eliminado"), HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createrol")
    public ResponseEntity<?> CrearRol(){
        try{
             Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
             Rol rolUser = new Rol(RolNombre.ROLE_USER);
             if(rolService.getByRolNombre(rolAdmin.getRolNombre()) != null &&
                     rolService.getByRolNombre(rolAdmin.getRolNombre()) != null) {
                rolService.saveRol(rolAdmin);
                rolService.saveRol(rolUser);
            }
            return new ResponseEntity(new Mensaje("Roles creados"),HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity(new Mensaje
                    ("Error: ".concat(ex.getMessage()).concat(", "+ex.getMostSpecificCause().getMessage())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
