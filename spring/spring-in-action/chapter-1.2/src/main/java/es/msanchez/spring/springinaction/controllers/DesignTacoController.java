package es.msanchez.spring.springinaction.controllers;

import es.msanchez.spring.springinaction.entities.Ingredient;
import es.msanchez.spring.springinaction.entities.Taco;
import es.msanchez.spring.springinaction.enums.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    /**
     * This is going to be loaded every time we load for "design" view.
     *
     * @param model -
     */
    @ModelAttribute
    public void addIngredientsToModel(final Model model) {
        final List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Ceddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour cream", Type.SAUCE));

        for (final Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(final Model model) {
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") final Taco design,
                                    final Errors errors,
                                    final Model model) {
        if (errors.hasErrors()) {
            log.error("Errors found on processDesign()");
            return "design";
        }

        log.info("processing design '{}'", design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(final List<Ingredient> ingredients,
                                          final Type type) {
        return ingredients.stream()
                .filter(ing -> type.equals(ing.getType()))
                .collect(Collectors.toList());
    }
}
