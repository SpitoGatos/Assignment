package Spitogatos.service;

import Spitogatos.api.model.RealEstateAdvertisement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService {
    private static final String SECRET_KEY = "your_secret_key"; // this can be changed to whatever secret key we want to use
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, List<RealEstateAdvertisement>> userAdvertisements = new HashMap<>();

    static {
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        userCredentials.put("user3", "password3");

        // Assuming user advertisements are already stored somewhere, possibly added manually for demonstration
        userAdvertisements.put("user1", new ArrayList<>());
        userAdvertisements.put("user2", new ArrayList<>());
        userAdvertisements.put("user3", new ArrayList<>());
    }

    public boolean isUserAuthenticated(String username) {
        return userCredentials.containsKey(username);
    }
    public List<RealEstateAdvertisement> getUserAdvertisements(String username) {
        return userAdvertisements.getOrDefault(username, new ArrayList<>());
    }
    public String authenticate(String username, String password) {
        String storedPassword = userCredentials.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            // Generate token
            return generateToken(username);
        }
        return null; // Authentication failed
    }

    private String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
