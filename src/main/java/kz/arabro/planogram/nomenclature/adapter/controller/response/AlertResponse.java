package kz.arabro.planogram.nomenclature.adapter.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AlertResponse {
    private String httpCode;
    private String path;
    private String errorCode;
    private String msg;
    private long timestamp;
}
