package org.ais.hr.fileupload.Service;

import org.ais.hr.fileupload.dto.LovResponse;

import java.util.List;

public interface HrLovService {
    List<LovResponse> lovRemitCompany();

    List<LovResponse> lovFinInstBank();
}
