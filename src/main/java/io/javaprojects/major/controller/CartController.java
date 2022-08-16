package io.javaprojects.major.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.javaprojects.major.global.GlobalDetails;
import io.javaprojects.major.model.Product;
import io.javaprojects.major.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalDetails.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount", GlobalDetails.cart.size());
		model.addAttribute("total", (int) GlobalDetails.cart.stream().mapToDouble(Product::getWeight).sum());
		model.addAttribute("cart", GlobalDetails.cart);
		return "cart";

	}

	@GetMapping("/cart/removeItem/{item}")
	public String cartItemRemove(@PathVariable int item) {
		GlobalDetails.cart.remove(item);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		List<Product> ls = GlobalDetails.cart;
		int sum = 0;
		for (int i = 0; i < ls.size(); i++) {
			sum += ls.get(i).getPrice();
		}

		model.addAttribute("total", sum);
		return "checkout";

	}

}
