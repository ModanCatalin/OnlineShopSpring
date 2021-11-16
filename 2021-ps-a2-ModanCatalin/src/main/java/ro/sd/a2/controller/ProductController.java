package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.Product;
import ro.sd.a2.service.ProductService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class ProductController
{

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);
    private List<ProductDto> inventar = new ArrayList<>();

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ModelAndView showProfile()
    {

        ModelAndView mav = new ModelAndView();

        Product product = new Product();
        product.setProductName("user@email.com");

        mav.addObject("userObj", product);
        mav.addObject("name", product.getProductName());
        mav.addObject("price", product.getPrice());

        mav.setViewName("products");

        return mav;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(ProductDto productDto)
    {
        log.info("Received a request to create a new product.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("productObj", productDto);
        mav.addObject("productName", productDto.getProductName());

        String result = productService.createProduct(productDto);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @PostMapping("/updateProduct")
    public ModelAndView updateProduct(ProductDto productDto)
    {
        log.info("reveiced a request to update a product");

        ModelAndView mav = new ModelAndView();

        mav.addObject("productObj1", productDto);
        mav.addObject("productName", productDto.getProductName());

        String result = productService.updateProduct(productDto);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;

    }

    @PostMapping("/deleteProduct")
    public ModelAndView deleteProduct(ProductDto productDto)
    {
        log.info("received a request to update a product.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("productObj1", productDto);
        mav.addObject("numeObject1", productDto.getProductName());

        String result = productService.deleteByName(productDto);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/showProducts")
    public ModelAndView showProduct()
    {
        ModelAndView mav = new ModelAndView();
        List<ProductDto> productDtos = productService.getAllProduct();
        mav.addObject("products", productDtos);

        mav.setViewName("allProducts");

        return mav;
    }


    @Scheduled(fixedRate = 60000L)
    void inventory(){
        inventar = productService.getAllProduct();

        for(ProductDto entry : inventar)
        {
            System.out.println("name "+ entry.getProductName() + " : pret "+ entry.getPrice() +"lei");
        }

    }

}
