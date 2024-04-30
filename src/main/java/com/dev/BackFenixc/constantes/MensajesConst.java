package com.dev.BackFenixc.constantes;
import org.springframework.beans.factory.annotation.Value;
public class MensajesConst {
    @Value("${spring.jpa.properties.hibernate.default_schema}")
    public static String DEFAULT_SCHEMA;

    public static final String REGISTRO_ELIMINADO_EXITO = "Registro eliminado con éxito";
}
