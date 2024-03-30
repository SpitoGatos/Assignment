package Spitogatos.service;

import Spitogatos.api.model.RealEstateAdvertisement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class AddRealEstateService {

    private final List<RealEstateAdvertisement> advertisements = new ArrayList<>();
    private final SessionFactory sessionFactory;

    public AddRealEstateService() {
        // Create SessionFactory
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public void addAdvertisement(@org.jetbrains.annotations.NotNull RealEstateAdvertisement request) {
        RealEstateAdvertisement advertisement = new RealEstateAdvertisement();
        advertisement.setArea(request.getArea());
        advertisement.setPrice(request.getPrice());
        advertisement.setAvailability(request.getAvailability());
        advertisement.setAreaSize(request.getAreaSize());

        advertisements.add(advertisement);

        // Save advertisement to the database or at least try to :P
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(request);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
