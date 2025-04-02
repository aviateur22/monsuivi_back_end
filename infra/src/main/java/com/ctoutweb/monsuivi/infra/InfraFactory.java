package com.ctoutweb.monsuivi.infra;

import com.ctoutweb.monsuivi.infra.model.document.IImageToSave;
import com.ctoutweb.monsuivi.infra.model.document.impl.ImageToSaveImpl;
import com.ctoutweb.monsuivi.infra.model.error.ErrorMessageImpl;
import com.ctoutweb.monsuivi.infra.model.error.IErrorMessage;
import org.springframework.stereotype.Component;
@Component
public class InfraFactory {
  public IImageToSave getImageToSaveImpl() {
    return new ImageToSaveImpl();
  }

  public IErrorMessage getErrorMessageImpl(String errorMessage) {
    return new ErrorMessageImpl(errorMessage);
  }
}
