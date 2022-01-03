package pl.coderslab.charity.converter;


import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Institution;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.repository.InstitutionRepository;

@Component
public class InstitutionConverter implements Converter<String, Institution> {


    private final InstitutionRepository institutionRepository;

    public InstitutionConverter(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    @Override
    public Institution convert(String s) {
        return institutionRepository.getOne(Long.parseLong(s));
    }
}
