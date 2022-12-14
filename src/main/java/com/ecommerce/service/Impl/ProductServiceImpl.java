package com.ecommerce.service.Impl;

import com.ecommerce.exception.NoSuchProductWithIdException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product getById(Long id) {
        return this.productRepository.findById(id).orElseThrow(NoSuchProductWithIdException::new);
    }

    public Product getBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    public String persistDailyProductInfo(String json) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        System.out.println(jsonArray.get(1).getAsJsonObject().get("Product categories"));
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Product product = new Product();
            product.setTitle(String.valueOf(jsonObject.get("Title")));
            product.setContent(String.valueOf(jsonObject.get("Content")));
            product.setSku(String.valueOf(jsonObject.get("Sku")));
            product.setProductType(String.valueOf(jsonObject.get("Product Type")));
            product.setParentProductId(String.valueOf(jsonObject.get("Parent Product ID")));
            product.setProductCategories(String.valueOf(jsonObject.get("Product categories")));
            product.setProductBrand(String.valueOf(jsonObject.get("Product Brand")));
            product.setProductEan(String.valueOf(jsonObject.get("Product EAN")));
            product.setTsvyat(String.valueOf(jsonObject.get("Tsvyat")));
            if (String.valueOf(jsonObject.get("Price")) != null) {
                product.setPrice(Double.parseDouble(String.valueOf(jsonObject.get("Price"))));
            }
            product.setStock(Integer.parseInt(String.valueOf(jsonObject.get("Stock"))));
            product.setStockStatus(String.valueOf(jsonObject.get("Stock Status")));
            product.setImageUrl(String.valueOf(jsonObject.get("Image URL")));
            product.setImageFeatured(String.valueOf(jsonObject.get("Image Featured")));
            product.setImageTitle(String.valueOf(jsonObject.get("Image Title")));
            product.setWeight(String.valueOf(jsonObject.get("Weight")));

            productRepository.save(product);
//            if (product.getProductCategories().contains("???????????? ??????????????")) {
//                List<DetskiIgrachki> detskiIgrachkiList = detskiIgrachkiRepository.findAll();
//                DetskiIgrachki detskiIgrachki = new DetskiIgrachki();
//                detskiIgrachki.setProducts(product);
//                detskiIgrachkiRepository.save(detskiIgrachki);
//            }
//            if (product.getProductCategories().contains("?????? ?? ??????????????")) {
//                List<DomIGradina> domIGradinaList = domIGradinaRepository.findAll();
//                DomIGradina domIGradina = new DomIGradina();
//                domIGradina.setProduct(product);
//                domIGradinaRepository.save(domIGradina);
//            }
//            if (product.getProductCategories().contains("???????????????? ?? ??????????????????????")) {
//                List<KompiutriIElektronika> kompiutriIElektronikaList = kompiutriIElektronikaRepository.findAll();
//                KompiutriIElektronika kompiutriIElektronika = new KompiutriIElektronika();
//                kompiutriIElektronika.setProduct(product);
//                kompiutriIElektronikaRepository.save(kompiutriIElektronika);
//            }
//            if (product.getProductCategories().contains("?????????? ?? ??????????????????????")) {
//                List<KuhnqIGastronomiya> kuhnqIGastronomiyaList = kuhnqIGastronomiyaRepository.findAll();
//                KuhnqIGastronomiya kuhnqIGastronomiya = new KuhnqIGastronomiya();
//                kuhnqIGastronomiya.setProduct(product);
//                kuhnqIGastronomiyaRepository.save(kuhnqIGastronomiya);
//            }
//            if (product.getProductCategories().contains("???????????? ???? ??????????????????????")) {
//                List<VidyanoPoTv> vidyanoPoTvList = vidyanoPoTvRepository.findAll();
//                VidyanoPoTv vidyanoPoTv = new VidyanoPoTv();
//                vidyanoPoTv.setProduct(product);
//                vidyanoPoTvRepository.save(vidyanoPoTv);
//            }
//            if (product.getProductCategories().contains("???????????? ?? ??????????????")) {
//                List<ZdraveIKrasota> zdraveIKrasotaList = zdraveIKrasotaRepository.findAll();
//                ZdraveIKrasota zdraveIKrasota = new ZdraveIKrasota();
//                zdraveIKrasota.setProduct(product);
//                zdraveIKrasotaRepository.save(zdraveIKrasota);
//            }

        }

        return "ok";
    }

    @Override
    public List<Product> searchProductsByKeywords(String keywords) {
        String[] keywordsMassive = keywords.split(" ");
        List<Product> products = new ArrayList<>();


        for (int i = 0; i < keywordsMassive.length; i++) {
            if (!productRepository.findAllByContentContains(keywordsMassive[i]).isEmpty()) {
                products.addAll(productRepository.findAllByContentContains(keywordsMassive[i]));
            }
            if (!productRepository.findAllByTitleContains(keywordsMassive[i]).isEmpty()) {
                products.addAll(productRepository.findAllByTitleContains(keywordsMassive[i]));
            }
        }
        return products;
    }

//    public List<Product> getProductsByCategoryStandard(String category) {
//        if (category.equals("DetskiIgrachki")) {
//            List<Product> products = new ArrayList<>();
//            detskiIgrachkiRepository.findAll().forEach(a -> products.add(a.getProducts()));
//            return products;
//        }
//        if(category.equals("DomIGradina")){
//            List<Product> products = new ArrayList<>();
//            domIGradinaRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("KlimatizaciyaIVentilaciya")){
//            List<Product> products = new ArrayList<>();
//            klimatizaciyaIVentilaciyaRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("KompiutriIElektronika")){
//            List<Product> products = new ArrayList<>();
//            kompiutriIElektronikaRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("KuhnqIGastronomiya")){
//            List<Product> products = new ArrayList<>();
//            kuhnqIGastronomiyaRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("SportISvobodnoVreme")){
//            List<Product> products = new ArrayList<>();
//            sportISvobodnoVremeRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("VidyanoPoTv")){
//            List<Product> products = new ArrayList<>();
//            vidyanoPoTvRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        if(category.equals("ZdraveIKrasota")){
//            List<Product> products = new ArrayList<>();
//            zdraveIKrasotaRepository.findAll().forEach(a -> products.add(a.getProduct()));
//            return products;
//        }
//        return new ArrayList<>();
//    }

}
