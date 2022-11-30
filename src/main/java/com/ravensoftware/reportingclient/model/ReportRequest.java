package com.ravensoftware.reportingclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by bilga
 */
@Data
public class ReportRequest implements Serializable {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate toDate;

    private Integer merchant;

    private Integer acquirer;

}
