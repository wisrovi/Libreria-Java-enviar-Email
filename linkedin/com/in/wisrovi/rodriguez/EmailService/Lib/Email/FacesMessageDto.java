package SenInt2.fcv.org.EmailService.linkedin.com.in.wisrovi.rodriguez.EmailService.Lib.Email;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FacesMessageDto implements Serializable {

    private final Map<String, String> mensajes;

    public FacesMessageDto(String tipoConstructor, String constructor, String tipo, String mensaje) {
        this.mensajes = new HashMap<>();
        if (constructor != null && !constructor.equals("")) {
            this.mensajes.put(tipoConstructor,constructor);
        }
        this.mensajes.put(tipo, mensaje);
    }

    public Map<String, String> getMensajes() {
        return mensajes;
    }

}
