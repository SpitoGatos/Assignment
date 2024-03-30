package Spitogatos.api.controller;
import Spitogatos.api.model.RealEstateAdvertisement;
import Spitogatos.api.model.User;
import Spitogatos.service.AuthenticationService;
import Spitogatos.service.DeleteService;
import Spitogatos.service.AddRealEstateService;
import Spitogatos.service.GetService;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController 
public class UserController {

    private AuthenticationService authenticationService;
    private jakarta.validation.Validator validator;

    private AddRealEstateService realEstateService;

    private DeleteService deleteService;

    @Autowired
    private GetService getService;

    private UserController(AuthenticationService authenticationService, AddRealEstateService realEstateService, jakarta.validation.Validator validator) {
        this.authenticationService = authenticationService;
        this.validator = validator;
        this.realEstateService = realEstateService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setRealEstateService(AddRealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @Autowired
    public void setValidator(jakarta.validation.Validator validator) {
        this.validator = validator;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request) {
        String username = request.getUsername();
        String password = request.getPassword();

        String token = authenticationService.authenticate(username, password);
        if (token != null && authenticationService.verifyToken(token)) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @PostMapping("/add")
    public ResponseEntity<String> addAdvertisement(@RequestBody RealEstateAdvertisement request) {
        // Perform server-side validation
        Set<ConstraintViolation<RealEstateAdvertisement>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            // Handle validation errors
            // You can return error messages to the client or handle them in any suitable way
            // For simplicity, just returning a bad request status and the first error message
            ConstraintViolation<RealEstateAdvertisement> violation = violations.iterator().next();
            return ResponseEntity.badRequest().body(violation.getMessage());
        }
        // Add validation logic here if needed
        realEstateService.addAdvertisement(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement added successfully");
    }

    @GetMapping("/advertisements")
    public ResponseEntity<List<RealEstateAdvertisement>> getUserAdvertisements(@AuthenticationPrincipal UserDetails userDetails) {
        return getService.getUserAdvertisements(userDetails);

    }

    @DeleteMapping("/advertisements/{id}")
    public ResponseEntity<?> deleteUserAdvertisement(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = userDetails.getUsername(); // Get the username of the authenticated user

        if (!authenticationService.isUserAuthenticated(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Assuming you have a method in AuthenticationService to delete user advertisement by ID
        boolean deleted = deleteService.deleteAdvertisement( id );

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

    }

}
