package com.dev.BackFenixc.service.serviceImpl;

import com.dev.BackFenixc.JWT.controller.request.CloudinaryResponse;
import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.security.util.EmailUtil;
import com.dev.BackFenixc.JWT.security.util.FileUploadUtil;
import com.dev.BackFenixc.JWT.services.CloudinaryService;
import com.dev.BackFenixc.entity.Detallefactura;
import com.dev.BackFenixc.entity.Producto;
import com.dev.BackFenixc.repository.DetallefacturaRepository;
import com.dev.BackFenixc.repository.ProductoRepository;
import com.dev.BackFenixc.service.ProductoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallefacturaRepository detallefacturaRepository;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private EmailUtil emailUtil;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }


    @Override
    public List<Producto> getAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public List<Producto> findByCategoria(String categoria) {
        return (List<Producto>) productoRepository.findByCategoria(categoria);
    }

    @Override
    public Optional<Producto> getById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }
    @Transactional
    public void uploadImage(final Integer id, final MultipartFile file){
        final Producto producto = this.productoRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro el producto"));
        FileUploadUtil.assertAllowed(file,FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = this.cloudinaryService.uploadFile(file,fileName);
        producto.setCloudinaryImageId(response.getPublicId());
        producto.setImageUrl(response.getUrl());
        this.productoRepository.save(producto);

    }

    public void uploadPaymentProof(List<Integer> productoIds, MultipartFile file, String buyerUsername, BigDecimal vt) throws MessagingException {
        // Verifica que el archivo es válido
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);

        // Genera un nombre de archivo único
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());

        // Sube el archivo a Cloudinary (u otro servicio)
        final CloudinaryResponse response = this.cloudinaryService.uploadFile(file, fileName);

        // Concatenar los nombres de todos los productos comprados
        String productNames = productoIds.stream()
                .map(id -> {
                    Producto producto = this.productoRepository.findById(id).orElseThrow(
                            () -> new RuntimeException("No se encontró el producto con ID: " + id));
                    //producto.setStockproducto(producto.getStockproducto() - 1); // Ajustar según la cantidad específica
                    //productoRepository.save(producto);
                    return producto.getNomproducto();
                })
                .collect(Collectors.joining(", "));
        // Obtener el nombre de usuario del vendedor (asumimos que todos los productos tienen el mismo vendedor)
        String sellerUsername = productoRepository.findById(productoIds.get(0))
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + productoIds.get(0)))
                .getUsername();
        String sellerUsernameEmail = productoRepository.findById(productoIds.get(0))
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + productoIds.get(0)))
                .getEmail();

        // Crear una nueva entrada de PaymentProof
        Detallefactura paymentProof = new Detallefactura();
        paymentProof.setProductName(productNames);
        paymentProof.setPaymentProofUrl(response.getUrl());
        paymentProof.setBuyerUsername(buyerUsername);
        paymentProof.setSellerUsername(sellerUsername); // Ajustar según sea necesario
        paymentProof.setState("PENDIENTE");
        paymentProof.setValorTotal(vt);

        // Guardar PaymentProof en la base de datos
        detallefacturaRepository.save(paymentProof);
        emailUtil.sendPurchase(sellerUsernameEmail, productNames);
    }
    /*public void uploadPaymentProof(final Integer id, final MultipartFile file, final String buyerUsername, final BigDecimal vt) {
        final Producto producto = this.productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el producto"));

        // Verifica que el archivo es válido
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);

        // Genera un nombre de archivo único
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());

        // Sube el archivo a Cloudinary (u otro servicio)
        final CloudinaryResponse response = this.cloudinaryService.uploadFile(file, fileName);

        // Crear una nueva entrada de PaymentProof
        Detallefactura paymentProof = new Detallefactura();
        paymentProof.setProductName(producto.getNomproducto());
        paymentProof.setPaymentProofUrl(response.getUrl());
        paymentProof.setBuyerUsername(buyerUsername);
        paymentProof.setSellerUsername(producto.getUsername()); // campo id del usuario
        paymentProof.setProducto(producto);
        paymentProof.setState("PENDIENTE");
        paymentProof.setValorTotal(vt);

        // Guardar PaymentProof en la base de datos
        detallefacturaRepository.save(paymentProof);
    }*/




    @Override
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }
}
