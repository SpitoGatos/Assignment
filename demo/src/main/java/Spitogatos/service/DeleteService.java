package Spitogatos.service;

import Spitogatos.api.model.RealEstateAdvertisement;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    private final List<RealEstateAdvertisement> advertisements = new ArrayList<>();

    private final SessionFactory sessionFactory;

    public DeleteService() {
        sessionFactory = null;
    }

    // Other methods...

    public boolean deleteAdvertisement(Long id) {
        // Remove from the in-memory list
        advertisements.removeIf(advertisement -> advertisement.getId().equals(id));

        // Try to do delete from the database also
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Load the advertisement entity from the database
            RealEstateAdvertisement advertisement = session.get(RealEstateAdvertisement.class, id);

            // Check if the advertisement exists
            if (advertisement != null) {
                // Delete the advertisement
                session.delete(advertisement);
                transaction.commit();
                return true;
            } else {
                // Advertisement not found, handle accordingly
                System.out.println("Advertisement with ID " + id + " not found.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
