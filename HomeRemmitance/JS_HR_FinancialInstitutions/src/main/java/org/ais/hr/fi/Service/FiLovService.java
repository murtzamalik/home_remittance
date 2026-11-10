package org.ais.hr.fi.Service;

import org.ais.hr.fi.dto.LovResponse;

import java.util.List;

public interface FiLovService {
    List<LovResponse> lovCountry();

    List<LovResponse> lovCity(Long countryId);

    List<LovResponse> lovOrganization();

    List<LovResponse> lovGlAccType();

    List<LovResponse> lovTransFields();

    List<LovResponse> lovParsers();

    List<LovResponse> lovNature();

    List<LovResponse> lovRemitMode();
}
