package icu.rolin.easy.model.VO;

import org.springframework.http.ResponseEntity;

public class DownloadFileVO {

    private SimpleVO simpleVO;
    private ResponseEntity<byte[]> responseEntity;

    public DownloadFileVO(SimpleVO simpleVO, ResponseEntity<byte[]> responseEntity) {
        this.simpleVO = simpleVO;
        this.responseEntity = responseEntity;
    }

    public SimpleVO getSimpleVO() {
        return simpleVO;
    }

    public void setSimpleVO(SimpleVO simpleVO) {
        this.simpleVO = simpleVO;
    }

    public ResponseEntity<byte[]> getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(ResponseEntity<byte[]> responseEntity) {
        this.responseEntity = responseEntity;
    }
}
