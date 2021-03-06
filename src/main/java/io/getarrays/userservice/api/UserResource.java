package io.getarrays.userservice.api;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath ().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath ().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RolToUserForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath ().path("/api/role/save").toUriString());
        userService.addRoleToUser(form.getUserName(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    // @PostMapping("/role/addToUser") 
    // public void refreshToken(HttpServletRequest request, HttpServletResponse response){
    //     String authorizationHeader  = request.getHeader(AUTHORIZATION);
    // }

}

@Data
class RolToUserForm{
    private String userName;
    private String roleName;
}