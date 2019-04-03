package es.msanchez.spring.springinaction.controllers;

import es.msanchez.spring.springinaction.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

  @GetMapping("/current")
  public String orderForm(final Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String handleOrder(@Valid final Order order,
                                   final Errors errors) {
    if(errors.hasErrors()) {
      return "orderForm";
    }

    log.info("order submitted '{}'", order);
    return "redirect:/";
  }

}
