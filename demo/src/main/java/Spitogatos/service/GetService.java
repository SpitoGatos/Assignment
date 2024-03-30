package Spitogatos.service;
import Spitogatos.api.model.RealEstateAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetService {

    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity<List<RealEstateAdvertisement>> getUserAdvertisements(UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = userDetails.getUsername(); // Get the username of the authenticated user

        if (!authenticationService.isUserAuthenticated(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<RealEstateAdvertisement> userAdvertisements = authenticationService.getUserAdvertisements(username);
        return ResponseEntity.ok(userAdvertisements);
    }
}