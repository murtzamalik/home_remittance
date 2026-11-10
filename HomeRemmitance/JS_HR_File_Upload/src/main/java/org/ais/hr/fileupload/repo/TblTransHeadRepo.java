package org.ais.hr.fileupload.repo;

import org.ais.hr.fileupload.model.TblTransHead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblTransHeadRepo extends JpaRepository<TblTransHead,Long> {
    TblTransHead findByFileName(String fileName);
}
