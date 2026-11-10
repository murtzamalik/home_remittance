package org.ais.hr.user_management.service;

import org.ais.hr.user_management.dto.LovResponse;

import java.util.List;

public interface JsHrUserManagementLovService {
    List<LovResponse> lovRoleRights();

    List<LovResponse> lovMenu();

    List<LovResponse> lovModule();

    List<LovResponse> lovRole();

    List<LovResponse> lovLocation();
}
