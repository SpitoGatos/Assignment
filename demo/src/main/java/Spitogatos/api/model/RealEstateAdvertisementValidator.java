package Spitogatos.api.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;


//import javax.xml.validation.Validator;
import java.util.Set;
public class RealEstateAdvertisementValidator {

    private final jakarta.validation.Validator validator;

    public RealEstateAdvertisementValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Set<ConstraintViolation<RealEstateAdvertisement>> validate(RealEstateAdvertisement advertisement) {
        return validator.validate(advertisement);
    }
}
