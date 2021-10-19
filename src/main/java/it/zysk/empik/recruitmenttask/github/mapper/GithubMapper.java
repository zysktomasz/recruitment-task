package it.zysk.empik.recruitmenttask.github.mapper;

import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GithubMapper {

    org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GithubMapper.class);

    @Mappings({
            @Mapping(source = "githubUser", target = "calculations", qualifiedByName = "performCalculations")
    })
    GithubUserDTO mapGithubUserToDTO(GithubUser githubUser);

    @Named("performCalculations")
    default Long performCalculations(GithubUser githubUser) {
        Long result;
        try {
            // assumes non-float numbers (as in requirements document)
            result = 6 / githubUser.getFollowers() * (2 + githubUser.getPublicRepos());
        } catch (Exception e) {
            log.info("Unable to calculate valid 'calculations' result for login: '{}'", githubUser.getLogin());
            result = null; // assumes that we want to return some default (null) value event if unable to calculate
        }

        return result;
    }
}
