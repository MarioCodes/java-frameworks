package es.msanchez.spring.springinaction.controllers;

import es.msanchez.spring.springinaction.dao.IngredientRepository;
import es.msanchez.spring.springinaction.dao.TacoRepository;
import es.msanchez.spring.springinaction.entities.Ingredient;
import es.msanchez.spring.springinaction.entities.Order;
import es.msanchez.spring.springinaction.entities.Taco;
import es.msanchez.spring.springinaction.enums.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    /**
     * @param ingredientRepository injected
     * @param tacoRepository       injected
     */
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

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
        final List<Ingredient> ingredients = new ArrayList<>();
        this.ingredientRepository.findAll().forEach(ingredients::add);

        final List<Type> types = CollectionUtils.arrayToList(Type.values());
        for (final Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") final Taco design,
                                final Errors errors,
                                @ModelAttribute final Order order) {
        if (errors.hasErrors()) {
            log.error("Errors found on processDesign()");
            return "design";
        }

        final Taco saved = this.tacoRepository.save(design);
        order.getTacos().add(saved);

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
