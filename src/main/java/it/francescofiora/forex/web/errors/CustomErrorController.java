package it.francescofiora.forex.web.errors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Capture and manage all Exception.
 * 
 */
@Controller
@Hidden
public class CustomErrorController extends AbstractErrorController {
  private static final String PATH = "/error";

  public CustomErrorController(ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  /**
   * return the error in JSON format.
   * 
   * @param request rest request
   * @return handle Error
   */
  @RequestMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
    HttpStatus status = super.getStatus(request);
    Map<String, Object> map = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
    return new ResponseEntity<>(map, status);
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }
}
